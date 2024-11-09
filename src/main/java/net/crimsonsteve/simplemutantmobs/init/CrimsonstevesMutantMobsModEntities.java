
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.WitheredHopkeletonEntity;
import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;
import net.crimsonsteve.simplemutantmobs.entity.HopkeletonEntity;
import net.crimsonsteve.simplemutantmobs.CrimsonstevesMutantMobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrimsonstevesMutantMobsModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CrimsonstevesMutantMobsMod.MODID);
	public static final RegistryObject<EntityType<MutantSkeletonEntity>> MUTANT_SKELETON = register("mutant_skeleton",
			EntityType.Builder.<MutantSkeletonEntity>of(MutantSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MutantSkeletonEntity::new)

					.sized(1.3f, 3.9f));
	public static final RegistryObject<EntityType<HopkeletonEntity>> HOPKELETON = register("hopkeleton",
			EntityType.Builder.<HopkeletonEntity>of(HopkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HopkeletonEntity::new)

					.sized(0.6f, 2f));
	public static final RegistryObject<EntityType<WitheredHopkeletonEntity>> WITHERED_HOPKELETON = register("withered_hopkeleton", EntityType.Builder.<WitheredHopkeletonEntity>of(WitheredHopkeletonEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(WitheredHopkeletonEntity::new).fireImmune().sized(0.72f, 2.4f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			MutantSkeletonEntity.init();
			HopkeletonEntity.init();
			WitheredHopkeletonEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MUTANT_SKELETON.get(), MutantSkeletonEntity.createAttributes().build());
		event.put(HOPKELETON.get(), HopkeletonEntity.createAttributes().build());
		event.put(WITHERED_HOPKELETON.get(), WitheredHopkeletonEntity.createAttributes().build());
	}
}
