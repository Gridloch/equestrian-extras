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
    public static final BooleanProperty IS_HINGE = BooleanProperty.of("is_hinge");
    protected static final VoxelShape Z_AXIS_COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
    protected static final VoxelShape X_AXIS_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);

    protected ModDoubleGate(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(OPEN, false).with(POWERED, false).with(IN_WALL, false).with(HINGE, DoorHinge.LEFT).with(IS_HINGE, true));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        DoorHinge hinge = this.getHinge(ctx);
        Direction facing = ctx.getPlayerFacing();
        
        if (world.getBlockState(findPosOfOtherGateHalf(blockPos, this.getDefaultState().with(FACING, facing).with(HINGE, hinge))).canReplace(ctx)) {
            boolean bl = world.isReceivingRedstonePower(blockPos);
            return this.getDefaultState().with(FACING, facing).with(HINGE, hinge).with(POWERED, bl);
        }
        return null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(findPosOfOtherGateHalf(pos, state), state.with(IS_HINGE, false), Block.NOTIFY_ALL);
    }

    /**
     * Locates the other half of gate
     * @param blockPos The BlockPos of one half of a gate
     * @param state The BlockState of the block at blockPos
     * @return The blockPos of the other half of the gate
     */
    private static BlockPos findPosOfOtherGateHalf(BlockPos blockPos, BlockState state) {
        DoorHinge hinge = state.get(HINGE);
        boolean isLeftHinge = hinge.equals(DoorHinge.LEFT);
        Direction facing = state.get(FACING);
        Boolean isHinge = state.get(IS_HINGE);
        Boolean open = state.get(OPEN);

        if (Boolean.TRUE.equals(isHinge)) {
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
            BlockPos blockPos = findPosOfOtherGateHalf(pos, state);
            BlockState blockState = world.getBlockState(blockPos);
            Boolean doubleBlockHalf = state.get(IS_HINGE);
            if (Boolean.TRUE.equals(!doubleBlockHalf) && (blockState).isOf(state.getBlock()) && Boolean.TRUE.equals(blockState.get(IS_HINGE))) {
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

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Boolean isHingeBlock = state.get(IS_HINGE);
        Boolean posMatchOtherGateHalf = neighborPos.equals(findPosOfOtherGateHalf(pos, state));

        Direction.Axis axis = direction.getAxis();
        if (state.get(FACING).rotateYClockwise().getAxis() == axis && Boolean.TRUE.equals(isHingeBlock)) {
            boolean bl = this.isWall(neighborState) || this.isWall(world.getBlockState(pos.offset(direction.getOpposite())));
            world.setBlockState(findPosOfOtherGateHalf(pos, state), state.with(IN_WALL, bl).with(IS_HINGE, false), Block.FORCE_STATE);
            return state.with(IN_WALL, bl);
        }
        
        if (Boolean.TRUE.equals(posMatchOtherGateHalf)) {
            if (neighborState.isOf(this) && neighborState.get(IS_HINGE).equals(!isHingeBlock)) {
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
            boolean movementDirection = state.get(OPEN);
            playSound(world, pos, player, movementDirection);
            world.emitGameEvent(player, movementDirection ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        }

        return ActionResult.success(world.isClient);
    }


    private void playSound(World world, BlockPos pos, PlayerEntity player, Boolean isOpened) {
        if (this.material == Material.METAL) {
            if (!world.isClient) {
                world.playSound(
                        null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                        pos, // The position of where the sound will come from
                        Boolean.TRUE.equals(isOpened) ? EquestrianExtras.GATE_OPEN_EVENT : EquestrianExtras.GATE_CLOSE_EVENT, // The sound that will play
                        SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                        1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                        1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                );
            }
        }
        else {
            world.syncWorldEvent(player, Boolean.TRUE.equals(isOpened) ? WorldEvents.FENCE_GATE_OPENS : WorldEvents.FENCE_GATE_CLOSES, pos, 0);
        }
    }


    /**
     * Determines the new position of the swinging portion of the gate when the gate is opened or closed
     * @param initialPos is the initial position of the block being moved
     * @param state is the current block state
     * @return the position the block should be moved to
     */
    private BlockPos movePos(BlockPos initialPos, BlockState state) {
        Direction dir = state.get(FACING);
        Boolean open = state.get(OPEN);
        Boolean isLeftHinge = state.get(HINGE) == DoorHinge.LEFT; // true if left hinge, false if right hinge

        if (Boolean.TRUE.equals(dir == Direction.NORTH && (!open && !isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (open && !isLeftHinge))) {
            return initialPos.east().north();
        }
        else if (Boolean.TRUE.equals(dir == Direction.NORTH && (open && isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (!open && isLeftHinge))) {
            return initialPos.east().south();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (!open && !isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (open && !isLeftHinge))) {
            return initialPos.south().east();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (open && isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (!open && isLeftHinge))) {
            return initialPos.south().west();
        }
        else if (Boolean.TRUE.equals(dir == Direction.NORTH && (open && !isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.SOUTH && (!open && !isLeftHinge))) {
            return initialPos.west().south();
        }
        else if (Boolean.TRUE.equals(dir == Direction.SOUTH) || Boolean.TRUE.equals(dir == Direction.NORTH)) {
            return initialPos.west().north();
        }
        else if (Boolean.TRUE.equals(dir == Direction.EAST && (open && !isLeftHinge)) || Boolean.TRUE.equals(dir == Direction.WEST && (!open && !isLeftHinge))) {
            return initialPos.north().west();
        }
        else {
            return initialPos.north().east();
        }
    }


    /**
     * Attempts to open or close the gate and returns a boolean for the success status of the attempt
     * @param state The block state of one block in the gate
     * @param world The world
     * @param pos The block position of the gate block
     * @return True if the gate's open state was changed, false if it was not
     */
    private boolean gateOpen(BlockState state, World world, BlockPos pos) {
        Boolean isHinge = state.get(IS_HINGE);
        Boolean opened = false;

        BlockPos initialInnerPos = Boolean.TRUE.equals(!isHinge) ? pos : findPosOfOtherGateHalf(pos, state);
        BlockPos hingeBlockPos = Boolean.TRUE.equals(isHinge) ? pos : findPosOfOtherGateHalf(pos, state);
        BlockPos movePos = this.movePos(initialInnerPos, state);
        Boolean power = world.isReceivingRedstonePower(hingeBlockPos);

        if (world.isAir(movePos)) {
            // move swinging block
            world.setBlockState(movePos, state.with(POWERED, power).with(IS_HINGE, false).cycle(OPEN), Block.NOTIFY_ALL);
            // update hinge block
            world.setBlockState(hingeBlockPos, state.with(POWERED, power).with(IS_HINGE, true).cycle(OPEN), Block.NOTIFY_ALL);
            // remove block which was moved
            world.removeBlock(initialInnerPos, true);
            // gate opened
            opened = true;
        }
        return opened;
    }


    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean posPowered = world.isReceivingRedstonePower(pos);

            // If the block is on the hinge side and has its powered status updated, try to open/close the gate
            if ((state.get(POWERED) != posPowered) && state.get(IS_HINGE)) {
                world.setBlockState(pos, state.with(POWERED, posPowered));
                if (posPowered != state.get(OPEN)) {
                    if (gateOpen(state, world, pos)) {
                        boolean isOpened = state.cycle(OPEN).get(OPEN);
                        playSound(world, pos, null, isOpened);
                        world.emitGameEvent(posPowered ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                    }
                }
            }
         }
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
        builder.add(FACING, OPEN, POWERED, IN_WALL, HINGE, IS_HINGE);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }
}
