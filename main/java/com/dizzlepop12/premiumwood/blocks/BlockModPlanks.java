package com.dizzlepop12.premiumwood.blocks;

import com.dizzlepop12.premiumwood.PremiumWood;
import com.dizzlepop12.premiumwood.init.PWBlocks;
import com.dizzlepop12.premiumwood.init.PWItems;
import com.dizzlepop12.premiumwood.util.IHasModel;
import com.dizzlepop12.premiumwood.util.IMetaName;
import com.dizzlepop12.premiumwood.util.ItemBlockVarients;
import com.dizzlepop12.premiumwood.util.handlers.EnumHandler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockModPlanks extends Block implements IMetaName, IHasModel {
	
	public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);
	
	private String name;
	
	public BlockModPlanks(String name, Material m) {
		super(m);
		m = m.WOOD;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.IRONWOOD));
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.name = name;
		PWBlocks.BLOCKS.add(this);
		PWItems.ITEMS.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

	}

	public BlockModPlanks(String name, Material m, MapColor c) {
		super(m, c);
		m = m.LEAVES;
	}

	@Override
	public int damageDropped(IBlockState state)  {
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)  {
		for(EnumHandler.EnumType customblockplanks$enumtype : EnumHandler.EnumType.values()) {
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)  {
		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels() {
		for(int i = 0; i < EnumHandler.EnumType.values().length; i++) {
			PremiumWood.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "planks_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
