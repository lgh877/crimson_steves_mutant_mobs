package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;
import net.crimsonsteve.simplemutantmobs.configuration.CrimsonSteveMutantMobsConfigsConfiguration;

public class MutantSkeletonOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof MutantSkeletonEntity _datEntSetI)
			_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_scale, (int) ((double) CrimsonSteveMutantMobsConfigsConfiguration.MUTANTSKELETONSCALE.get() * 10));
	}
}
