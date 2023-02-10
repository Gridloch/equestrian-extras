package net.equestrian.extras.block;


import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;



public class VRStandard extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty UP = IntProperty.of("up", 0, 2);
    public static final IntProperty DOWN = IntProperty.of("down", 0, 2);
    protected static final Map<Direction, IntProperty> FACING_PROPERTIES = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), directions -> {
        directions.put(Direction.UP, UP);
        directions.put(Direction.DOWN, DOWN);
    }));

    public VRStandard(Settings settings) {
        super(settings.nonOpaque());  
		this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, false)).with(UP, 0)).with(DOWN, 0)));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }


    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING, WATERLOGGED, UP, DOWN);
	}
 
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
		Direction dir = state.get(FACING);
		switch(dir) {
			case NORTH:
				return VoxelShapes.cuboid(0.0f, 0.0f, 0.375f, 1.0f, 1.0f, 0.625f);
			case SOUTH:
				return VoxelShapes.cuboid(0.0f, 0.0f, 0.375f, 1.0f, 1.0f, 0.625f);
			case EAST:
				return VoxelShapes.cuboid(0.375f, 0.0f, 0.0f, 0.625f, 1.0f, 1.0f);
			case WEST:
				return VoxelShapes.cuboid(0.375f, 0.0f, 0.0f, 0.625f, 1.0f, 1.0f);
			default:
				return VoxelShapes.fullCube();
		}
	}
 
    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        if (blockView.getBlockState(blockPos.down()).isOf(this)) {
            if (blockView.getBlockState(blockPos.up()).isOf(this)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 2)).with(DOWN, 2)));
            }
            else if (blockView.getBlockState(blockPos.up()).isIn(EquestrianExtras.BlockTags.STANDARDS)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 1)).with(DOWN, 2)));
            }
            else
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 0)).with(DOWN, 2)));
        }
        else if (blockView.getBlockState(blockPos.down()).isIn(EquestrianExtras.BlockTags.STANDARDS)) {
            if (blockView.getBlockState(blockPos.up()).isOf(this)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 2)).with(DOWN, 1)));
            }
            else if (blockView.getBlockState(blockPos.up()).isIn(EquestrianExtras.BlockTags.STANDARDS)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 1)).with(DOWN, 1)));
            }
            else
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 0)).with(DOWN, 1)));
        }
        else
            if (blockView.getBlockState(blockPos.up()).isOf(this)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 2)).with(DOWN, 0)));
            }
            else if (blockView.getBlockState(blockPos.up()).isIn(EquestrianExtras.BlockTags.STANDARDS)) {
                return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 1)).with(DOWN, 0)));
            }
            else
		        return (BlockState)((BlockState)((BlockState)((BlockState)this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, 0)).with(DOWN, 0)));
	}
    //blockView.getBlockState(blockPos.up()).isOf(this) AND blockView.getBlockState(blockPos.down()).isOf(this) are boolean

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED).booleanValue()) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (neighborState.isIn(EquestrianExtras.BlockTags.VRSTANDARDS) && direction.getAxis().getType() == Direction.Type.VERTICAL) {
            return (BlockState)state.with(FACING_PROPERTIES.get(direction), 2);
        }
        else if (neighborState.isIn(EquestrianExtras.BlockTags.STANDARDS) && direction.getAxis().getType() == Direction.Type.VERTICAL) {
            return (BlockState)state.with(FACING_PROPERTIES.get(direction), 1);
        }
        if (neighborState.isOf(Blocks.AIR) && direction.getAxis().getType() == Direction.Type.VERTICAL) {
            return (BlockState)state.with(FACING_PROPERTIES.get(direction), 0);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

	@Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
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
}