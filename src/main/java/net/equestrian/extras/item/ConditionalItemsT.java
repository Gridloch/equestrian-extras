package net.equestrian.extras.item;

import net.equestrian.extras.EquestrianExtras;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.DyeableHorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// Registers white tack items and displays them in the creative menu
public class ConditionalItemsT {

    private ConditionalItemsT() {
        throw new IllegalStateException("Utility class");
    }

    
    public static final Item RACE_TACK_W1 = registerItem("race_tack_white_1", 
    new DyeableHorseArmorItem(0, "race_tack_white_1", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W2 = registerItem("race_tack_white_2", 
    new DyeableHorseArmorItem(0, "race_tack_white_2", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W3 = registerItem("race_tack_white_3", 
    new DyeableHorseArmorItem(0, "race_tack_white_3", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W4 = registerItem("race_tack_white_4", 
    new DyeableHorseArmorItem(0, "race_tack_white_4", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W5 = registerItem("race_tack_white_5", 
    new DyeableHorseArmorItem(0, "race_tack_white_5", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W6 = registerItem("race_tack_white_6", 
    new DyeableHorseArmorItem(0, "race_tack_white_6", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W7 = registerItem("race_tack_white_7", 
    new DyeableHorseArmorItem(0, "race_tack_white_7", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_W8 = registerItem("race_tack_white_8", 
    new DyeableHorseArmorItem(0, "race_tack_white_8", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item BLANKET_WHITE_TOP = registerItem("blanket_white_top", 
    new DyeableHorseArmorItem(0, "blanket_white_top", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_WHITE_TOP_H = registerItem("blanket_white_top_halter", 
    new DyeableHorseArmorItem(0, "blanket_white_top_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_WHITE_BOTTOM = registerItem("blanket_white_bottom", 
    new DyeableHorseArmorItem(0, "blanket_white_bottom", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_WHITE_BOTTOM_H = registerItem("blanket_white_bottom_halter", 
    new DyeableHorseArmorItem(0, "blanket_white_bottom_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );
    

    private static Item registerItem(String name, Item item, ItemGroup group) {
        Item actualItem = Registry.register(Registries.ITEM, new Identifier(EquestrianExtras.MOD_ID, name), item);
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> {
            content.add(item);
        });  
        return actualItem;
    }


    public static void registerItems() {
        System.out.println("'Dyed' mod detected - showing additional items for "+ EquestrianExtras.MOD_ID);
    }
    
}
