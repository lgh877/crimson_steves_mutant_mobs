package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class WitheredBoxerAttackTriggerProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		double speed = 0;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0 && !((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if (entity.tickCount % 5 == 0) {
				{
					final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 1.5), z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 4) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.toList();
					for (Entity entityiterator : _entfound) {
						if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
							((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue((Mth.nextInt(RandomSource.create(), 1, 2)));
							currentAnimation = ("punch_" + (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 1 ? "left" : "right")) + "" + Mth.nextInt(RandomSource.create(), 1, 4);
							entity.getPersistentData().putDouble("attackTicks", 0);
						}
					}
				}
			} else if (Math.random() < 0.4) {
				{
					final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 3), z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 10) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.toList();
					for (Entity entityiterator : _entfound) {
						if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, 1, 0, 0, 0, 1);
							entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getY()),
									((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
							speed = 2 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
							entity.setDeltaMovement(
									new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * speed), (entity.getDeltaMovement().y() + entity.getLookAngle().y * speed), (entity.getDeltaMovement().z() + entity.getLookAngle().z * speed)));
							((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue((entity.getPersistentData().getDouble("weavedCount") % 2 + 3));
							currentAnimation = "weaving_" + (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 3 ? "left" : "right");
							entity.getPersistentData().putDouble("attackTicks", 0);
							entity.getPersistentData().putDouble("weavedCount", (entity.getPersistentData().getDouble("weavedCount") + 1));
						}
					}
				}
			}
		}
		return currentAnimation;
	}
}
