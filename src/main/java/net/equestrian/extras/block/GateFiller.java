package net.equestrian.extras.block;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import org.jetbrains.annotations.Nullable;

import net.equestrian.extras.EquestrianExtras;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class GateFiller extends HorizontalFacingBlock implements Waterloggable {
    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");
    protected static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), directions -> {
        directions.put(Direction.UP, UP);
        directions.put(Direction.DOWN, DOWN);
    }));

    protected static final VoxelShape BOTTOM_SHAPE_N = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 10.0, 9.0);
    protected static final VoxelShape BOTTOM_SHAPE_E = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 10.0, 16.0);
    protected static final VoxelShape TOP_SHAPE_N = Block.createCuboidShape(0.0, 6.0, 7.0, 16.0, 16.0, 9.0);
    protected static final VoxelShape TOP_SHAPE_E = Block.createCuboidShape(7.0, 6.0, 0.0, 9.0, 16.0, 16.0);
    protected static final VoxelShape FULL_SHAPE_N = Block.createCuboidShape(0.0, 0.0, 7.0, 16.0, 16.0, 9.0);
    protected static final VoxelShape FULL_SHAPE_E = Block.createCuboidShape(7.0, 0.0, 0.0, 9.0, 16.0, 16.0);


    public GateFiller(AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(UP, false).with(DOWN, false));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, Properties.HORIZONTAL_FACING, UP, DOWN);
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
                    return FULL_SHAPE_N;
                } else {
                    return FULL_SHAPE_E;
                }
            }
            case TOP -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return TOP_SHAPE_N;
                } else {
                    return TOP_SHAPE_E;
                }
            }
            case BOTTOM -> {
                if (dir == Direction.NORTH || dir == Direction.SOUTH) {
                    return BOTTOM_SHAPE_N;
                } else {
                    return BOTTOM_SHAPE_E;
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
            return this.getDefaultState().with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, checkUp(ctx)).with(DOWN, checkDown(ctx));
        }

        BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, checkUp(ctx)).with(DOWN, checkDown(ctx));
        Direction direction = ctx.getSide();
        if (direction == Direction.DOWN || direction != Direction.UP && ctx.getHitPos().y - (double)blockPos.getY() > 0.5) {
            return blockState2.with(TYPE, SlabType.TOP).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite()).with(UP, checkUp(ctx)).with(DOWN, checkDown(ctx));
        }
        return blockState2;
    }

    public boolean checkDown(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        if (blockView.getBlockState(blockPos.down()).isIn(EquestrianExtras.BlockTags.GATEFILLER)) {
            return !blockView.getBlockState(blockPos.down()).get(TYPE).equals(SlabType.BOTTOM);
        }
        return false;
    }

    public boolean checkUp(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        if (blockView.getBlockState(blockPos.up()).isIn(EquestrianExtras.BlockTags.GATEFILLER)) {
            return !blockView.getBlockState(blockPos.up()).get(TYPE).equals(SlabType.TOP);
        }
        return false;
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
        
        if (neighborState.isIn(EquestrianExtras.BlockTags.GATEFILLER) && direction == Direction.DOWN) {
            return state.with(DOWN, !neighborState.get(TYPE).equals(SlabType.BOTTOM));

        }
        if (neighborState.isIn(EquestrianExtras.BlockTags.GATEFILLER) && direction == Direction.UP) {
            return state.with(UP, !neighborState.get(TYPE).equals(SlabType.TOP));
        }
        
        if (neighborState.isOf(Blocks.AIR) && direction.getAxis().getType() == Direction.Type.VERTICAL) {
            return state.with(FACING_PROPERTIES.get(direction), false);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}



