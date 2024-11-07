/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;

import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrimsonstevesMutantMobsModAttributes {
	public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, CrimsonstevesMutantMobsMod.MODID);
	public static final RegistryObject<Attribute> ACTIONSTATE = ATTRIBUTES.register("action_state", () -> (new RangedAttribute("attribute." + CrimsonstevesMutantMobsMod.MODID + ".action_state", 0, 0, 1024)).setSyncable(true));

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
		});
	}

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(CrimsonstevesMutantMobsModEntities.HOPKELETON.get(), ACTIONSTATE.get());
		event.add(CrimsonstevesMutantMobsModEntities.MUTANT_SKELETON.get(), ACTIONSTATE.get());
	}
}
