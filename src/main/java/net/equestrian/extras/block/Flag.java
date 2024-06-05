package net.equestrian.extras.block;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class Flag extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty CENTRED = BooleanProperty.of("centred");

    public Flag(Settings settings) {
        super(settings.nonOpaque());  
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(CENTRED, true));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING, WATERLOGGED, CENTRED);
	}

    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        
        return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite()).with(CENTRED, !blockView.getBlockState(blockPos.down()).isIn(EquestrianExtras.BlockTags.STANDARDS));
	}
    
    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
		Direction dir = state.get(FACING);
        if (Boolean.TRUE.equals(state.get(CENTRED))) {
            return switch (dir) {
                case NORTH -> VoxelShapes.cuboid(0.17f, 0.0f, 0.45f, 0.57f, 0.45f, 0.55f);
                case SOUTH -> VoxelShapes.cuboid(0.43f, 0.0f, 0.45f, 0.83f, 0.45f, 0.55f);
                case EAST -> VoxelShapes.cuboid(0.45f, 0.0f, 0.17f, 0.55f, 0.45f, 0.57f);
                case WEST -> VoxelShapes.cuboid(0.45f, 0.0f, 0.43f, 0.55f, 0.45f, 0.83f);
                default -> VoxelShapes.cuboid(0.55f, 0.0f, 0.45f, 0.94f, 0.45f, 0.55f);
            };
        }
		else {
            return switch (dir) {
                case NORTH -> VoxelShapes.cuboid(0.55f, 0.0f, 0.45f, 0.95f, 0.45f, 0.55f);
                case SOUTH -> VoxelShapes.cuboid(0.05f, 0.0f, 0.45f, 0.45f, 0.45f, 0.55f);
                case EAST -> VoxelShapes.cuboid(0.45f, 0.0f, 0.55f, 0.55f, 0.45f, 0.94f);
                case WEST -> VoxelShapes.cuboid(0.45f, 0.0f, 0.06f, 0.55f, 0.45f, 0.45f);
                default -> VoxelShapes.cuboid(0.55f, 0.0f, 0.45f, 0.94f, 0.45f, 0.55f);
            };
        }
	}

    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == Direction.DOWN) {
            return state.with(CENTRED, !neighborState.isIn(EquestrianExtras.BlockTags.STANDARDS));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

}
