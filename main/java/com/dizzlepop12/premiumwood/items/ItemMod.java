package com.dizzlepop12.premiumwood.items;

import com.dizzlepop12.premiumwood.PremiumWood;
import com.dizzlepop12.premiumwood.init.PWItems;
import com.dizzlepop12.premiumwood.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMod extends Item implements IHasModel {
	
	public ItemMod(String n) {
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.MATERIALS);
		PWItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		PremiumWood.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
