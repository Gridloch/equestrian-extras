package net.equestrian.extras.block;

import java.util.List;
import java.util.Random;

import me.shedaniel.autoconfig.AutoConfig;
import net.equestrian.extras.EquestrianExtras;
import net.equestrian.extras.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MetalBarrel extends SlabBlock {

    public static final EnumProperty<SlabType> TYPE = Properties.SLAB_TYPE;
    public static final BooleanProperty HIT = BooleanProperty.of("hit");
    protected static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 8.0, 16.0);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape DOUBLE_SHAPE = Block.createCuboidShape(0.0, -1.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape BOTTOM_SHAPE2 = Block.createCuboidShape(0.1, -1.0, 0.1, 15.9, 8.0, 15.9);
    protected static final VoxelShape TOP_SHAPE2 = Block.createCuboidShape(0.1, 8.0, 0.1, 15.9, 16.0, 15.9);
    protected static final VoxelShape DOUBLE_SHAPE2 = Block.createCuboidShape(0.1, -1.0, 0.1, 15.9, 16.0, 15.9);

    public MetalBarrel(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(HIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, HIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        switch (slabType) {
            case DOUBLE -> {
                return DOUBLE_SHAPE;
            }
            case TOP -> {
                return TOP_SHAPE;
            }
            default -> {
                return BOTTOM_SHAPE;
            }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        switch (slabType) {
            case DOUBLE -> {
                return DOUBLE_SHAPE2;
            }
            case TOP -> {
                return TOP_SHAPE2;
            }
            default -> {
                return BOTTOM_SHAPE2;
            }
        }
    }
    

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) {
            return;
        }

        if (entity.hasPlayerRider()&& !world.getBlockState(pos.up()).isIn(EquestrianExtras.BlockTags.MBARRELS)) {
            // If entity has a player rider and the block below is not a barrel,
            // check that entity is in close contact with barrel
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
                // If entity has a player rider and is in close contact with berrel, play sound on first contact
                // and continue to check for it
                world.createAndScheduleBlockTick(new BlockPos(pos), this, 20);
                
                if (state.get(HIT).equals(false)) {
                    world.playSound(
                        null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                        pos, // The position of where the sound will come from
                        EquestrianExtras.BARREL_HIT_EVENT, // The sound that will play
                        SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                        1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                        1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                    );
                    world.setBlockState(pos, state.with(HIT, true), Block.NOTIFY_LISTENERS);
                    
                    // Chance to break block when hit
                    int x = EquestrianExtras.RANDOM.nextInt(100); // Generates random integers 0 to 99  
                    ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
                    if (x<config.barrelBreakChance()) {
                        world.breakBlock(pos, true);
                    }
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // checks for contact with barrel
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

        switch (slabType) {
            case DOUBLE -> box = new Box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            case TOP -> box = new Box(0.0, 0.5, 0.0, 1.0, 1.0, 1.0);
            case BOTTOM -> box = new Box(0.0, 0.0, 0.0, 1.0, 0.5, 1.0);
        }
        return box;
	}
}
