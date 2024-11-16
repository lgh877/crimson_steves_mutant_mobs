package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.entity.WitheredBoxerEntity;

public class WitheredBoxerOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String attackTriggerAnimation = "";
		String currentAnimation = "";
		String attackProgressAnimation = "";
		currentAnimation = ((WitheredBoxerEntity) entity).animationprocedure;
		if (!world.isClientSide()) {
			attackTriggerAnimation = WitheredBoxerAttackTriggerProcedure.execute(world, x, y, z, entity);
			attackProgressAnimation = WitheredBoxerAttackProgressProcedure.execute(world, x, y, z, entity);
			currentAnimation = !(attackTriggerAnimation).isEmpty() ? attackTriggerAnimation : currentAnimation;
			currentAnimation = !(attackProgressAnimation).isEmpty() ? attackProgressAnimation : currentAnimation;
		}
		if (entity instanceof WitheredBoxerEntity) {
			((WitheredBoxerEntity) entity).setAnimation(currentAnimation);
		}
	}
}
