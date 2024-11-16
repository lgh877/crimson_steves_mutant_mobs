
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrimsonstevesMutantMobsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CrimsonstevesMutantMobsMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(CrimsonstevesMutantMobsModItems.MUTANT_SKELETON_SPAWN_EGG.get());
			tabData.accept(CrimsonstevesMutantMobsModItems.HOPKELETON_SPAWN_EGG.get());
			tabData.accept(CrimsonstevesMutantMobsModItems.WITHERED_HOPKELETON_SPAWN_EGG.get());
			tabData.accept(CrimsonstevesMutantMobsModItems.STUNT_SKELETON_UPPER_BODY_SPAWN_EGG.get());
			tabData.accept(CrimsonstevesMutantMobsModItems.WITHERED_BOXER_SPAWN_EGG.get());
		}
	}
}
