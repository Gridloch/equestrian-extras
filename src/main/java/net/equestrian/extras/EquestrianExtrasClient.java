package net.equestrian.extras;

import net.equestrian.extras.block.ModBlocks;
import net.equestrian.extras.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.DyeableItem;

public class EquestrianExtrasClient implements ClientModInitializer  {

	@Override
	public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack),
			ModItems.RIBBON, 
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

	
		for (int i = 0; i < ModBlocks.transparentBlocks.size(); i++) {
			BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.transparentBlocks.get(i), RenderLayer.getCutout());
		}
	}
}
