package net.equestrian.extras.item;

import net.equestrian.extras.EquestrianExtras;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

// Registers white tack items and displays them in the creative menu
public class ConditionalItemsT {

    private ConditionalItemsT() {
        throw new IllegalStateException("Utility class");
    }

    
    public static final Item RACE_TACK_W1 = registerItem("race_tack_white_1", 
    new DyeableHorseArmorItem(0, "race_tack_white_1", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W2 = registerItem("race_tack_white_2", 
    new DyeableHorseArmorItem(0, "race_tack_white_2", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W3 = registerItem("race_tack_white_3", 
    new DyeableHorseArmorItem(0, "race_tack_white_3", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W4 = registerItem("race_tack_white_4", 
    new DyeableHorseArmorItem(0, "race_tack_white_4", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W5 = registerItem("race_tack_white_5", 
    new DyeableHorseArmorItem(0, "race_tack_white_5", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W6 = registerItem("race_tack_white_6", 
    new DyeableHorseArmorItem(0, "race_tack_white_6", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W7 = registerItem("race_tack_white_7", 
    new DyeableHorseArmorItem(0, "race_tack_white_7", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item RACE_TACK_W8 = registerItem("race_tack_white_8", 
    new DyeableHorseArmorItem(0, "race_tack_white_8", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_ARENA).maxCount(16)
        )
    );

    public static final Item BLANKET_WHITE_TOP = registerItem("blanket_white_top", 
    new DyeableHorseArmorItem(0, "blanket_white_top", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_STABLE).maxCount(16)
        )
    );

    public static final Item BLANKET_WHITE_TOP_H = registerItem("blanket_white_top_halter", 
    new DyeableHorseArmorItem(0, "blanket_white_top_halter", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_STABLE).maxCount(16)
        )
    );

    public static final Item BLANKET_WHITE_BOTTOM = registerItem("blanket_white_bottom", 
    new DyeableHorseArmorItem(0, "blanket_white_bottom", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_STABLE).maxCount(16)
        )
    );

    public static final Item BLANKET_WHITE_BOTTOM_H = registerItem("blanket_white_bottom_halter", 
    new DyeableHorseArmorItem(0, "blanket_white_bottom_halter", 
        new FabricItemSettings().group(EquestrianExtras.ITEM_GROUP_STABLE).maxCount(16)
        )
    );
    

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(EquestrianExtras.MOD_ID, name), item);
    }


    public static void registerItems() {
        System.out.println("'Dyed' mod detected - showing additional items for "+ EquestrianExtras.MOD_ID);
    }
    
}
