package com.dizzlepop12.premiumwood.init;

import java.util.ArrayList;
import java.util.List;

import com.dizzlepop12.premiumwood.blocks.BlockMod;
import com.dizzlepop12.premiumwood.blocks.BlockModLeaves;
import com.dizzlepop12.premiumwood.blocks.BlockModLog;
import com.dizzlepop12.premiumwood.blocks.BlockModPlanks;
import com.dizzlepop12.premiumwood.blocks.BlockModSapling;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class PWBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	/**ironwood**/
	
	public static final Block LOGS = new BlockModLog("logs", Material.WOOD);
	public static final Block PLANKS = new BlockModPlanks("planks", Material.WOOD);
	public static final Block SAPLINGS = new BlockModSapling("saplings", Material.PLANTS);
	public static final Block LEAVES = new BlockModLeaves("leaves", Material.LEAVES);
	/*public static final Block IRONWOODLOG = new BlockMod("ironwoodlog", Material.WOOD);
	public static final Block IRONWOODPLANKS = new BlockModPlanks("ironwoodplanks", Material.WOOD);
	public static final Block IRONWOODLEAVES = new BlockModLeaves("ironwoodleaves", Material.LEAVES, PWBlocks.IRONWOODSAPLING);
	public static final Block IRONWOODSAPLING = new BlockModSapling("ironwoodsapling", Material.PLANTS); */
}
