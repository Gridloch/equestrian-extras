package net.equestrian.extras.block;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ModDoubleGate extends FenceGateBlock {

    public static final EnumProperty<DoorHinge> HINGE = Properties.DOOR_HINGE;
    protected static final VoxelShape Z_AXIS_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape X_AXIS_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);
    protected static final VoxelShape Z_AXIS_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
    protected static final VoxelShape X_AXIS_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);
    public static final BooleanProperty OUTER = BooleanProperty.of("outer");

    protected ModDoubleGate(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false).with(POWERED, false).with(IN_WALL, false).with(HINGE, DoorHinge.LEFT).with(OUTER, true));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        DoorHinge hinge = this.getHinge(ctx);
        Direction facing = ctx.getPlayerFacing();
        
        if (world.getBlockState(otherBlockPos(blockPos, this.getDefaultState().with(FACING, facing).with(HINGE, hinge))).canReplace(ctx)) {
            boolean bl = world.isReceivingRedstonePower(blockPos);
            return this.getDefaultState().with(FACING, facing).with(HINGE, hinge).with(POWERED, bl);
        }
        return null;

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(otherBlockPos(pos, state), state.with(OUTER, false), Block.NOTIFY_ALL);
    }

    private static BlockPos otherBlockPos(BlockPos blockPos, BlockState state) {
        // locates other half of gate
        DoorHinge hinge = state.get(HINGE);
        boolean isLeftHinge = hinge.equals(DoorHinge.LEFT);
        Direction facing = state.get(FACING);
        Boolean isOuter = state.get(OUTER);
        Boolean open = state.get(OPEN);

        if (Boolean.TRUE.equals(isOuter)) {
            if (facing.equals(Direction.NORTH)) {
                return Boolean.TRUE.equals(open) ? blockPos.north() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.east(): blockPos.west();
            }
            else if (facing.equals(Direction.SOUTH)) {
                return Boolean.TRUE.equals(open) ? blockPos.south() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.west(): blockPos.east();
            }
            else if (facing.equals(Direction.EAST)) {
                return Boolean.TRUE.equals(open) ? blockPos.east() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.south(): blockPos.north();
            }
            else {
                return Boolean.TRUE.equals(open) ? blockPos.west() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.north(): blockPos.south();
            }
        }
        else {
            if (facing.equals(Direction.NORTH)) {
                return Boolean.TRUE.equals(open) ? blockPos.south() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.west(): blockPos.east();
            }
            else if (facing.equals(Direction.SOUTH)) {
                return Boolean.TRUE.equals(open) ? blockPos.north() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.east(): blockPos.west();
            }
            else if (facing.equals(Direction.EAST)) {
                return Boolean.TRUE.equals(open) ? blockPos.west() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.north(): blockPos.south();
            }
            else {
                return Boolean.TRUE.equals(open) ? blockPos.east() : Boolean.TRUE.equals(isLeftHinge) ? blockPos.south(): blockPos.north();
            }
        }
    }

    private DoorHinge getHinge(ItemPlacementContext ctx) {
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
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            BlockPos blockPos = otherBlockPos(pos, state);
            BlockState blockState = world.getBlockState(blockPos);
            Boolean doubleBlockHalf = state.get(OUTER);
            if (Boolean.TRUE.equals(!doubleBlockHalf) && (blockState).isOf(state.getBlock()) && Boolean.TRUE.equals(blockState.get(OUTER))) {
                BlockState blockState2 = blockState.contains(Properties.WATERLOGGED) && Boolean.TRUE.equals(blockState.get(Properties.WATERLOGGED)) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                if (player.isCreative()) {
                    world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
                }
                else {
                    world.breakBlock(blockPos, true);
                }
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
            }
        }
        super.onBreak(world, pos, state, player);
    }

    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Boolean outer = state.get(OUTER);
        BlockPos blockPos = otherBlockPos(pos, state);
        BlockState blockState = world.getBlockState(blockPos);

        if (Boolean.TRUE.equals(!outer && (blockState).isOf(state.getBlock()))) {
            BlockState blockState2 = blockState.contains(Properties.WATERLOGGED) && Boolean.TRUE.equals(blockState.get(Properties.WATERLOGGED)) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
            world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
            world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Boolean outer = state.get(OUTER);
        Boolean posMatch = neighborPos.equals(otherBlockPos(pos, state));

        Direction.Axis axis = direction.getAxis();
        if (state.get(FACING).rotateYClockwise().getAxis() == axis && Boolean.TRUE.equals(outer)) {
            boolean bl = this.isWall(neighborState) || this.isWall(world.getBlockState(pos.offset(direction.getOpposite())));
            world.setBlockState(otherBlockPos(pos, state), state.with(IN_WALL, bl).with(OUTER, false), Block.FORCE_STATE);
            return state.with(IN_WALL, bl);
        }
        
        if (Boolean.TRUE.equals(posMatch)) {
            if (neighborState.isOf(this) && neighborState.get(OUTER).equals(!outer)) {
                return state.with(FACING, neighborState.get(FACING)).with(OPEN, neighborState.get(OPEN)).with(HINGE, neighborState.get(HINGE)).with(IN_WALL, neighborState.get(IN_WALL)).with(POWERED, neighborState.get(POWERED));
            }
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private boolean isWall(BlockState state) {
        return state.isIn(BlockTags.WALLS);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.get(OPEN)) {
            Direction direction = player.getHorizontalFacing();
            if (state.get(FACING) == direction.getOpposite()) {
                if (state.get(HINGE) == DoorHinge.LEFT) {
                    state = state.with(FACING, direction).with(HINGE, DoorHinge.RIGHT);
                }
                else {
                    state = state.with(FACING, direction).with(HINGE, DoorHinge.LEFT);
                }
            }
        }

        if (gateOpen(state, world, pos)) {
            state = state.cycle(OPEN);
            boolean direction = state.get(OPEN);
            playSound(world, pos, player, direction);
            world.emitGameEvent(player, direction ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }

        return ActionResult.success(world.isClient);
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
            world.syncWorldEvent(player, Boolean.TRUE.equals(direction) ? WorldEvents.FENCE_GATE_OPENS : WorldEvents.FENCE_GATE_CLOSES, pos, 0);
        }
    }


    private BlockPos movePos(BlockPos pos, BlockState state) {
        Direction dir = state.get(FACING);
        Boolean open = state.get(OPEN);
        Boolean lhinge = state.get(HINGE) == DoorHinge.LEFT; // true if left hinge, false if right hinge

        if (Boolean.TRUE.equals(dir == Direction.NORTH && (!open && !lhinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (open && !lhinge))) {
            return pos.east().north();
        }
        else if (Boolean.TRUE.equals(dir == Direction.NORTH && (open && lhinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (!open && lhinge))) {
            return pos.east().south();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (!open && !lhinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (open && !lhinge))) {
            return pos.south().east();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (open && lhinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (!open && lhinge))) {
            return pos.south().west();
        }
        else if (Boolean.TRUE.equals(dir == Direction.NORTH && (open && !lhinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (!open && !lhinge))) {
            return pos.west().south();
        }
        else if (Boolean.TRUE.equals(dir == Direction.SOUTH) || Boolean.TRUE.equals(dir == Direction.NORTH)) {
            return pos.west().north();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (open && !lhinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (!open && !lhinge))) {
            return pos.north().west();
        }
        else {
            return pos.north().east();
        }
    }


    private boolean gateOpen(BlockState state, World world, BlockPos pos) {

        Boolean outer = state.get(OUTER);
        Boolean opened = false;

        BlockPos initialInnerPos = Boolean.TRUE.equals(!outer) ? pos : otherBlockPos(pos, state);
        BlockPos outerPos = Boolean.TRUE.equals(outer) ? pos : otherBlockPos(pos, state);
        BlockPos movePos = this.movePos(initialInnerPos, state);
        Boolean power = world.isReceivingRedstonePower(outerPos);

        if (world.isAir(movePos)) {
            // update outer block
            world.setBlockState(outerPos, state.with(POWERED, power).with(OUTER, true).cycle(OPEN), Block.FORCE_STATE);
            // move inner block
            world.setBlockState(movePos, state.with(POWERED, power).with(OUTER, false).cycle(OPEN), Block.NOTIFY_LISTENERS);
            // remove block which was moved
            world.setBlockState(initialInnerPos, Blocks.AIR.getDefaultState(), Block.MOVED);
            // gate opened
            opened = true;
        }
        return opened;
    }


    // Redstone?
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        // if (world.isClient) {
        //     return;
        // }
        // boolean bl = world.isReceivingRedstonePower(pos);
        // if (Boolean.TRUE.equals(state.get(POWERED) != bl) && state.get(OUTER).equals(Boolean.TRUE)) {
        //     BlockPos initialInnerPos = Boolean.TRUE.equals(!state.get(OUTER)) ? pos : otherBlockPos(pos, state);
        //     if (gateOpen(state, world, pos)) {
        //         world.setBlockState(pos, (BlockState)((BlockState)state.with(POWERED, bl)).with(OPEN, bl), Block.NOTIFY_LISTENERS);
        //         // remove block which was moved
        //         world.setBlockState(initialInnerPos, Blocks.AIR.getDefaultState(), Block.MOVED);
        //     }
        //     if (Boolean.TRUE.equals(state.get(OPEN)) != bl) {
        //         world.syncWorldEvent(null, bl ? WorldEvents.FENCE_GATE_OPENS : WorldEvents.FENCE_GATE_CLOSES, pos, 0);
        //         world.emitGameEvent(bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        //     }
        // }
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, POWERED, IN_WALL, HINGE, OUTER);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }
}
