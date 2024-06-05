package net.equestrian.extras.block;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ModGate extends FenceGateBlock {

    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    protected static final VoxelShape Z_AXIS_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
    protected static final VoxelShape X_AXIS_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);

    protected ModGate(Settings settings) {
        super(settings.nonOpaque(), WoodType.OAK);
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false).with(POWERED, false).with(IN_WALL, false).with(HINGE, DoorHinge.LEFT));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();

        boolean bl = world.isReceivingRedstonePower(blockPos) || world.isReceivingRedstonePower(blockPos.up());
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection()).with(HINGE, this.getHinge(ctx)).with(POWERED, bl);
    }

    private DoorHinge getHinge(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction direction = ctx.getPlayerLookDirection();
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
        if (i > 0) {
            return DoorHinge.RIGHT;
        }
        if (i < 0) {
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(IN_WALL)) {
            if (state.get(OPEN)) {
                Direction dir = state.get(FACING);
                return switch (dir) {
                    case SOUTH ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(12.0, 0.0, 7.0, 16.0, 16.0, 23.0) : Block.createCuboidShape(0.0, 0.0, 7.0, 4.0, 16.0, 23.0);
                    case EAST ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(7.0, 0.0, 0.0, 23.0, 16.0, 4.0) : Block.createCuboidShape(7.0, 0.0, 12.0, 23.0, 16.0, 16.0);
                    case WEST ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(-7.0, 0.0, 12.0, 9.0, 16.0, 16.0) : Block.createCuboidShape(-7.0, 0.0, 0.0, 9.0, 16.0, 4.0);
                    default ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(0.0, 0.0, -7.0, 4.0, 16.0, 9.0) : Block.createCuboidShape(12.0, 0.0, -7.0, 16.0, 16.0, 9.0);
                };
            }
            else {
                return state.get(FACING).getAxis() == Direction.Axis.X ? Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 16.0, 16.0) : Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
            }
        }
        else {
            if (state.get(OPEN)) {
                Direction dir = state.get(FACING);
                return switch (dir) {
                    case SOUTH ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(12.0, 0.3, 7.0, 16.0, 19.0, 23.0) : Block.createCuboidShape(0.0, 0.3, 7.0, 4.0, 19.0, 23.0);
                    case EAST ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(7.0, 0.3, 0.0, 23.0, 19.0, 4.0) : Block.createCuboidShape(7.0, 0.3, 12.0, 23.0, 19.0, 16.0);
                    case WEST ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(-7.0, 0.3, 12.0, 9.0, 19.0, 16.0) : Block.createCuboidShape(-7.0, 0.3, 0.0, 9.0, 19.0, 4.0);
                    default ->
                            state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(0.0, 0.3, -7.0, 4.0, 19.0, 9.0) : Block.createCuboidShape(12.0, 0.3, -7.0, 16.0, 19.0, 9.0);
                };
            }
            else {
                return state.get(FACING).getAxis() == Direction.Axis.X ? Block.createCuboidShape(6.0, 0.3, 0.0, 10.0, 19.0, 16.0) : Block.createCuboidShape(0.0, 0.3, 6.0, 16.0, 19.0, 10.0);
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(OPEN)) {
            Direction dir = state.get(FACING);
            return switch (dir) {
                case SOUTH ->
                        state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(12.0, 0.0, 8.0, 16.0, 24.0, 24.0) : Block.createCuboidShape(0.0, 0.0, 8.0, 4.0, 24.0, 24.0);
                case EAST ->
                        state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(8.0, 0.0, 0.0, 24.0, 24.0, 4.0) : Block.createCuboidShape(8.0, 0.0, 12.0, 24.0, 24.0, 16.0);
                case WEST ->
                        state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(-8.0, 0.0, 12.0, 8.0, 24.0, 16.0) : Block.createCuboidShape(-8.0, 0.0, 0.0, 8.0, 24.0, 4.0);
                default ->
                        state.get(HINGE) == DoorHinge.LEFT ? Block.createCuboidShape(0.0, 0.0, -8.0, 4.0, 24.0, 8.0) : Block.createCuboidShape(12.0, 0.0, -8.0, 16.0, 24.0, 8.0);
            };
        }
        else {
            return state.get(FACING).getAxis() == Direction.Axis.Z ? Z_AXIS_COLLISION_SHAPE : X_AXIS_COLLISION_SHAPE;
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(OPEN)) {
            state = state.with(OPEN, false);
            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
        } else {
            Direction direction = player.getHorizontalFacing();
            if (state.get(FACING) == direction.getOpposite()) {
                if (state.get(HINGE) == DoorHinge.LEFT) {
                    state = state.with(FACING, direction).with(HINGE, DoorHinge.RIGHT);
                }
                else {
                    state = state.with(FACING, direction).with(HINGE, DoorHinge.LEFT);
                }
            }
            state = state.with(OPEN, true);
            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
        }
        boolean direction = state.get(OPEN);
        playSound(world, pos, player, direction);
        world.emitGameEvent(player, direction ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (world.isClient) {
            return;
        }
        boolean bl = world.isReceivingRedstonePower(pos);
        if (Boolean.TRUE.equals(state.get(POWERED)) != bl) {
            world.setBlockState(pos, state.with(POWERED, bl).with(OPEN, bl), Block.NOTIFY_LISTENERS);
            if (Boolean.TRUE.equals(state.get(OPEN) != bl) && this.material == Material.METAL) {
                world.playSound(
                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                    pos, // The position of where the sound will come from
                    Boolean.FALSE.equals(state.get(OPEN)) ? EquestrianExtras.GATE_OPEN_EVENT : EquestrianExtras.GATE_CLOSE_EVENT, // The sound that will play
                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                    1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                );
                world.emitGameEvent(bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos, GameEvent.Emitter.of(state));
            }
        }
    }

    private void playSound(World world, BlockPos pos, PlayerEntity player, Boolean direction) {
        if (this.material == Material.METAL) {
            if (!world.isClient) {
                world.playSound(
                    null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                    pos, // The position of where the sound will come from
                    Boolean.TRUE.equals(direction) ? EquestrianExtras.GATE_OPEN_EVENT : EquestrianExtras.GATE_CLOSE_EVENT, // The sound that will play
                    SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                    1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                    1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                );
            }
        }
        else {
            world.emitGameEvent(player, Boolean.TRUE.equals(direction) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, POWERED, IN_WALL, HINGE);
    }
}
