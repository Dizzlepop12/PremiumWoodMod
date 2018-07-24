package com.dizzlepop12.premiumwood.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.dizzlepop12.premiumwood.PremiumWood;
import com.dizzlepop12.premiumwood.init.PWBlocks;
import com.dizzlepop12.premiumwood.init.PWItems;
import com.dizzlepop12.premiumwood.util.IHasModel;
import com.dizzlepop12.premiumwood.util.IMetaName;
import com.dizzlepop12.premiumwood.util.ItemBlockVarients;
import com.dizzlepop12.premiumwood.util.handlers.EnumHandler;
import com.dizzlepop12.premiumwood.util.handlers.EnumHandler.EnumType;
import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockModLeaves extends BlockLeaves implements IMetaName, IHasModel {

	public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class, new Predicate<EnumHandler.EnumType>()
	{
		public boolean apply(@Nullable EnumHandler.EnumType apply)
		{
			return apply.getMeta() < 2;
		}
	});
	
	private String name;
	
    public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
    public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");
    
    public Block sapling;
    
    protected boolean leavesFancy;
    int[] surroundings;
    
    public BlockModLeaves(String name, Material m) {
		super();
		m = m.LEAVES;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setLightOpacity(1);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		PWBlocks.BLOCKS.add(this);
		PWItems.ITEMS.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));

	}

	public BlockModLeaves(String name, Material m, MapColor c) {
		super();
		m = m.LEAVES;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setSoundType(SoundType.PLANT);
		this.setLightOpacity(1);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		PWBlocks.BLOCKS.add(this);
		PWItems.ITEMS.add(new ItemBlockVarients(this).setRegistryName(this.getRegistryName()));
	}
	
	/* public BlockModLeaves(String name, Material m, Block sapling) {
		super(name, m);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.IRONWOOD).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
		this.sapling = sapling;
	} */
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta % 2));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		int i = ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
		
		if(!((Boolean)state.getValue(DECAYABLE)).booleanValue())
		{
			i |= 2;
		}
		
		if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue())
		{
			i |= 4;
		}
		
		return i;
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		for(EnumHandler.EnumType customblockplanks$enumtype : EnumHandler.EnumType.values())
		{
			items.add(new ItemStack(this, 1, customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) 
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta());
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public String getSpecialName(ItemStack stack) 
	{
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {return;}
	
	@Override
	protected int getSaplingDropChance(IBlockState state) {return 30;}
	
	@Override
	public net.minecraft.block.BlockPlanks.EnumType getWoodType(int meta) {return null;}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMeta()));
	}
	
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(PWBlocks.SAPLINGS);
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT,DECAYABLE,CHECK_DECAY});
	}
	
	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,  EnumFacing side) {
		return true;
	}
	
	@Override
	public void registerModels() {
		for(int i = 0; i < EnumHandler.EnumType.values().length; i++) {
			PremiumWood.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "leaves_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
	}
}
