package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

public class HopkeletonAttackTriggerProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0) {
			if (entity.fallDistance == 0 && entity.onGround() && (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) || Math.random() < 0.03)) {
				currentAnimation = "hop_ready";
				((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null ? 1 : 3));
				entity.getPersistentData().putDouble("attackProgress", 0);
				entity.getPersistentData().putDouble("attackTicks", 0);
			}
			if (entity.fallDistance < entity.getPersistentData().getDouble("prevFallHeight")) {
				currentAnimation = "land";
				((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(2);
				entity.getPersistentData().putDouble("attackProgress", 0);
				entity.getPersistentData().putDouble("attackTicks", 0);
			} else if (entity.fallDistance > entity.getPersistentData().getDouble("prevFallHeight")) {
				currentAnimation = "hop_idle";
			}
			entity.getPersistentData().putDouble("prevFallHeight", entity.fallDistance);
		}
		return currentAnimation;
	}
}
