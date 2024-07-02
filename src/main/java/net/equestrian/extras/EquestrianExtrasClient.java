package net.equestrian.extras;

import net.equestrian.extras.item.ConditionalItemsF;
import net.equestrian.extras.item.ConditionalItemsT;
import net.equestrian.extras.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.DyeableItem;

public class EquestrianExtrasClient implements ClientModInitializer  {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)(stack.getItem())).getColor(stack),
			ModItems.RIBBON, 
			ModItems.HARNESS, 
			ModItems.SADDLE_PAD,
			ModItems.JUMP_TACK,
			ModItems.RACE_TACK_1, 
			ModItems.RACE_TACK_2, 
			ModItems.RACE_TACK_3, 
			ModItems.RACE_TACK_4, 
			ModItems.RACE_TACK_5, 
			ModItems.RACE_TACK_6, 
			ModItems.RACE_TACK_7, 
			ModItems.RACE_TACK_8,
			ModItems.WESTERN_TACK,
			ModItems.BARDING_TACK,
			ModItems.HALTER,
			ModItems.BLANKET_PLAIN,
			ModItems.BLANKET_PLAIN_H,
			ModItems.BLANKET_BLACK_TOP,
			ModItems.BLANKET_BLACK_TOP_H,
			ModItems.BLANKET_BLACK_BOTTOM,
			ModItems.BLANKET_BLACK_BOTTOM_H,
			ModItems.BLANKET_DARK_TOP,
			ModItems.BLANKET_DARK_TOP_H,
			ModItems.BLANKET_DARK_BOTTOM,
			ModItems.BLANKET_DARK_BOTTOM_H
		);


		if (FabricLoader.getInstance().isModLoaded("dyed")) {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)(stack.getItem())).getColor(stack),
				ConditionalItemsT.RACE_TACK_W1, 
				ConditionalItemsT.RACE_TACK_W2, 
				ConditionalItemsT.RACE_TACK_W3, 
				ConditionalItemsT.RACE_TACK_W4, 
				ConditionalItemsT.RACE_TACK_W5, 
				ConditionalItemsT.RACE_TACK_W6, 
				ConditionalItemsT.RACE_TACK_W7, 
				ConditionalItemsT.RACE_TACK_W8,
				ConditionalItemsT.BLANKET_WHITE_TOP,
				ConditionalItemsT.BLANKET_WHITE_TOP_H,
				ConditionalItemsT.BLANKET_WHITE_BOTTOM,
				ConditionalItemsT.BLANKET_WHITE_BOTTOM_H
			);
		}
		else {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem)(stack.getItem())).getColor(stack),
				ConditionalItemsF.RACE_TACK_W1, 
				ConditionalItemsF.RACE_TACK_W2, 
				ConditionalItemsF.RACE_TACK_W3, 
				ConditionalItemsF.RACE_TACK_W4, 
				ConditionalItemsF.RACE_TACK_W5, 
				ConditionalItemsF.RACE_TACK_W6, 
				ConditionalItemsF.RACE_TACK_W7, 
				ConditionalItemsF.RACE_TACK_W8,
				ConditionalItemsF.BLANKET_WHITE_TOP,
				ConditionalItemsF.BLANKET_WHITE_TOP_H,
				ConditionalItemsF.BLANKET_WHITE_BOTTOM,
				ConditionalItemsF.BLANKET_WHITE_BOTTOM_H
			);
		}
	}
}
