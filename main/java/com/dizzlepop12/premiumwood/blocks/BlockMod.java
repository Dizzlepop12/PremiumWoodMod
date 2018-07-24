package com.dizzlepop12.premiumwood.blocks;

import com.dizzlepop12.premiumwood.PremiumWood;
import com.dizzlepop12.premiumwood.init.PWBlocks;
import com.dizzlepop12.premiumwood.init.PWItems;
import com.dizzlepop12.premiumwood.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockMod extends Block implements IHasModel {

	public BlockMod(String name, Material m) {
		super(m);
		m = m.WOOD;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.WOOD);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		PWBlocks.BLOCKS.add(this);
		PWItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

	}

	public BlockMod(String name, Material m, MapColor c) {
		super(m, c);
		m = m.LEAVES;
	}

	@Override
	public void registerModels() {
		PremiumWood.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
