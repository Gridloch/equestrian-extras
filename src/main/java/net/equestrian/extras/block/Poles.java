package net.equestrian.extras.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.property.*;
import org.jetbrains.annotations.Nullable;

import me.shedaniel.autoconfig.AutoConfig;
import net.equestrian.extras.EquestrianExtras;
import net.equestrian.extras.config.ModConfig;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class Poles extends HorizontalFacingBlock implements Waterloggable {
    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty PLACEMENT = IntProperty.of("placement", 1, 3);
    public static final BooleanProperty HIT = BooleanProperty.of("hit");

    private final double singlePoleHeight; // Height of poles (from top to bottom), Should match width
    private final double placementChangeYMovement; // Movement up or down when pole has up or down placement
    private final double minSide;
    private final double maxSide;
    private final double bottomPoleMinY; // Lowest point on lower pole
    public final double bottomPoleMaxY;
    private final double topPoleMinY; // Lowest point on upper pole
    public final double topPoleMaxY;
    public final boolean isLargePoles;

    public Poles(AbstractBlock.Settings settings) {
        this(settings, false);
    }



    public Poles(AbstractBlock.Settings settings, boolean isLargePoles) { //
        super(settings.nonOpaque());
        this.isLargePoles = isLargePoles;

        if (isLargePoles) {
            singlePoleHeight = 6.0;
            placementChangeYMovement = 1.0;
            minSide = 5.0;
            bottomPoleMinY = 1.0;
            topPoleMinY = 9.0;
        } else {
            singlePoleHeight = 2.0; // Height of poles (from top to bottom), Should match width
            placementChangeYMovement = 3.0; // Movement up or down when pole has up or down placement
            minSide = 7.0;
            bottomPoleMinY = 3.0; // Lowest point on lower pole
            topPoleMinY = 11.0; // Lowest point on upper pole
        }
        maxSide = minSide + singlePoleHeight;
        bottomPoleMaxY = bottomPoleMinY + singlePoleHeight;
        topPoleMaxY = topPoleMinY + singlePoleHeight;

        this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(PLACEMENT, 1).with(HIT, false));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, Properties.HORIZONTAL_FACING, PLACEMENT, HIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        // Determines the outline shape based on the block's slab type, direction,
        // and placement (up, down, or centred)
        SlabType slabType = state.get(TYPE);
        Direction dir = state.get(FACING);
        switch (slabType) {
            case DOUBLE -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(0.0, bottomPoleMinY - placementChangeYMovement, minSide, 16.0, topPoleMaxY - placementChangeYMovement, maxSide);
                        case 3 -> Block.createCuboidShape(0.0, bottomPoleMinY + placementChangeYMovement, minSide, 16.0, topPoleMaxY + placementChangeYMovement, maxSide);
                        default -> Block.createCuboidShape(0.0, bottomPoleMinY, minSide, 16.0, topPoleMaxY, maxSide);
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(minSide, bottomPoleMinY - placementChangeYMovement, 0.0, maxSide, topPoleMaxY - placementChangeYMovement, 16.0);
                        case 3 -> Block.createCuboidShape(minSide, bottomPoleMinY + placementChangeYMovement, 0.0, maxSide, topPoleMaxY + placementChangeYMovement, 16.0);
                        default -> Block.createCuboidShape(minSide, bottomPoleMinY, 0.0, maxSide, topPoleMaxY, 16.0);
                    };
                }
            }
            case TOP -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(0.0, topPoleMinY - placementChangeYMovement, minSide, 16.0, topPoleMaxY - placementChangeYMovement, maxSide);
                        case 3 -> Block.createCuboidShape(0.0, topPoleMinY + placementChangeYMovement, minSide, 16.0, topPoleMaxY + placementChangeYMovement, maxSide);
                        default -> Block.createCuboidShape(0.0, topPoleMinY, minSide, 16.0, topPoleMaxY, maxSide);
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(minSide, topPoleMinY - placementChangeYMovement, 0.0, maxSide, topPoleMaxY - placementChangeYMovement, 16.0);
                        case 3 -> Block.createCuboidShape(minSide, topPoleMinY + placementChangeYMovement, 0.0, maxSide, topPoleMaxY + placementChangeYMovement, 16.0);
                        default -> Block.createCuboidShape(minSide, topPoleMinY, 0.0, maxSide, topPoleMaxY, 16.0);
                    };
                }
            }
            case BOTTOM -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(0.0, bottomPoleMinY - placementChangeYMovement, minSide, 16.0, bottomPoleMaxY - placementChangeYMovement, maxSide);
                        case 3 -> Block.createCuboidShape(0.0, bottomPoleMinY + placementChangeYMovement, minSide, 16.0, bottomPoleMaxY + placementChangeYMovement, maxSide);
                        default -> Block.createCuboidShape(0.0, bottomPoleMinY, minSide, 16.0, bottomPoleMaxY, maxSide);
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> Block.createCuboidShape(minSide, bottomPoleMinY - placementChangeYMovement, 0.0, maxSide, bottomPoleMaxY - placementChangeYMovement, 16.0);
                        case 3 -> Block.createCuboidShape(minSide, bottomPoleMinY + placementChangeYMovement, 0.0, maxSide, bottomPoleMaxY + placementChangeYMovement, 16.0);
                        default -> Block.createCuboidShape(minSide, bottomPoleMinY, 0.0, maxSide, bottomPoleMaxY, 16.0);
                    };
                }
            }
        }
        return Block.createCuboidShape(0.0, bottomPoleMinY, minSide, 16.0, bottomPoleMaxY, maxSide);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);

        if (blockState.isOf(this)) {
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(HIT, false);
        }
        BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(HIT, false);
        Direction direction = ctx.getSide();
        if (direction == Direction.DOWN || direction != Direction.UP && ctx.getHitPos().y - (double)blockPos.getY() > 0.5) {
            return blockState2.with(TYPE, SlabType.TOP).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
        }

        if (!ctx.getWorld().isClient() && ctx.getPlayer().isSneaking()) {
            return blockState2.with(PLACEMENT, 2);
        }

        return blockState2;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        SlabType slabType = state.get(TYPE);
        if (slabType == SlabType.DOUBLE || !itemStack.isOf(this.asItem())) {
            return false;
        }
        if (context.canReplaceExisting()) {
            boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5;
            Direction direction = context.getSide();
            if (slabType == SlabType.BOTTOM) {
                return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
            }
            return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
        }
        return true;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Waterloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return Waterloggable.super.canFillWithFluid(world, pos, state, fluid);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!player.getAbilities().allowModifyWorld || !itemStack.isEmpty()) {
            return ActionResult.PASS;
        }
        world.setBlockState(pos, state.cycle(PLACEMENT));
        return ActionResult.success(world.isClient);
    }


    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        boolean blockIsHighestPole = world.getBlockState(pos.up()).canPathfindThrough(world, pos.up(), NavigationType.LAND);
        boolean blockIsGroundPole = state.get(TYPE).equals(SlabType.BOTTOM) && state.get(PLACEMENT).equals(2);
        boolean blockBelowIsSolid = world.getBlockState(pos.down()).shouldSuffocate(world, pos);
        if (!world.isClient && entity.hasPlayerRider() && blockIsHighestPole && !(blockIsGroundPole && blockBelowIsSolid)) {
            // If entity has a player rider, pole can be jumped over, and is not a ground pole on a solid block, then
            // check that entity is in close contact with the poles
            Box box = getBox(state).offset(pos);
            List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, box);
            Boolean b1 = false;

            if (!list.isEmpty()) {
                for (Entity listentity : list) {
                    if (!listentity.hasPlayerRider()) continue;
                    b1 = true;
                }
            }

            if (b1) {
                // If entity has a player rider and is in close contact with poles, play sound on first contact
                // and continue to check for it
                world.createAndScheduleBlockTick(new BlockPos(pos), this, 20);
                
                if (state.get(HIT).equals(false)) {
                    world.playSound(
                        null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                        pos, // The position of where the sound will come from
                        EquestrianExtras.POLE_HIT_EVENT, // The sound that will play
                        SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                        1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                        1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                    );
                    world.setBlockState(pos, state.with(HIT, true), Block.NOTIFY_LISTENERS);
                    // Chance to break block when hit
                    int x = EquestrianExtras.RANDOM.nextInt(100); // Generates random integers 0 to 99
                    ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
                    int breakChance = isLargePoles ? config.largePoleBreakChance() : config.poleBreakChance();
                    if (x < breakChance) {
                        world.breakBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // checks for contact with poles
        Box box = getBox(state).offset(pos);
        List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, box);
        boolean b1 = false;

        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (!entity.hasPlayerRider()) continue;
                b1 = true;
            }
        }
        world.setBlockState(pos, state.with(HIT, b1), Block.NOTIFY_LISTENERS);
    }


    public Box getBox(BlockState state) {
        // Determines the box shape based on the block's slab type, direction,
        // and placement (up, down, or centred)

        Box box = new Box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

        SlabType slabType = state.get(TYPE);
        Direction dir = state.get(FACING);
        double pixelToBlock = 0.0625; // To convert from pixel scale to box
        double offset = 0.001;


        switch (slabType) {
            case DOUBLE -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, (bottomPoleMinY)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, topPoleMaxY *pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        case 3 -> new Box(0.0, (bottomPoleMinY + placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (topPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        default -> new Box(0.0, (bottomPoleMinY - placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (topPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, topPoleMaxY *pixelToBlock+offset, 1.0);
                        case 3 -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY + placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (topPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, 1.0);
                        default -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY - placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (topPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, 1.0);
                    };
                }
            }
            case TOP -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, (topPoleMinY)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, topPoleMaxY *pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        case 3 -> new Box(0.0, (topPoleMinY + placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (topPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        default -> new Box(0.0, (topPoleMinY - placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (topPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box((minSide)*pixelToBlock-offset, (topPoleMinY)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, topPoleMaxY *pixelToBlock+offset, 1.0);
                        case 3 -> new Box((minSide)*pixelToBlock-offset, (topPoleMinY + placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (topPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, 1.0);
                        default -> new Box((minSide)*pixelToBlock-offset, (topPoleMinY - placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (topPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, 1.0);
                    };
                }
            }
            case BOTTOM -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, (bottomPoleMinY)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, bottomPoleMaxY *pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        case 3 -> new Box(0.0, (bottomPoleMinY + placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (bottomPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                        default -> new Box(0.0, (bottomPoleMinY - placementChangeYMovement)*pixelToBlock-offset, (minSide)*pixelToBlock-offset, 1.0, (bottomPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, (maxSide)*pixelToBlock+offset);
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, bottomPoleMaxY *pixelToBlock+offset, 1.0);
                        case 3 -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY + placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (bottomPoleMaxY + placementChangeYMovement)*pixelToBlock+offset, 1.0);
                        default -> new Box((minSide)*pixelToBlock-offset, (bottomPoleMinY - placementChangeYMovement)*pixelToBlock-offset, 0.0, (maxSide)*pixelToBlock+offset, (bottomPoleMaxY - placementChangeYMovement)*pixelToBlock+offset, 1.0);
                    };
                }
            }
        }
        return box;
    }
}



