package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;
import net.crimsonsteve.simplemutantmobs.entity.ChadWitheredBoxerEntity;

public class ChadWitheredBoxerOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double width = 0;
		String attackTriggerAnimation = "";
		String currentAnimation = "";
		String attackProgressAnimation = "";
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() != 0) {
			((LivingEntity) entity).setYRot(entity.getYHeadRot());
			((LivingEntity) entity).setYBodyRot(entity.getYHeadRot());
		}
		currentAnimation = ((ChadWitheredBoxerEntity) entity).animationprocedure;
		if (!world.isClientSide()) {
			width = entity.getBbWidth();
			attackTriggerAnimation = WitheredBoxerAttackTriggerProcedure.execute(world, x, y, z, entity);
			attackProgressAnimation = WitheredBoxerAttackProgressProcedure.execute(world, x, y, z, entity, width * 2, width * 1.5 * CalculateViewVectorXProcedure.execute(0, entity.getYRot()), width * 0.8,
					width * 1.5 * CalculateViewVectorZProcedure.execute(0, entity.getYRot()));
			currentAnimation = !(attackTriggerAnimation).isEmpty() ? attackTriggerAnimation : currentAnimation;
			currentAnimation = !(attackProgressAnimation).isEmpty() ? attackProgressAnimation : currentAnimation;
		}
		if (entity instanceof ChadWitheredBoxerEntity) {
			((ChadWitheredBoxerEntity) entity).setAnimation(currentAnimation);
		}
	}
}
