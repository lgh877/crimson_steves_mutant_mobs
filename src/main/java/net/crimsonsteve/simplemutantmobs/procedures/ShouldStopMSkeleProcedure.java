package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

public class ShouldStopMSkeleProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return 0 == (entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0);
	}
}
