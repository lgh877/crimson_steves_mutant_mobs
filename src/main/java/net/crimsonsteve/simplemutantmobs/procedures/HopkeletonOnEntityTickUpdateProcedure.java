package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;
import net.crimsonsteve.simplemutantmobs.entity.HopkeletonEntity;

public class HopkeletonOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String currentAnimation = "";
		String attackTriggerAnimation = "";
		String attackProgressAnimation = "";
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() != 0) {
			((LivingEntity) entity).setYRot(entity.getYHeadRot());
			((LivingEntity) entity).setYBodyRot(entity.getYHeadRot());
		}
		currentAnimation = ((HopkeletonEntity) entity).animationprocedure;
		if (!world.isClientSide()) {
			attackTriggerAnimation = HopkeletonAttackTriggerProcedure.execute(entity);
			attackProgressAnimation = HopkeletonAttackProgressProcedure.execute(world, x, y, z, entity);
			currentAnimation = !(attackTriggerAnimation).isEmpty() ? attackTriggerAnimation : currentAnimation;
			currentAnimation = !(attackProgressAnimation).isEmpty() ? attackProgressAnimation : currentAnimation;
		}
		if (entity instanceof HopkeletonEntity) {
			((HopkeletonEntity) entity).setAnimation(currentAnimation);
		}
	}
}
