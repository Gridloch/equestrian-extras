package net.equestrian.extras.block;

import java.util.LinkedList;
import java.util.List;

import net.equestrian.extras.EquestrianExtras;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WoodType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;


@SuppressWarnings("unused")
public class ModBlocks {
    public static final List<Block> transparentBlocks = new LinkedList<>();

    private ModBlocks() {
        throw new IllegalStateException("Utility class");
    }



    public static final Block OAK_SADDLE_RACK = registerFlammableTransparencyBlock("oak_saddle_rack",
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block SPRUCE_SADDLE_RACK = registerFlammableTransparencyBlock("spruce_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block BIRCH_SADDLE_RACK = registerFlammableTransparencyBlock("birch_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block JUNGLE_SADDLE_RACK = registerFlammableTransparencyBlock("jungle_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block ACACIA_SADDLE_RACK = registerFlammableTransparencyBlock("acacia_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block DARK_OAK_SADDLE_RACK = registerFlammableTransparencyBlock("dark_oak_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block CRIMSON_SADDLE_RACK = registerTransparencyBlock("crimson_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_SADDLE_RACK = registerTransparencyBlock("warped_saddle_rack", 
        new SaddleRack(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );



    public static final Block OAK_DUTCH_DOOR = registerTransparencyBlock("oak_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block SPRUCE_DUTCH_DOOR = registerTransparencyBlock("spruce_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.SPRUCE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block BIRCH_DUTCH_DOOR = registerTransparencyBlock("birch_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.BIRCH),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block JUNGLE_DUTCH_DOOR = registerTransparencyBlock("jungle_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.JUNGLE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block ACACIA_DUTCH_DOOR = registerTransparencyBlock("acacia_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.ACACIA),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block DARK_OAK_DUTCH_DOOR = registerTransparencyBlock("dark_oak_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.DARK_OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block CRIMSON_DUTCH_DOOR = registerTransparencyBlock("crimson_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.CRIMSON),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_DUTCH_DOOR = registerTransparencyBlock("warped_dutch_door", 
        new DutchDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.WARPED),
        EquestrianExtras.ITEM_GROUP_STABLE
    );


    public static final Block OAK_V_FRONT_DOOR = registerTransparencyBlock("oak_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block SPRUCE_V_FRONT_DOOR = registerTransparencyBlock("spruce_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.SPRUCE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block BIRCH_V_FRONT_DOOR = registerTransparencyBlock("birch_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.BIRCH),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block JUNGLE_V_FRONT_DOOR = registerTransparencyBlock("jungle_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.JUNGLE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block ACACIA_V_FRONT_DOOR = registerTransparencyBlock("acacia_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.ACACIA),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block DARK_OAK_V_FRONT_DOOR = registerTransparencyBlock("dark_oak_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.DARK_OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block CRIMSON_V_FRONT_DOOR = registerTransparencyBlock("crimson_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.CRIMSON),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_V_FRONT_DOOR = registerTransparencyBlock("warped_v_front_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.WARPED),
        EquestrianExtras.ITEM_GROUP_STABLE
    );


    public static final Block OAK_SLIDE_DOOR = registerTransparencyBlock("oak_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block SPRUCE_SLIDE_DOOR = registerTransparencyBlock("spruce_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.SPRUCE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block BIRCH_SLIDE_DOOR = registerTransparencyBlock("birch_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.BIRCH),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block JUNGLE_SLIDE_DOOR = registerTransparencyBlock("jungle_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.JUNGLE),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block ACACIA_SLIDE_DOOR = registerTransparencyBlock("acacia_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.ACACIA),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block DARK_OAK_SLIDE_DOOR = registerTransparencyBlock("dark_oak_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.DARK_OAK),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block CRIMSON_SLIDE_DOOR = registerTransparencyBlock("crimson_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.CRIMSON),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_SLIDE_DOOR = registerTransparencyBlock("warped_slide_door", 
        new SlidingDoor(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(3.0f), BlockSetType.WARPED),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    
    public static final Block OAK_PANEL = registerFlammableTransparencyBlock("oak_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block SPRUCE_PANEL = registerFlammableTransparencyBlock("spruce_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block BIRCH_PANEL = registerFlammableTransparencyBlock("birch_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block JUNGLE_PANEL = registerFlammableTransparencyBlock("jungle_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block ACACIA_PANEL = registerFlammableTransparencyBlock("acacia_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block DARK_OAK_PANEL = registerFlammableTransparencyBlock("dark_oak_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block CRIMSON_PANEL = registerFlammableTransparencyBlock("crimson_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block WARPED_PANEL = registerFlammableTransparencyBlock("warped_panel", 
        new StablePanel(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        30, 20
    );

    public static final Block IRON_BARS_PANEL = registerTransparencyBlock("iron_bars_panel", 
        new StablePanel(FabricBlockSettings.of(Material.METAL).resistance(6.0f).hardness(5.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block GOLD_BARS_PANEL = registerTransparencyBlock("gold_bars_panel", 
        new StablePanel(FabricBlockSettings.of(Material.METAL).resistance(6.0f).hardness(5.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block CRIMSON_BARS_PANEL = registerTransparencyBlock("crimson_bars_panel", 
        new StablePanel(FabricBlockSettings.of(Material.METAL).resistance(6.0f).hardness(5.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_BARS_PANEL = registerTransparencyBlock("warped_bars_panel", 
        new StablePanel(FabricBlockSettings.of(Material.METAL).resistance(6.0f).hardness(5.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );
    

    public static final Block STRAW_BEDDING = registerFlammableBlock("straw_bedding", 
        new Bedding(FabricBlockSettings.of(Material.SOLID_ORGANIC).resistance(0.2f).hardness(0.2f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        60, 20
    );

    public static final Block THATCH = registerFlammableBlock("thatch", 
        new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).resistance(0.5f).hardness(0.5f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        60, 20
    );

    public static final Block THATCH_STAIRS = registerFlammableBlock("thatch_stairs", 
        new ModStairs(ModBlocks.THATCH.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC).resistance(0.5f).hardness(0.5f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        60, 20
    );

    public static final Block THATCH_SLAB = registerFlammableBlock("thatch_slab", 
        new SlabBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).resistance(0.5f).hardness(0.5f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        60, 20
    );


    public static final Block W_FENCE = registerFlammableBlock("white_fence", 
        new FenceBlock(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );
    
    public static final Block W_GATE = registerFlammableBlock("white_fence_gate", 
        new FenceGateBlock(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), WoodType.OAK),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block OAK_GATE_D = registerFlammableBlock("oak_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block SPRUCE_GATE_D = registerFlammableBlock("spruce_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block BIRCH_GATE_D = registerFlammableBlock("birch_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block JUNGLE_GATE_D = registerFlammableBlock("jungle_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block ACACIA_GATE_D = registerFlammableBlock("acacia_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block DARK_OAK_GATE_D = registerFlammableBlock("dark_oak_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block CRIMSON_GATE_D = registerBlock("crimson_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WARPED_GATE_D = registerBlock("warped_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block WHITE_GATE_D = registerFlammableBlock("white_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE,
        5, 20
    );

    public static final Block BR_IRON_GATE = registerBlock("brown_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block R_IRON_GATE = registerBlock("red_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block O_IRON_GATE = registerBlock("orange_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block Y_IRON_GATE = registerBlock("yellow_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block L_IRON_GATE = registerBlock("lime_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block GR_IRON_GATE = registerBlock("green_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block C_IRON_GATE = registerBlock("cyan_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block LB_IRON_GATE = registerBlock("light_blue_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block B_IRON_GATE = registerBlock("blue_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block P_IRON_GATE = registerBlock("purple_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block M_IRON_GATE = registerBlock("magenta_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block PK_IRON_GATE = registerBlock("pink_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block W_IRON_GATE = registerBlock("white_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block LG_IRON_GATE = registerBlock("light_gray_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block G_IRON_GATE = registerBlock("gray_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block K_IRON_GATE = registerBlock("black_iron_gate", 
        new ModGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block BR_IRON_GATE_D = registerBlock("brown_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block R_IRON_GATE_D = registerBlock("red_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block O_IRON_GATE_D = registerBlock("orange_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block Y_IRON_GATE_D = registerBlock("yellow_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block L_IRON_GATE_D = registerBlock("lime_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block GR_IRON_GATE_D = registerBlock("green_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block C_IRON_GATE_D = registerBlock("cyan_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block LB_IRON_GATE_D = registerBlock("light_blue_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block B_IRON_GATE_D = registerBlock("blue_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block P_IRON_GATE_D = registerBlock("purple_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block M_IRON_GATE_D = registerBlock("magenta_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block PK_IRON_GATE_D = registerBlock("pink_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block W_IRON_GATE_D = registerBlock("white_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block LG_IRON_GATE_D = registerBlock("light_gray_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block G_IRON_GATE_D = registerBlock("gray_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Block K_IRON_GATE_D = registerBlock("black_iron_gate_double", 
        new ModDoubleGate(FabricBlockSettings.of(Material.METAL).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_STABLE
    );


    public static final Block OAK_SHORT_FENCE = registerFlammableBlock("oak_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block SPRUCE_SHORT_FENCE = registerFlammableBlock("spruce_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block BIRCH_SHORT_FENCE = registerFlammableBlock("birch_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block JUNGLE_SHORT_FENCE = registerFlammableBlock("jungle_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block ACACIA_SHORT_FENCE = registerFlammableBlock("acacia_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block DARK_OAK_SHORT_FENCE = registerFlammableBlock("dark_oak_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );

    public static final Block CRIMSON_SHORT_FENCE = registerBlock("crimson_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block WARPED_SHORT_FENCE = registerBlock("warped_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block W_SHORT_FENCE = registerFlammableBlock("white_short_fence", 
        new HalfFence(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        5, 40
    );


    public static final Block LETTER_A = registerFlammableBlock("dressage_lettera", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_K = registerFlammableBlock("dressage_letterk", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_E = registerFlammableBlock("dressage_lettere", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_H = registerFlammableBlock("dressage_letterh", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_C = registerFlammableBlock("dressage_letterc", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_M = registerFlammableBlock("dressage_letterm", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_B = registerFlammableBlock("dressage_letterb", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LETTER_F = registerFlammableBlock("dressage_letterf", 
        new DressageLetter(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );


    public static final Block BR_VRSTANDARD = registerFlammableBlock("brown_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_VRSTANDARD = registerFlammableBlock("red_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_VRSTANDARD = registerFlammableBlock("orange_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_VRSTANDARD = registerFlammableBlock("yellow_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_VRSTANDARD = registerFlammableBlock("lime_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_VRSTANDARD = registerFlammableBlock("green_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_VRSTANDARD = registerFlammableBlock("cyan_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_VRSTANDARD = registerFlammableBlock("light_blue_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_VRSTANDARD = registerFlammableBlock("blue_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_VRSTANDARD = registerFlammableBlock("purple_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_VRSTANDARD = registerFlammableBlock("magenta_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_VRSTANDARD = registerFlammableBlock("pink_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_VRSTANDARD = registerFlammableBlock("white_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_VRSTANDARD = registerFlammableBlock("light_gray_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_VRSTANDARD = registerFlammableBlock("gray_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_VRSTANDARD = registerFlammableBlock("black_vertical_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );



    public static final Block BR_HRSTANDARD = registerFlammableBlock("brown_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_HRSTANDARD = registerFlammableBlock("red_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_HRSTANDARD = registerFlammableBlock("orange_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_HRSTANDARD = registerFlammableBlock("yellow_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_HRSTANDARD = registerFlammableBlock("lime_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_HRSTANDARD = registerFlammableBlock("green_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_HRSTANDARD = registerFlammableBlock("cyan_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_HRSTANDARD = registerFlammableBlock("light_blue_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_HRSTANDARD = registerFlammableBlock("blue_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_HRSTANDARD = registerFlammableBlock("purple_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_HRSTANDARD = registerFlammableBlock("magenta_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_HRSTANDARD = registerFlammableBlock("pink_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_HRSTANDARD = registerFlammableBlock("white_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_HRSTANDARD = registerFlammableBlock("light_gray_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_HRSTANDARD = registerFlammableBlock("gray_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_HRSTANDARD = registerFlammableBlock("black_horizontal_rail_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );



    public static final Block BR_PSTANDARD = registerFlammableBlock("brown_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_PSTANDARD = registerFlammableBlock("red_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_PSTANDARD = registerFlammableBlock("orange_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_PSTANDARD = registerFlammableBlock("yellow_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_PSTANDARD = registerFlammableBlock("lime_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_PSTANDARD = registerFlammableBlock("green_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_PSTANDARD = registerFlammableBlock("cyan_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_PSTANDARD = registerFlammableBlock("light_blue_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_PSTANDARD = registerFlammableBlock("blue_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_PSTANDARD = registerFlammableBlock("purple_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_PSTANDARD = registerFlammableBlock("magenta_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_PSTANDARD = registerFlammableBlock("pink_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_PSTANDARD = registerFlammableBlock("white_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_PSTANDARD = registerFlammableBlock("light_gray_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_PSTANDARD = registerFlammableBlock("gray_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_PSTANDARD = registerFlammableBlock("black_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );
    

    public static final Block BR_FPSTANDARD = registerFlammableBlock("brown_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_FPSTANDARD = registerFlammableBlock("red_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_FPSTANDARD = registerFlammableBlock("orange_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_FPSTANDARD = registerFlammableBlock("yellow_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_FPSTANDARD = registerFlammableBlock("lime_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_FPSTANDARD = registerFlammableBlock("green_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_FPSTANDARD = registerFlammableBlock("cyan_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_FPSTANDARD = registerFlammableBlock("light_blue_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_FPSTANDARD = registerFlammableBlock("blue_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_FPSTANDARD = registerFlammableBlock("purple_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_FPSTANDARD = registerFlammableBlock("magenta_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_FPSTANDARD = registerFlammableBlock("pink_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_FPSTANDARD = registerFlammableBlock("white_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_FPSTANDARD = registerFlammableBlock("light_gray_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_FPSTANDARD = registerFlammableBlock("gray_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_FPSTANDARD = registerFlammableBlock("black_fancy_panel_wing_standard", 
        new JumpStandard(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );


    public static final Block BROWN_POLES = registerFlammableBlock("brown_poles",
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block RED_POLES = registerFlammableBlock("red_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block ORANGE_POLES = registerFlammableBlock("orange_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block YELLOW_POLES = registerFlammableBlock("yellow_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LIME_POLES = registerFlammableBlock("lime_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GREEN_POLES = registerFlammableBlock("green_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block CYAN_POLES = registerFlammableBlock("cyan_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LIGHT_BLUE_POLES = registerFlammableBlock("light_blue_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block BLUE_POLES = registerFlammableBlock("blue_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PURPLE_POLES = registerFlammableBlock("purple_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block MAGENTA_POLES = registerFlammableBlock("magenta_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PINK_POLES = registerFlammableBlock("pink_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block WHITE_POLES = registerFlammableBlock("white_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LIGHT_GRAY_POLES = registerFlammableBlock("light_gray_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GRAY_POLES = registerFlammableBlock("gray_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block BLACK_POLES = registerFlammableBlock("black_poles", 
        new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );


    public static final Block BR_LADDERGATE = registerFlammableBlock("brown_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_LADDERGATE = registerFlammableBlock("red_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_LADDERGATE = registerFlammableBlock("orange_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_LADDERGATE = registerFlammableBlock("yellow_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_LADDERGATE = registerFlammableBlock("lime_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_LADDERGATE = registerFlammableBlock("green_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_LADDERGATE = registerFlammableBlock("cyan_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_LADDERGATE = registerFlammableBlock("light_blue_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_LADDERGATE = registerFlammableBlock("blue_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_LADDERGATE = registerFlammableBlock("purple_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_LADDERGATE = registerFlammableBlock("magenta_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_LADDERGATE = registerFlammableBlock("pink_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_LADDERGATE = registerFlammableBlock("white_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_LADDERGATE = registerFlammableBlock("light_gray_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_LADDERGATE = registerFlammableBlock("gray_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_LADDERGATE = registerFlammableBlock("black_ladder_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );


    public static final Block BR_PANELGATE = registerFlammableBlock("brown_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block R_PANELGATE = registerFlammableBlock("red_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block O_PANELGATE = registerFlammableBlock("orange_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block Y_PANELGATE = registerFlammableBlock("yellow_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block L_PANELGATE = registerFlammableBlock("lime_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block GR_PANELGATE = registerFlammableBlock("green_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block C_PANELGATE = registerFlammableBlock("cyan_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LB_PANELGATE = registerFlammableBlock("light_blue_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block B_PANELGATE = registerFlammableBlock("blue_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block P_PANELGATE = registerFlammableBlock("purple_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block M_PANELGATE = registerFlammableBlock("magenta_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block PK_PANELGATE = registerFlammableBlock("pink_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f) ),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block W_PANELGATE = registerFlammableBlock("white_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block LG_PANELGATE = registerFlammableBlock("light_gray_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block G_PANELGATE = registerFlammableBlock("gray_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );

    public static final Block K_PANELGATE = registerFlammableBlock("black_panel_gate_filler", 
        new GateFiller(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f)),
        EquestrianExtras.ITEM_GROUP_ARENA,
        30, 20
    );
    

    public static final Block BROWN_FLAG = registerBlock("brown_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block RED_FLAG = registerBlock("red_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block ORANGE_FLAG = registerBlock("orange_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block YELLOW_FLAG = registerBlock("yellow_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block LIME_FLAG = registerBlock("lime_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block GREEN_FLAG = registerBlock("green_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block CYAN_FLAG = registerBlock("cyan_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block LIGHT_BLUE_FLAG = registerBlock("light_blue_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block BLUE_FLAG = registerBlock("blue_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block PURPLE_FLAG = registerBlock("purple_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block MAGENTA_FLAG = registerBlock("magenta_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block PINK_FLAG = registerBlock("pink_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block WHITE_FLAG = registerBlock("white_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block LIGHT_GRAY_FLAG = registerBlock("light_gray_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block GRAY_FLAG = registerBlock("gray_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );
    
    public static final Block BLACK_FLAG = registerBlock("black_flag", 
        new Flag(FabricBlockSettings.of(Material.WOOD).resistance(1.0f).hardness(1.0f).collidable(false)) {},
        EquestrianExtras.ITEM_GROUP_ARENA
    );



    public static final Block OAK_POLES = registerFlammableBlock("oak_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block SPRUCE_POLES = registerFlammableBlock("spruce_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block BIRCH_POLES = registerFlammableBlock("birch_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block JUNGLE_POLES = registerFlammableBlock("jungle_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block ACACIA_POLES = registerFlammableBlock("acacia_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block DARK_OAK_POLES = registerFlammableBlock("dark_oak_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block CRIMSON_POLES = registerFlammableBlock("crimson_poles",
            new Poles(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block WARPED_POLES = registerFlammableBlock("warped_poles",
            new Poles(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );


    public static final Block STRIPPED_OAK_POLES = registerFlammableBlock("stripped_oak_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_SPRUCE_POLES = registerFlammableBlock("stripped_spruce_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_BIRCH_POLES = registerFlammableBlock("stripped_birch_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_JUNGLE_POLES = registerFlammableBlock("stripped_jungle_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_ACACIA_POLES = registerFlammableBlock("stripped_acacia_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_DARK_OAK_POLES = registerFlammableBlock("stripped_dark_oak_poles",
            new Poles(FabricBlockSettings.of(Material.WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_CRIMSON_POLES = registerFlammableBlock("stripped_crimson_poles",
            new Poles(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );

    public static final Block STRIPPED_WARPED_POLES = registerFlammableBlock("stripped_warped_poles",
            new Poles(FabricBlockSettings.of(Material.NETHER_WOOD).resistance(3.0f).hardness(2.0f), true),
            EquestrianExtras.ITEM_GROUP_ARENA,
            30, 20
    );



    public static final Block BROWN_BARREL = registerBlock("brown_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block RED_BARREL = registerBlock("red_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block ORANGE_BARREL = registerBlock("orange_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block YELLOW_BARREL = registerBlock("yellow_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block LIME_BARREL = registerBlock("lime_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block GREEN_BARREL = registerBlock("green_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block CYAN_BARREL = registerBlock("cyan_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block LIGHT_BLUE_BARREL = registerBlock("light_blue_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block BLUE_BARREL = registerBlock("blue_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block PURPLE_BARREL = registerBlock("purple_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block MAGENTA_BARREL = registerBlock("magenta_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block PINK_BARREL = registerBlock("pink_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block WHITE_BARREL = registerBlock("white_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block LIGHT_GRAY_BARREL = registerBlock("light_gray_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block GRAY_BARREL = registerBlock("gray_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Block BLACK_BARREL = registerBlock("black_barrel", 
        new MetalBarrel(FabricBlockSettings.of(Material.METAL).resistance(2.5f).hardness(2.5f)),
        EquestrianExtras.ITEM_GROUP_ARENA
    );




    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registries.BLOCK, new Identifier(EquestrianExtras.MOD_ID, name), block);
    }

    private static Block registerTransparencyBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        return Registry.register(Registries.BLOCK, new Identifier(EquestrianExtras.MOD_ID, name), block);
    }


    private static Block registerFlammableBlock(String name, Block block, ItemGroup group, Integer burn, Integer spread) {
        registerBlockItem(name, block, group);
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
        return Registry.register(Registries.BLOCK, new Identifier(EquestrianExtras.MOD_ID, name), block);
    }

    private static Block registerFlammableTransparencyBlock(String name, Block block, ItemGroup group, Integer burn, Integer spread) {
        registerBlockItem(name, block, group);
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        return Registry.register(Registries.BLOCK, new Identifier(EquestrianExtras.MOD_ID, name), block);
    }
    

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {  
        Item item = Registry.register(Registries.ITEM, new Identifier(EquestrianExtras.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            content.add(item);
        });  
        return item;
    }
    

    public static void registerBlocks() {
        System.out.println("Registering blocks for "+ EquestrianExtras.MOD_ID);
    }
}
