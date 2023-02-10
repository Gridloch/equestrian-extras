package net.equestrian.extras;

import java.util.Random;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.equestrian.extras.block.ModBlocks;
import net.equestrian.extras.config.ModConfig;
import net.equestrian.extras.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EquestrianExtras implements ModInitializer {

	public static final String MOD_ID = "equestrianextras";
	public static final Random RANDOM = new Random();

	// Add new groups for modded items
		public static final ItemGroup ITEM_GROUP_STABLE = FabricItemGroupBuilder.build(
        new Identifier(EquestrianExtras.MOD_ID, "stable"), () -> new ItemStack(ModBlocks.OAK_DUTCH_DOOR)
        );
		public static final ItemGroup ITEM_GROUP_ARENA = FabricItemGroupBuilder.build(
        new Identifier(EquestrianExtras.MOD_ID, "arena"), () -> new ItemStack(ModBlocks.R_VRSTANDARD)
        );

	// Add new tags for modded items
	public class BlockTags {
		private BlockTags() {
			throw new IllegalStateException("Utility class");
		}
  		public static final Tag<Block> STANDARDS = TagFactory.BLOCK.create(new Identifier(MOD_ID, "standards"));
		public static final Tag<Block> PANELSTANDARDS = TagFactory.BLOCK.create(new Identifier(MOD_ID, "panel_standards"));
		public static final Tag<Block> VRSTANDARDS = TagFactory.BLOCK.create(new Identifier(MOD_ID, "vertical_rail_standards"));
		public static final Tag<Block> GATEFILLER = TagFactory.BLOCK.create(new Identifier(MOD_ID, "gate_fillers"));
		public static final Tag<Block> SHORT_FENCE = TagFactory.BLOCK.create(new Identifier(MOD_ID, "short_fences"));
		public static final Tag<Block> SLIDEDOORS = TagFactory.BLOCK.create(new Identifier(MOD_ID, "sliding_doors"));
		public static final Tag<Block> MBARRELS = TagFactory.BLOCK.create(new Identifier(MOD_ID, "metal_barrels"));
	}

	// Sliding door sounds
    public static final Identifier DOOR_OPEN_SOUND = new Identifier(MOD_ID, "door_opens");
    public static final SoundEvent DOOR_OPEN_EVENT = new SoundEvent(DOOR_OPEN_SOUND);
	
    public static final Identifier DOOR_CLOSE_SOUND = new Identifier(MOD_ID, "door_closes");
    public static final SoundEvent DOOR_CLOSE_EVENT = new SoundEvent(DOOR_CLOSE_SOUND);

	// Metal gate sounds
    public static final Identifier GATE_OPEN_SOUND = new Identifier(MOD_ID, "gate_opens");
    public static final SoundEvent GATE_OPEN_EVENT = new SoundEvent(GATE_OPEN_SOUND);
	
    public static final Identifier GATE_CLOSE_SOUND = new Identifier(MOD_ID, "gate_closes");
    public static final SoundEvent GATE_CLOSE_EVENT = new SoundEvent(GATE_CLOSE_SOUND);

	// Obstacle hit sounds
    public static final Identifier BARREL_HIT_SOUND = new Identifier(MOD_ID, "barrel_hit");
    public static final SoundEvent BARREL_HIT_EVENT = new SoundEvent(BARREL_HIT_SOUND);
	
    public static final Identifier POLE_HIT_SOUND = new Identifier(MOD_ID, "pole_hit");
    public static final SoundEvent POLE_HIT_EVENT = new SoundEvent(POLE_HIT_SOUND);	


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerItems();
		ModBlocks.registerBlocks();
		Registry.register(Registry.SOUND_EVENT, DOOR_OPEN_SOUND, DOOR_OPEN_EVENT);
		Registry.register(Registry.SOUND_EVENT, DOOR_CLOSE_SOUND, DOOR_CLOSE_EVENT);
		Registry.register(Registry.SOUND_EVENT, GATE_OPEN_SOUND, GATE_OPEN_EVENT);
		Registry.register(Registry.SOUND_EVENT, GATE_CLOSE_SOUND, GATE_CLOSE_EVENT);
		Registry.register(Registry.SOUND_EVENT, BARREL_HIT_SOUND, BARREL_HIT_EVENT);
		Registry.register(Registry.SOUND_EVENT, POLE_HIT_SOUND, POLE_HIT_EVENT);

		
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);

	}
}
