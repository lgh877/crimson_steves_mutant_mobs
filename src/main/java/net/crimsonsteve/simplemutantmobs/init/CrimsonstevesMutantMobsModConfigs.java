package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.crimsonsteve.simplemutantmobs.configuration.CrimsonSteveMutantMobsConfigsConfiguration;
import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

@Mod.EventBusSubscriber(modid = CrimsonstevesMutantMobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrimsonstevesMutantMobsModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CrimsonSteveMutantMobsConfigsConfiguration.SPEC, "CrimsonSteveMutantMobsConfig.toml");
		});
	}
}
