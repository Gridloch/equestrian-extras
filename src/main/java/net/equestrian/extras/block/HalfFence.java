package net.equestrian.extras.block;


import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class HalfFence extends FenceBlock {
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");
    private final VoxelShape[] cullingShapes;
    private final VoxelShape[] tallcullingShapes;
    private final VoxelShape[] postlesscullingShapes;

    public HalfFence(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, true).with(WATERLOGGED, false));
        this.cullingShapes = this.createShapes(2.0f, 1.0f, 8.0f, 4.0f, 7.0f);
        this.tallcullingShapes = this.createShapes(2.0f, 1.0f, 16.0f, 0.0f, 0.0f);
        this.postlesscullingShapes = this.createShapes(2.0f, 1.0f, 0.0f, 4.0f, 7.0f);
    }


    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        return getCollisionShape(state, view, pos, ctx);
	}

    @Override
	public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
        if (Boolean.FALSE.equals(state.get(DOWN))) {
            return this.postlesscullingShapes[this.getShapeIndex(state)];
        }
        if (Boolean.TRUE.equals(state.get(UP))) {
            return this.tallcullingShapes[this.getShapeIndex(state)];
        }
        return this.cullingShapes[this.getShapeIndex(state)];
	}


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, UP, DOWN, WATERLOGGED);
    }


    
    @Override
    public boolean canConnect(BlockState state, boolean neighborIsFullSquare, Direction dir) {
        Block block = state.getBlock();
        boolean bl = this.canConnectToHalfFence(state);
        boolean bl2 = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, dir);
        return !Block.cannotConnect(state) && neighborIsFullSquare || bl || bl2;
    }

    private boolean canConnectToHalfFence(BlockState state) {
        boolean bl = false;
        if (state.isIn(EquestrianExtras.BlockTags.SHORT_FENCE)) {
            bl = !state.get(UP);
        }
        return bl;
    }



    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockState blockState2 = blockView.getBlockState(blockPos2);
        BlockState blockState3 = blockView.getBlockState(blockPos3);
        BlockState blockState4 = blockView.getBlockState(blockPos4);
        BlockState blockState5 = blockView.getBlockState(blockPos5);

        Boolean north = this.canConnect(blockState2, blockState2.isSideSolidFullSquare(blockView, blockPos2, Direction.SOUTH), Direction.SOUTH);
        Boolean east = this.canConnect(blockState3, blockState3.isSideSolidFullSquare(blockView, blockPos3, Direction.WEST), Direction.WEST);
        Boolean south = this.canConnect(blockState4, blockState4.isSideSolidFullSquare(blockView, blockPos4, Direction.NORTH), Direction.NORTH);
        Boolean west = this.canConnect(blockState5, blockState5.isSideSolidFullSquare(blockView, blockPos5, Direction.EAST), Direction.EAST);
        Boolean up = blockView.getBlockState(blockPos.up()).isIn(EquestrianExtras.BlockTags.SHORT_FENCE);
        boolean fullside = (north && south) || (east && west);
        boolean onsolid = !blockView.getBlockState(blockPos.down()).isAir();

        return super.getPlacementState(ctx).with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west).with(UP, up).with(DOWN, !(fullside && !onsolid)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction.getAxis().getType() == Direction.Type.HORIZONTAL) {
            // Horizontal changes
            state = state.with(FACING_PROPERTIES.get(direction), this.canConnect(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction.getOpposite()), direction.getOpposite()));
        }
        if (direction == Direction.UP) {
            state = state.with(UP, neighborState.isIn(EquestrianExtras.BlockTags.SHORT_FENCE));
        }
        else {
            boolean fullside = state.get(NORTH) && state.get(SOUTH) || state.get(EAST) && state.get(WEST);
            boolean onsolid = world.getBlockState(pos.down()).getMaterial().isSolid();
            state = state.with(DOWN, (!fullside || onsolid));
        }

        return state;
    }
}
