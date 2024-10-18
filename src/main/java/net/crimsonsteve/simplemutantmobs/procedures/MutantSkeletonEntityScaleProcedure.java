package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

public class MutantSkeletonEntityScaleProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return (entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_scale) : 0) * 0.1;
	}
}
