package com.dizzlepop12.premiumwood;

import com.dizzlepop12.premiumwood.proxy.CommonProxy;
import com.dizzlepop12.premiumwood.util.Reference;

import net.minecraft.client.main.Main;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
		modid = Reference.MOD_ID, 
		name = Reference.NAME,
		version = Reference.VERSION)

public class PremiumWood {
	
	@Instance
	public static PremiumWood instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent e) {
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e) {
		
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent e) {
		
	}
}
