package net.equestrian.extras.block;

import java.util.List;
import java.util.Random;

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
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
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

    protected static final VoxelShape BOTTOM_SHAPE_N = Block.createCuboidShape(0.0, 3.0, 7.0, 16.0, 5.0, 9.0);
    protected static final VoxelShape BOTTOM_SHAPE_E = Block.createCuboidShape(7.0, 3.0, 0.0, 9.0, 5.0, 16.0);
    protected static final VoxelShape BOTTOM_SHAPE_DOWN_N = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 2.0, 9.0);
    protected static final VoxelShape BOTTOM_SHAPE_DOWN_E = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 2.0, 16.0);
    protected static final VoxelShape BOTTOM_SHAPE_UP_N = Block.createCuboidShape(0.0, 6.0, 7.0, 16.0, 8.0, 9.0);
    protected static final VoxelShape BOTTOM_SHAPE_UP_E = Block.createCuboidShape(7.0, 6.0, 0.0, 9.0, 8.0, 16.0);

    protected static final VoxelShape TOP_SHAPE_N = Block.createCuboidShape(0.0, 11.0, 7.0, 16.0, 13.0, 9.0);
    protected static final VoxelShape TOP_SHAPE_E = Block.createCuboidShape(7.0, 11.0, 0.0, 9.0, 13.0, 16.0);
    protected static final VoxelShape TOP_SHAPE_DOWN_N = Block.createCuboidShape(0.0, 8.0, 7.0, 16.0, 10.0, 9.0);
    protected static final VoxelShape TOP_SHAPE_DOWN_E = Block.createCuboidShape(7.0, 8.0, 0.0, 9.0, 10.0, 16.0);
    protected static final VoxelShape TOP_SHAPE_UP_N = Block.createCuboidShape(0.0, 14.0, 7.0, 16.0, 16.0, 9.0);
    protected static final VoxelShape TOP_SHAPE_UP_E = Block.createCuboidShape(7.0, 14.0, 0.0, 9.0, 16.0, 16.0);

    protected static final VoxelShape FULL_SHAPE_N = Block.createCuboidShape(0.0, 3.0, 7.0, 16.0, 13.0, 9.0);
    protected static final VoxelShape FULL_SHAPE_E = Block.createCuboidShape(7.0, 3.0, 0.0, 9.0, 13.0, 16.0);
    protected static final VoxelShape FULL_SHAPE_DOWN_N = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 10.0, 9.0);
    protected static final VoxelShape FULL_SHAPE_DOWN_E = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 10.0, 16.0);
    protected static final VoxelShape FULL_SHAPE_UP_N = Block.createCuboidShape(0.0, 6.0, 7.0, 16.0, 16.0, 9.0);
    protected static final VoxelShape FULL_SHAPE_UP_E = Block.createCuboidShape(7.0, 6.0, 0.0, 9.0, 16.0, 16.0);

    public Poles(AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
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
                        case 2 -> FULL_SHAPE_DOWN_N;
                        case 3 -> FULL_SHAPE_UP_N;
                        default -> FULL_SHAPE_N;
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> FULL_SHAPE_DOWN_E;
                        case 3 -> FULL_SHAPE_UP_E;
                        default -> FULL_SHAPE_E;
                    };
                }
            }
            case TOP -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> TOP_SHAPE_DOWN_N;
                        case 3 -> TOP_SHAPE_UP_N;
                        default -> TOP_SHAPE_N;
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> TOP_SHAPE_DOWN_E;
                        case 3 -> TOP_SHAPE_UP_E;
                        default -> TOP_SHAPE_E;
                    };
                }
            }
            case BOTTOM -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> BOTTOM_SHAPE_DOWN_N;
                        case 3 -> BOTTOM_SHAPE_UP_N;
                        default -> BOTTOM_SHAPE_N;
                    };
                } else {
                    return switch (state.get(PLACEMENT)) {
                        case 2 -> BOTTOM_SHAPE_DOWN_E;
                        case 3 -> BOTTOM_SHAPE_UP_E;
                        default -> BOTTOM_SHAPE_E;
                    };
                }
            }
        }
        return BOTTOM_SHAPE_N;
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
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
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
        if (world.isClient) {
            return;
        }

        if (entity.hasPlayerRider() && world.getBlockState(pos.up()).canPathfindThrough(world, pos.up(), NavigationType.LAND) && !((state.get(TYPE).equals(SlabType.BOTTOM) && state.get(PLACEMENT).equals(2)) && world.getBlockState(pos.down()).shouldSuffocate(world, pos))) {
            // If entity has a player rider, can be jumped over, and is not a ground pole, then
            // check that entity is in close contact with the poles
            List<LivingEntity> list;
            Box box = getBox(state).offset(pos);
            Boolean b1 = false;

            list = world.getNonSpectatingEntities(LivingEntity.class, box);

            if (!list.isEmpty()) {
                for (Entity listentity : list) {
                    if (!listentity.hasPlayerRider()) continue;
                    b1 = true;
                }
            }


            if (Boolean.TRUE.equals(b1)) {
                // If entity has a player rider and is in close contact with poles, play sound on first contact
                // and continue to check for it
                world.getBlockTickScheduler().schedule(new BlockPos(pos), this, 20);
                
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
                    if (x<config.poleBreakChance()) {
                        world.breakBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // checks for contact with poles
        Boolean b1 = false;
        List<LivingEntity> list;
        Box box = getBox(state).offset(pos);
        list = world.getNonSpectatingEntities(LivingEntity.class, box);

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


        switch (slabType) {
            case DOUBLE -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, 0.125, 0.375, 1.0, 0.813, 0.625);
                        // Should match shape (0.0, 3.0, 7.0, 16.0, 13.0, 9.0)
                        case 3 -> new Box(0.0, 0.312, 0.375, 1.0, 1.0, 0.625);
                        // Should match shape (0.0, 6.0, 7.0, 16.0, 16.0, 9.0)
                        default -> new Box(0.0, 0.0, 0.375, 1.0, 0.625, 0.625);
                        // Should match shape (0.0, 0.0, 7.0, 16.0, 10.0, 9.0)
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.375, 0.125, 0.0, 0.625, 0.813, 1.0);
                        // Should match shape (7.0, 3.0, 0.0, 9.0, 13.0, 16.0)
                        case 3 -> new Box(0.375, 0.312, 0.0, 0.625, 1.0, 1.0);
                        // Should match shape (7.0, 6.0, 0.0, 9.0, 16.0, 16.0)
                        default -> new Box(0.375, 0.0, 0.0, 0.625, 0.625, 1.0);
                        // Should match shape (7.0, 0.0, 0.0, 9.0, 10.0, 16.0)
                    };
                }
            }
            case TOP -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, 0.625, 0.375, 1.0, 0.813, 0.625);
                        // Should match shape (0.0, 11.0, 7.0, 16.0, 13.0, 9.0)
                        case 3 -> new Box(0.0, 0.875, 0.375, 1.0, 1.0, 0.625);
                        // Should match shape (0.0, 14.0, 7.0, 16.0, 16.0, 9.0)
                        default -> new Box(0.0, 0.437, 0.375, 1.0, 0.625, 0.625);
                        // Should match shape (0.0, 8.0, 7.0, 16.0, 10.0, 9.0)
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.375, 0.625, 0.0, 0.625, 0.813, 1.0);
                        // Should match shape (7.0, 11.0, 0.0, 9.0, 13.0, 16.0)
                        case 3 -> new Box(0.375, 0.875, 0.0, 0.625, 1.0, 1.0);
                        // Should match shape (7.0, 14.0, 0.0, 9.0, 16.0, 16.0)
                        default -> new Box(0.375, 0.437, 0.0, 0.625, 0.625, 1.0);
                        // Should match shape (7.0, 8.0, 0.0, 9.0, 10.0, 16.0)
                    };
                }
            }
            case BOTTOM -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.0, 0.125, 0.375, 1.0, 0.313, 0.625);
                        // Should match shape (0.0, 3.0, 7.0, 16.0, 5.0, 9.0)
                        case 3 -> new Box(0.0, 0.313, 0.375, 1.0, 0.5, 0.625);
                        // Should match shape (0.0, 6.0, 7.0, 16.0, 8.0, 9.0)
                        default -> new Box(0.0, 0.0, 0.375, 1.0, 0.125, 0.625);
                        // Should match shape (0.0, 0.0, 7.0, 16.0, 2.0, 9.0)
                    };
                } else {
                    box = switch (state.get(PLACEMENT)) {
                        case 1 -> new Box(0.375, 0.125, 0.0, 0.625, 0.313, 1.0);
                        // Should match shape (7.0, 11.0, 0.0, 9.0, 13.0, 16.0)
                        case 3 -> new Box(0.375, 0.313, 0.0, 0.625, 0.5, 1.0);
                        // Should match shape (7.0, 14.0, 0.0, 9.0, 16.0, 16.0)
                        default -> new Box(0.375, 0.0, 0.0, 0.625, 0.125, 1.0);
                        // Should match shape (7.0, 8.0, 0.0, 9.0, 10.0, 16.0)
                    };
                }
            }
        }
        return box;
	}
}



