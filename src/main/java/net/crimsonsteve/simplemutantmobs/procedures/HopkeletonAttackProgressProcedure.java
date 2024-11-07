package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class HopkeletonAttackProgressProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		double actionState = 0;
		double attackProgress = 0;
		double speed = 0;
		Entity target = null;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0) {
			if (entity.onGround() || entity.isInWater()) {
				currentAnimation = "idle";
			} else {
				currentAnimation = currentAnimation;
			}
		} else {
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
				if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
										(float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
							}
						}
					}
				}
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
				} else if (attackProgress == 2 && (entity.getPersistentData().getDouble("attackTicks") >= 16 || entity.onGround() || entity.fallDistance > 0)) {
					currentAnimation = "hop_attack_occur";
					attackProgress = 0;
					((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(2);
					entity.getPersistentData().putDouble("attackTicks", 0);
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
