
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
	public static final RegistryObject<Item> HOPKELETON_SPAWN_EGG = REGISTRY.register("hopkeleton_spawn_egg", () -> new ForgeSpawnEggItem(CrimsonstevesMutantMobsModEntities.HOPKELETON, -10066330, -3355444, new Item.Properties()));
	public static final RegistryObject<Item> WITHERED_HOPKELETON_SPAWN_EGG = REGISTRY.register("withered_hopkeleton_spawn_egg",
			() -> new ForgeSpawnEggItem(CrimsonstevesMutantMobsModEntities.WITHERED_HOPKELETON, -16777216, -13291215, new Item.Properties()));
	public static final RegistryObject<Item> STUNT_SKELETON_UPPER_BODY_SPAWN_EGG = REGISTRY.register("stunt_skeleton_upper_body_spawn_egg",
			() -> new ForgeSpawnEggItem(CrimsonstevesMutantMobsModEntities.STUNT_SKELETON_UPPER_BODY, -10066330, -6710887, new Item.Properties()));
	public static final RegistryObject<Item> WITHERED_BOXER_SPAWN_EGG = REGISTRY.register("withered_boxer_spawn_egg", () -> new ForgeSpawnEggItem(CrimsonstevesMutantMobsModEntities.WITHERED_BOXER, -14015966, -7881517, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
