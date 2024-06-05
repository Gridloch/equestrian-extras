package net.equestrian.extras.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SaddleRack extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty SADDLE = BooleanProperty.of("saddle");
    public static final BooleanProperty ON_WALL = BooleanProperty.of("on_wall");

    public SaddleRack(Settings settings) {
        super(settings.nonOpaque());  
		this.setDefaultState(this.getDefaultState().with(SADDLE, false).with(ON_WALL, true));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    
    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING, SADDLE, ON_WALL);
	}


    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
		Direction dir = state.get(FACING);
        if (Boolean.FALSE.equals(state.get(ON_WALL))) {
            return switch (dir) {
                case EAST, WEST -> VoxelShapes.cuboid(0.0f, 0.0f, 0.1875f, 1.0f, 0.875f, 0.8125f);
                default -> VoxelShapes.cuboid(0.1875f, 0.0f, 0.0f, 0.8125f, 0.875f, 1.0f);
            };
        }
		else {
            return switch (dir) {
                case EAST, WEST -> VoxelShapes.cuboid(0.0f, 0.375f, 0.1875f, 1.0f, 0.875f, 0.8125f);
                default -> VoxelShapes.cuboid(0.1875f, 0.375f, 0.0f, 0.8125f, 0.875f, 1.0f);
            };
        }
	}


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();

        BlockState blockState2 = this.getDefaultState().with(ON_WALL, false).with(SADDLE, false).with(Properties.HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite());
        Direction direction = ctx.getSide();
        if (direction == Direction.DOWN || direction != Direction.UP && ctx.getHitPos().y - (double)blockPos.getY() > 0.1) {
            return blockState2.with(ON_WALL, true).with(Properties.HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite());
        }
        return blockState2;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);

        if (!player.getAbilities().allowModifyWorld) {
            return ActionResult.PASS;
        }
        else if (Boolean.TRUE.equals(state.get(SADDLE))) { // Checks if block already 'contains' a saddle
            ItemStack itemStack2 = new ItemStack(Items.SADDLE);
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, itemStack2);
            } else if (!player.giveItemStack(itemStack2)) {
                player.dropItem(itemStack2, false);
            }
            world.setBlockState(pos, state.with(SADDLE, false), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        else if (itemStack.isOf(Items.SADDLE) && !Boolean.TRUE.equals(state.get(SADDLE))) { // Checks if block is 'empty' and the player is holding a saddle
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            world.setBlockState(pos, state.with(SADDLE, true), Block.NOTIFY_ALL);
            return ActionResult.SUCCESS;
        }
        else {
            return ActionResult.PASS;
        }
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
