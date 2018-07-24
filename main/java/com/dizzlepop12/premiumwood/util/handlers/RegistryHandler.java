package com.dizzlepop12.premiumwood.util.handlers;

import com.dizzlepop12.premiumwood.init.PWBlocks;
import com.dizzlepop12.premiumwood.init.PWItems;
import com.dizzlepop12.premiumwood.util.IHasModel;
import com.dizzlepop12.premiumwood.world.gen.WorldGenPremiumTrees;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(PWItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(PWBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for(Item item : PWItems.ITEMS) {
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : PWBlocks.BLOCKS) {
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void otherRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenPremiumTrees(), 0);
	}
}
