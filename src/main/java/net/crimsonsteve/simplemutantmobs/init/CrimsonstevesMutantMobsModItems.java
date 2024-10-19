
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

public class CrimsonstevesMutantMobsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CrimsonstevesMutantMobsMod.MODID);
	public static final RegistryObject<Item> MUTANT_SKELETON_SPAWN_EGG = REGISTRY.register("mutant_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(CrimsonstevesMutantMobsModEntities.MUTANT_SKELETON, -6710887, -10066330, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
