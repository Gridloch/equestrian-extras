package net.equestrian.extras.block;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SlidingDoor extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty OPEN = Properties.OPEN;

    public static final BooleanProperty OPENING = BooleanProperty.of("opening");
    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 3.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 13.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(13.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 3.0, 16.0, 16.0);

    protected SlidingDoor(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false).with(OPENING, false).with(HINGE, DoorHinge.LEFT).with(POWERED, false).with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case EAST -> {
                return EAST_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
            }
        }
        return NORTH_SHAPE;
    }


    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (Boolean.TRUE.equals(doorSlide(state, world, pos))) {
            state = state.cycle(OPEN);
            this.playDoorSlideSound(Boolean.TRUE.equals(state.get(OPEN)), world, pos);
            world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
        return ActionResult.success(world.isClient);
    }


    private void playDoorSlideSound(Boolean opening, World world, BlockPos pos) {
        if (!world.isClient) {
            world.playSound(
                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                    pos, // The position of where the sound will come from
                    Boolean.TRUE.equals(opening) ? EquestrianExtras.DOOR_OPEN_EVENT : EquestrianExtras.DOOR_CLOSE_EVENT, // The sound that will play
                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                    1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
            );
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        if (blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx)) {
            boolean bl = world.isReceivingRedstonePower(blockPos) || world.isReceivingRedstonePower(blockPos.up());
            return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(HINGE, this.getSlideDirection(ctx)).with(POWERED, bl).with(HALF, DoubleBlockHalf.LOWER);
        }
        return null;
    }

    private DoorHinge getSlideDirection(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction direction = ctx.getPlayerFacing();
        BlockPos blockPos2 = blockPos.up();
        Direction direction2 = direction.rotateYCounterclockwise();
        BlockPos blockPos3 = blockPos.offset(direction2);
        BlockState blockState = blockView.getBlockState(blockPos3);
        BlockPos blockPos4 = blockPos2.offset(direction2);
        BlockState blockState2 = blockView.getBlockState(blockPos4);
        Direction direction3 = direction.rotateYClockwise();
        BlockPos blockPos5 = blockPos.offset(direction3);
        BlockState blockState3 = blockView.getBlockState(blockPos5);
        BlockPos blockPos6 = blockPos2.offset(direction3);
        BlockState blockState4 = blockView.getBlockState(blockPos6);
        int i = (blockState.isFullCube(blockView, blockPos3) ? -1 : 0) + (blockState2.isFullCube(blockView, blockPos4) ? -1 : 0) + (blockState3.isFullCube(blockView, blockPos5) ? 1 : 0) + (blockState4.isFullCube(blockView, blockPos6) ? 1 : 0);
        boolean bl = blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
        boolean bl2 = blockState3.isOf(this) && blockState3.get(HALF) == DoubleBlockHalf.LOWER;
        if (bl && !bl2 || i > 0) {
            return DoorHinge.RIGHT;
        }
        if (bl2 && !bl || i < 0) {
            return DoorHinge.LEFT;
        }
        int j = direction.getOffsetX();
        int k = direction.getOffsetZ();
        Vec3d vec3d = ctx.getHitPos();
        double d = vec3d.x - (double)blockPos.getX();
        double e = vec3d.z - (double)blockPos.getZ();
        return j < 0 && e < 0.5 || j > 0 && e > 0.5 || k < 0 && d > 0.5 || k > 0 && d < 0.5 ? DoorHinge.RIGHT : DoorHinge.LEFT;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            BlockPos movePos = movePos(pos, state, 1);

            boolean power = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
            if (Boolean.TRUE.equals(state.get(OPEN))) {
                power = world.isReceivingRedstonePower(movePos) || world.isReceivingRedstonePower(movePos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
            }

            if ((state.get(POWERED) != power) && Boolean.TRUE.equals(doorSlide(state, world, pos))) {
                state = state.cycle(OPEN);
                this.playDoorSlideSound(Boolean.TRUE.equals(state.get(OPEN)), world, pos);
                world.emitGameEvent(null, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }
        }
    }

    /**
     *
     * @param pos the block position of a door block
     * @param state the block state for the block at pos
     * @param i the distance moved in blocks
     * @return the position that the door should be moved to
     */
    private BlockPos movePos(BlockPos pos, BlockState state, Integer i) {
        Direction dir = state.get(FACING);
        Boolean open = state.get(OPEN);
        Boolean lhinge = state.get(HINGE) == DoorHinge.LEFT; // true if left hinge, false if right hinge
        if (Boolean.TRUE.equals(dir == Direction.NORTH && ((open && lhinge) || (!open && !lhinge))) || Boolean.TRUE.equals(dir == Direction.SOUTH && ((!open && lhinge) || (open && !lhinge)))) {
            return pos.east(i);
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && ((open && lhinge) || (!open && !lhinge))) || Boolean.TRUE.equals(dir == Direction.WEST && ((!open && lhinge) || (open && !lhinge)))) {
            return pos.south(i);
        }
        else if (Boolean.TRUE.equals(dir == Direction.SOUTH) || Boolean.TRUE.equals(dir == Direction.NORTH)) {
            return pos.west(i);
        }
        else {
            return pos.north(i);
        }
    }

    /**
     * First determines which direction the door should slide
     * then determines if the door can slide (i.e. is not obstructed).
     * If the door can slide, both top and bottom blocks are moved one block to the side.
     * @param state BlockState of one of the door blocks
     * @param world The world
     * @param pos Position of the door block whose state was used
     * @return True if the door's open state was changed, false if it was not
     */
    private boolean doorSlide(BlockState state, World world, BlockPos pos) {
        // TODO This sort of works now but probably shouldn't produce block breaking particles every time
        //  you open/close a door...

        Boolean slide = false;
        boolean isUpperBlock = state.get(HALF) == DoubleBlockHalf.UPPER;
        BlockPos movepos = this.movePos(pos, state, 1);

        boolean power = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (Boolean.TRUE.equals(state.get(OPEN))) {
            power = world.isReceivingRedstonePower(movepos) || world.isReceivingRedstonePower(movepos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        }

        if (world.isAir(movepos) && world.isAir(isUpperBlock ? movepos.down() : movepos.up())) {
            // move lower block
            world.setBlockState(isUpperBlock ? movepos.down() : movepos, state.with(POWERED, power).with(HALF, DoubleBlockHalf.LOWER).cycle(OPEN), Block.NOTIFY_ALL);
            // move upper black
            world.setBlockState(isUpperBlock ? movepos : movepos.up(), state.with(POWERED, power).with(HALF, DoubleBlockHalf.UPPER).cycle(OPEN), Block.NOTIFY_ALL);
            // flag old (moved) blocks for removal (in getStateForNeighborUpdate)
            world.setBlockState(pos, state.with(OPENING, true), Block.NOTIFY_ALL); // Tag current block as opening
            world.setBlockState(isUpperBlock ? pos.down() : pos.up(), state.with(OPENING, true), Block.NOTIFY_ALL); // Tag other block as opening
            // door slid
            slide = true;
        }
        return slide;
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (state.get(OPENING)) {
            // Should prevent block breaking particles from appearing when the door is being opened
            world.removeBlock(pos, true);
        }
        if (direction.getAxis() == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP)) {
            if (neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf) {
                return state.with(FACING, neighborState.get(FACING)).with(OPEN, neighborState.get(OPEN)).with(HINGE, neighborState.get(HINGE)).with(POWERED, neighborState.get(POWERED));
            }
            return Blocks.AIR.getDefaultState();
        }
        if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            DoubleBlockHalf doubleBlockHalf = (DoubleBlockHalf)state.get(HALF);
            if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
                BlockPos blockPos = pos.down();
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
                    BlockState blockState2 = blockState.contains(Properties.WATERLOGGED) && (Boolean)blockState.get(Properties.WATERLOGGED) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                    world.setBlockState(blockPos, blockState2, 35);
                    world.syncWorldEvent(player, 2001, blockPos, Block.getRawIdFromState(blockState));
                }
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    public boolean isOpen(BlockState state) {
        return state.get(OPEN);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return blockState.isSideSolidFullSquare(world, blockPos, Direction.UP);
        }
        return blockState.isOf(this);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        if (mirror == BlockMirror.NONE) {
            return state;
        }
        return state.rotate(mirror.getRotation(state.get(FACING))).cycle(HINGE);
    }

    @Override
    public long getRenderingSeed(BlockState state, BlockPos pos) {
        return MathHelper.hashCode(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, OPENING, HINGE, POWERED);
    }
}
