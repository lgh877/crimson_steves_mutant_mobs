package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.crimsonsteve.simplemutantmobs.configuration.CrimsonSteveMutantMobsConfigsConfiguration;

import java.util.List;
import java.util.Comparator;

public class WitheredHopkeletonNaturalEntitySpawningConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean canSpawn = false;
		if (CrimsonSteveMutantMobsConfigsConfiguration.WITHEREDHOPKELETONSHOULDSPAWNNEARBYSKELETONS.get()) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(40 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof WitherSkeleton ? (ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).equals("minecraft:wither_skeleton") : false) {
						canSpawn = true;
						break;
					}
				}
			}
			return Mth.nextInt(RandomSource.create(), 1, (int) Math.max(1, (double) CrimsonSteveMutantMobsConfigsConfiguration.WITHEREDHOPKELETONSPAWNROLLS.get())) == 1 && canSpawn;
		}
		return Mth.nextInt(RandomSource.create(), 1, (int) (double) CrimsonSteveMutantMobsConfigsConfiguration.WITHEREDHOPKELETONSPAWNROLLS.get()) == 1;
	}
}
