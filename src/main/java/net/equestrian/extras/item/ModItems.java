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

public class ModItems {

    private ModItems() {
        throw new IllegalStateException("Utility class");
    }

    public static final Item RIBBON = registerItem("ribbon", 
        new DyeableHorseArmorItem(0, "ribbon",
            new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );


	public static final Item SADDLE_PAD = registerItem("saddle_pad", 
        new DyeableHorseArmorItem(0, "saddle_pad", 
            new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item JUMP_TACK = registerItem("jump_tack", 
    new DyeableHorseArmorItem(0, "jump_tack", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item WESTERN_TACK = registerItem("western_tack", 
    new DyeableHorseArmorItem(0, "western_tack", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item BARDING_TACK = registerItem("barding_tack", 
    new DyeableHorseArmorItem(2, "barding_tack", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_1 = registerItem("race_tack_1", 
    new DyeableHorseArmorItem(0, "race_tack_1", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_2 = registerItem("race_tack_2", 
    new DyeableHorseArmorItem(0, "race_tack_2", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_3 = registerItem("race_tack_3", 
    new DyeableHorseArmorItem(0, "race_tack_3", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_4 = registerItem("race_tack_4", 
    new DyeableHorseArmorItem(0, "race_tack_4", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_5 = registerItem("race_tack_5", 
    new DyeableHorseArmorItem(0, "race_tack_5", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_6 = registerItem("race_tack_6", 
    new DyeableHorseArmorItem(0, "race_tack_6", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_7 = registerItem("race_tack_7", 
    new DyeableHorseArmorItem(0, "race_tack_7", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );

    public static final Item RACE_TACK_8 = registerItem("race_tack_8", 
    new DyeableHorseArmorItem(0, "race_tack_8", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_ARENA
    );


    public static final Item HALTER = registerItem("halter", 
    new DyeableHorseArmorItem(0, "halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_PLAIN = registerItem("blanket_plain", 
    new DyeableHorseArmorItem(0, "blanket_plain", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_PLAIN_H = registerItem("blanket_plain_halter", 
    new DyeableHorseArmorItem(0, "blanket_plain_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_BLACK_TOP = registerItem("blanket_black_top", 
    new DyeableHorseArmorItem(0, "blanket_black_top", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_BLACK_TOP_H = registerItem("blanket_black_top_halter", 
    new DyeableHorseArmorItem(0, "blanket_black_top_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_BLACK_BOTTOM = registerItem("blanket_black_bottom", 
    new DyeableHorseArmorItem(0, "blanket_black_bottom", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_BLACK_BOTTOM_H = registerItem("blanket_black_bottom_halter", 
    new DyeableHorseArmorItem(0, "blanket_black_bottom_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_DARK_TOP = registerItem("blanket_dark_top", 
    new DyeableHorseArmorItem(0, "blanket_dark_top", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_DARK_TOP_H = registerItem("blanket_dark_top_halter", 
    new DyeableHorseArmorItem(0, "blanket_dark_top_halter", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_DARK_BOTTOM = registerItem("blanket_dark_bottom", 
    new DyeableHorseArmorItem(0, "blanket_dark_bottom", 
        new FabricItemSettings().maxCount(16)
        ),
        EquestrianExtras.ITEM_GROUP_STABLE
    );

    public static final Item BLANKET_DARK_BOTTOM_H = registerItem("blanket_dark_bottom_halter", 
    new DyeableHorseArmorItem(0, "blanket_dark_bottom_halter", 
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
        System.out.println("Registering items for "+ EquestrianExtras.MOD_ID);
    }
    
}
