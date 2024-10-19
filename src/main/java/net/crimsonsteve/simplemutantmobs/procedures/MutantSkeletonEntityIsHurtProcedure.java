package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

public class MutantSkeletonEntityIsHurtProcedure {
	public static void execute(double x, double z, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (!(sourceentity == null)) {
			if (entity instanceof MutantSkeletonEntity _datEntSetI)
				_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_damagedDirection, (int) (CalculateYawSqrProcedure.execute(x, sourceentity.getX(), z, sourceentity.getZ()) - entity.getYRot()));
		}
	}
}
