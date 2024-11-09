package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

public class HopkeletonAttackProgressProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		double actionState = 0;
		double attackProgress = 0;
		double speed = 0;
		Entity target = null;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0) {
			if (entity.isOnGround() || entity.isInWater()) {
				currentAnimation = "idle";
			} else {
				currentAnimation = currentAnimation;
			}
		} else if (entity.isAlive()) {
			actionState = ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue();
			attackProgress = entity.getPersistentData().getDouble("attackProgress");
			if (actionState == 1) {
				if (attackProgress == 0 && entity.getPersistentData().getDouble("attackTicks") >= 4) {
					currentAnimation = "hop";
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 3 * speed), ((1 + entity.getLookAngle().y) * 3 * speed), (entity.getLookAngle().z * 3 * speed)));
					attackProgress = 1;
				} else if (attackProgress == 1 && entity.getPersistentData().getDouble("attackTicks") >= 8) {
					currentAnimation = "hop_idle";
					attackProgress = 0;
					((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(0);
				}
			} else if (actionState == 2) {
				if (entity.getPersistentData().getDouble("attackTicks") >= 4) {
					((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(0);
				}
			} else if (actionState == 3) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				if (!(target == null)) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
				}
				if (attackProgress == 0 && entity.getPersistentData().getDouble("attackTicks") >= 4) {
					currentAnimation = "hop_attack";
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 3 * speed), ((1 + entity.getLookAngle().y) * 3 * speed), (entity.getLookAngle().z * 3 * speed)));
					attackProgress = 1;
				} else if (attackProgress == 1 && entity.getPersistentData().getDouble("attackTicks") >= 8) {
					currentAnimation = "hop_attack_idle";
					attackProgress = 2;
				} else if (attackProgress == 2 && (entity.getPersistentData().getDouble("attackTicks") >= 20 || entity.isOnGround() || entity.fallDistance > 0)) {
					currentAnimation = "hop_attack_occur";
					attackProgress = 0;
					((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(2);
					entity.getPersistentData().putDouble("attackTicks", (-10));
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(new Vec3((entity.getLookAngle().x * 3 * speed), (Math.min(entity.getLookAngle().y, 0) * 3 * speed), (entity.getLookAngle().z * 3 * speed)));
				}
			}
			entity.getPersistentData().putDouble("attackTicks", (entity.getPersistentData().getDouble("attackTicks") + 1));
			entity.getPersistentData().putDouble("attackProgress", attackProgress);
		}
		return currentAnimation;
	}
}
