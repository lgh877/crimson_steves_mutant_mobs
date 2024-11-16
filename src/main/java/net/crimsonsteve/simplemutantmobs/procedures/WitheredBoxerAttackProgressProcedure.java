package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class WitheredBoxerAttackProgressProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		double actionState = 0;
		double attackProgress = 0;
		double speed = 0;
		Entity target = null;
		String currentAnimation = "";
		boolean isInRadius = false;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0) {
			if (GetDistanceToTargetFlatProcedure.execute(0, entity.getDeltaMovement().x(), 0, entity.getDeltaMovement().z()) > 0.1) {
				currentAnimation = "walk";
			} else {
				currentAnimation = "idle";
			}
		} else if (entity.isAlive()) {
			actionState = ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue();
			attackProgress = entity.getPersistentData().getDouble("attackProgress");
			if (actionState == 1) {
				if (entity.getPersistentData().getDouble("attackTicks") < 1) {
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * 2;
					entity.setDeltaMovement(
							new Vec3((entity.getLookAngle().x * speed + entity.getDeltaMovement().x()), (entity.getLookAngle().y * speed + entity.getDeltaMovement().y()), (entity.getLookAngle().z * speed + entity.getDeltaMovement().z())));
				} else if (entity.getPersistentData().getDouble("attackTicks") >= 2) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity),
										(float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
								entityiterator.invulnerableTime = 0;
								isInRadius = true;
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.CRIT, (entityiterator.getX()), (y + entity.getBbHeight()), (entityiterator.getZ()), 10, 0.1, 0.1, 0.1, 0.1);
							}
						}
					}
					if (isInRadius) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.knockback")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.knockback")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
						actionState = 2;
						currentAnimation = "punch_right" + Mth.nextInt(RandomSource.create(), 1, 4);
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					} else {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						actionState = 0;
					}
					entity.getPersistentData().putDouble("attackTicks", 0);
				}
			} else if (actionState == 2) {
				if (entity.getPersistentData().getDouble("attackTicks") < 1) {
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(
							new Vec3((entity.getLookAngle().x * speed + entity.getDeltaMovement().x()), (entity.getLookAngle().y * speed + entity.getDeltaMovement().y()), (entity.getLookAngle().z * speed + entity.getDeltaMovement().z())));
				} else if (entity.getPersistentData().getDouble("attackTicks") >= 2) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity),
										(float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
								entityiterator.invulnerableTime = 0;
								isInRadius = true;
								if (world instanceof ServerLevel _level)
									_level.sendParticles(ParticleTypes.CRIT, (entityiterator.getX()), (y + entity.getBbHeight()), (entityiterator.getZ()), 10, 0.1, 0.1, 0.1, 0.1);
							}
						}
					}
					if (isInRadius) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.knockback")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.knockback")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
						actionState = 1;
						currentAnimation = "punch_left" + Mth.nextInt(RandomSource.create(), 1, 4);
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					} else {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						actionState = 0;
					}
					entity.getPersistentData().putDouble("attackTicks", 0);
				}
			} else if (actionState == 3) {
				if (entity.getPersistentData().getDouble("attackTicks") >= 3) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
								isInRadius = true;
							}
						}
					}
					if (isInRadius) {
						target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
						actionState = 2;
						currentAnimation = "punch_right" + Mth.nextInt(RandomSource.create(), 1, 4);
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					} else {
						actionState = 0;
						currentAnimation = "idle";
					}
					entity.getPersistentData().putDouble("attackTicks", 0);
				}
			} else if (actionState == 4) {
				if (entity.getPersistentData().getDouble("attackTicks") >= 3) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == entityiterator) {
								isInRadius = true;
							}
						}
					}
					if (isInRadius) {
						target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
						actionState = 1;
						currentAnimation = "punch_left" + Mth.nextInt(RandomSource.create(), 1, 4);
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					} else {
						actionState = 0;
						currentAnimation = "idle";
					}
					entity.getPersistentData().putDouble("attackTicks", 0);
				}
			}
			((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(actionState);
			entity.getPersistentData().putDouble("attackTicks", (entity.getPersistentData().getDouble("attackTicks") + 1));
			entity.getPersistentData().putDouble("attackProgress", attackProgress);
		} else {
			currentAnimation = "idle";
		}
		return currentAnimation;
	}
}
