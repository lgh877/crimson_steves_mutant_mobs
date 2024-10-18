package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

import net.crimsonsteve.simplemutantmobs.configuration.CrimsonSteveMutantMobsConfigsConfiguration;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class MutantSkeletonSpawnConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean canSpawn = false;
		if (CrimsonSteveMutantMobsConfigsConfiguration.MUTANTSKELETONSHOULDSPAWNNEARBYSKELETONS.get()) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
						.collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if ((ForgeRegistries.ENTITY_TYPES.getKey(entityiterator.getType()).toString()).equals("minecraft:skeleton")) {
						canSpawn = true;
						break;
					}
				}
			}
			return Mth.nextInt(RandomSource.create(), 1, (int) Math.max(1, (double) CrimsonSteveMutantMobsConfigsConfiguration.MUTANTSKELETONSPAWNROLLS.get())) == 1 && canSpawn;
		}
		return Mth.nextInt(RandomSource.create(), 1, (int) (double) CrimsonSteveMutantMobsConfigsConfiguration.MUTANTSKELETONSPAWNROLLS.get()) == 1;
	}
}
