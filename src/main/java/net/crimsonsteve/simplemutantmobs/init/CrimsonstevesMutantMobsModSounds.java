/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

public class CrimsonstevesMutantMobsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CrimsonstevesMutantMobsMod.MODID);
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_IDLE = REGISTRY.register("mutant_skeleton_idle", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_idle")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_HURT = REGISTRY.register("mutant_skeleton_hurt", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_hurt")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_PUNCH = REGISTRY.register("mutant_skeleton_punch", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_punch")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_SMASH = REGISTRY.register("mutant_skeleton_smash", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_smash")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_STEP = REGISTRY.register("mutant_skeleton_step", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_step")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_AVOID = REGISTRY.register("mutant_skeleton_avoid", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_avoid")));
	public static final RegistryObject<SoundEvent> MUTANT_SKELETON_DEATH = REGISTRY.register("mutant_skeleton_death", () -> new SoundEvent(new ResourceLocation("crimsonsteves_mutant_mobs", "mutant_skeleton_death")));
}
