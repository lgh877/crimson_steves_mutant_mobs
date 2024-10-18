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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

import java.util.List;
import java.util.Comparator;

public class MutantSkeletonOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean isMoving = false;
		String animationType = "";
		Entity target = null;
		double sprintTicks = 0;
		double lookX = 0;
		double lookZ = 0;
		double attackTicks = 0;
		double random = 0;
		double dmgMul = 0;
		double dashPower = 0;
		double jumpPower = 0;
		double attackType = 0;
		double attackProgress = 0;
		double particleSettings = 0;
		lookX = new Vec3(entity.getLookAngle().x(), 0, entity.getLookAngle().z()).normalize().x();
		lookZ = new Vec3(entity.getLookAngle().x(), 0, entity.getLookAngle().z()).normalize().z();
		animationType = ((MutantSkeletonEntity) entity).animationprocedure;
		dmgMul = entity.getPersistentData().getDouble("damageMultiplier");
		if (entity.tickCount % 9 == 0) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) && (entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0) == 0) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				random = Mth.nextInt(RandomSource.create(), 1, 20);
				if (random <= 16) {
					{
						final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 2), z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 4) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (target == entityiterator) {
								random = Mth.nextInt(RandomSource.create(), 1, 10);
								if (random <= 4) {
									animationType = "animation.simple_mutant_skeleton.melee_attack_left";
									dmgMul = 1;
									if (entity instanceof MutantSkeletonEntity _datEntSetI)
										_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 1);
								} else if (random <= 8) {
									animationType = "animation.simple_mutant_skeleton.melee_attack_right";
									dmgMul = 1;
									if (entity instanceof MutantSkeletonEntity _datEntSetI)
										_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 2);
								} else if (random == 9) {
									animationType = "animation.simple_mutant_skeleton.left_kick";
									dmgMul = 1.4;
									if (entity instanceof MutantSkeletonEntity _datEntSetI)
										_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 12);
								} else {
									animationType = "animation.simple_mutant_skeleton.right_kick";
									dmgMul = 1.4;
									if (entity instanceof MutantSkeletonEntity _datEntSetI)
										_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 13);
								}
								entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entityiterator.getX()), (entityiterator.getY() + entityiterator.getBbHeight() * 0.8), (entityiterator.getZ())));
								attackTicks = 0;
							}
						}
					}
				} else if (random <= 18) {
					random = entity.tickCount % 3;
					dmgMul = 2;
					if (random == 1) {
						animationType = "animation.simple_mutant_skeleton.left_smash";
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 3);
					} else if (random == 2) {
						animationType = "animation.simple_mutant_skeleton.right_smash";
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 4);
					} else {
						animationType = "animation.simple_mutant_skeleton.smash";
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 5);
					}
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					attackTicks = 0;
				} else if (random <= 20) {
					random = Mth.nextInt(RandomSource.create(), 1, 3);
					if (random == 1) {
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 9);
						animationType = "animation.simple_mutant_skeleton.left_leap";
					} else if (random == 2) {
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 10);
						animationType = "animation.simple_mutant_skeleton.right_leap";
					} else {
						if (entity instanceof MutantSkeletonEntity _datEntSetI)
							_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, 11);
						animationType = "animation.simple_mutant_skeleton.front_leap";
					}
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					attackTicks = 0;
				}
			}
		}
		if (world.isClientSide() && entity.onGround() && (entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0) == 0) {
			if (!entity.isSprinting()) {
				if (entity.tickCount % 15 == 1 && GetDistanceToTargetFlatProcedure.execute(0, entity.getDeltaMovement().x(), 0, entity.getDeltaMovement().z()) > 0.01) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.HOSTILE, (float) 0.25, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.HOSTILE, (float) 0.25, 1, false);
						}
					}
				}
			} else if (entity.tickCount % 6 == 1 && GetDistanceToTargetFlatProcedure.execute(0, entity.getDeltaMovement().x(), 0, entity.getDeltaMovement().z()) > 0.01) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.HOSTILE, (float) 0.25, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.HOSTILE, (float) 0.25, 1, false);
					}
				}
			}
		}
		if (!world.isClientSide()) {
			if (entity.isAlive()) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.5) {
					entity.setSprinting(true);
				} else {
					entity.setSprinting(false);
				}
				if ((entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0) == 0) {
					if (entity.isSprinting() && GetDistanceToTargetFlatProcedure.execute(0, entity.getDeltaMovement().x(), 0, entity.getDeltaMovement().z()) > 0.01) {
						animationType = "animation.simple_mutant_skeleton.sprint";
					} else {
						animationType = "empty";
					}
				} else {
					attackTicks = entity.getPersistentData().getDouble("attackTicks") + 1;
					attackType = entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0;
					attackProgress = entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackProgress) : 0;
					particleSettings = entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_particleSettings) : 0;
					if (attackType <= 2) {
						if (attackTicks > 7 && attackProgress == 0) {
							attackProgress = 1;
							particleSettings = attackType;
							{
								Entity _ent = entity;
								_ent.setYRot((float) entity.getYHeadRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
							entity.setDeltaMovement(new Vec3((lookX * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * 3 * dmgMul), (entity.getDeltaMovement().y()),
									(lookZ * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * 3 * dmgMul)));
						} else if (attackTicks > 8 && attackProgress == 1) {
							attackProgress = 2;
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth()), (y + entity.getBbWidth()), (z + lookZ * entity.getBbWidth()));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.invulnerableTime = 0;
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
									}
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth()), (y + 3 * entity.getBbWidth()), (z + lookZ * entity.getBbWidth()));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
									}
								}
							}
						} else if (attackTicks > 9 && attackProgress == 2) {
							random = Mth.nextInt(RandomSource.create(), 1, 11);
							particleSettings = 0;
							if (random <= 5) {
								if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
									target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + target.getBbHeight() * 0.8), (target.getZ())));
								}
								attackProgress = 0;
								attackTicks = 4;
								dmgMul = 1;
								if (attackType == 1) {
									attackType = 2;
									animationType = "animation.simple_mutant_skeleton.melee_attack_left_to_right";
								} else {
									attackType = 1;
									animationType = "animation.simple_mutant_skeleton.melee_attack_right_to_left";
								}
							} else if (random == 6) {
								if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
									target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + target.getBbHeight() * 0.8), (target.getZ())));
								}
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 1.6;
								if (attackType == 1) {
									attackType = 2;
									animationType = "animation.simple_mutant_skeleton.melee_attack_left_to_right_strong";
								} else {
									attackType = 1;
									animationType = "animation.simple_mutant_skeleton.melee_attack_right_to_left_strong";
								}
							} else if (random == 7) {
								if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
									target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + target.getBbHeight() * 0.8), (target.getZ())));
								}
								attackProgress = 0;
								attackTicks = -2;
								dmgMul = 1.8;
								if (attackType == 1) {
									attackType = 6;
									animationType = "animation.simple_mutant_skeleton.right_uppercut_melee";
								} else {
									attackType = 7;
									animationType = "animation.simple_mutant_skeleton.left_uppercut_melee";
								}
							} else if (random == 8) {
								if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
									target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY() + target.getBbHeight() * 0.8), (target.getZ())));
								}
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 1.4;
								if (attackType == 1) {
									attackType = 13;
									animationType = "animation.simple_mutant_skeleton.melee_attack_left_to_kick";
								} else {
									attackType = 12;
									animationType = "animation.simple_mutant_skeleton.melee_attack_right_to_kick";
								}
							} else {
								attackProgress = 3;
								if (attackType == 1) {
									animationType = "animation.simple_mutant_skeleton.melee_attack_left_end";
								} else {
									animationType = "animation.simple_mutant_skeleton.melee_attack_right_end";
								}
							}
						} else if (attackTicks > 15 && attackProgress == 3) {
							attackType = 0;
							attackProgress = 0;
							attackTicks = 0;
						}
					} else if (attackType <= 5) {
						if (attackTicks > 5 && attackProgress == 0) {
							attackProgress = 1;
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_avoid")), SoundSource.HOSTILE, 1, (float) 0.8);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_avoid")), SoundSource.HOSTILE, 1, (float) 0.8, false);
								}
							}
							{
								Entity _ent = entity;
								_ent.setYRot((float) entity.getYHeadRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
							if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
								target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
								random = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
								dashPower = Math.min(Math.sqrt(Math.max(GetDistanceToTargetFlatProcedure.execute(x, target.getX(), z, target.getZ()) - (target.getBbWidth() + entity.getBbWidth()) / 2, 0)), 12);
								jumpPower = Math.sqrt(Math.max(target.getY() - entity.getY(), 0));
								if (jumpPower < random * 8) {
									jumpPower = random * 8;
								}
							} else {
								dashPower = 2;
								jumpPower = random * 8;
							}
							entity.setDeltaMovement(new Vec3((lookX * dashPower), jumpPower, (lookZ * dashPower)));
							particleSettings = attackType - 2;
						} else if (attackTicks > 8 && attackProgress == 1) {
							attackProgress = 2;
							{
								Entity _ent = entity;
								_ent.setYRot((float) entity.getYHeadRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
							entity.setDeltaMovement(
									new Vec3((entity.getDeltaMovement().x()), (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * (-8)), (entity.getDeltaMovement().z())));
							entity.fallDistance = 200;
						} else if (attackTicks > 10 && attackProgress == 2) {
							attackProgress = 3;
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_smash")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_smash")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth() * 0.8), (y + 0.3 * entity.getBbWidth()), (z + lookZ * entity.getBbWidth() * 0.8));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2.5 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
										entityiterator.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().y() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().z() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5)));
									}
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth() * 0.8), (y + 1.3 * entity.getBbWidth()), (z + lookZ * entity.getBbWidth() * 0.8));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2.5 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
										entityiterator.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().y() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().z() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5)));
									}
								}
							}
						} else if (attackTicks > 12 && attackProgress == 3) {
							attackProgress = 4;
							particleSettings = 0;
						} else if (attackTicks > 16 && attackProgress == 4) {
							entity.fallDistance = 0;
							if (entity.tickCount % 8 < 2) {
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 1.8;
								attackType = attackType + 3;
								if (attackType == 6) {
									animationType = "animation.simple_mutant_skeleton.right_uppercut";
								} else if (attackType == 7) {
									animationType = "animation.simple_mutant_skeleton.left_uppercut";
								} else {
									animationType = "animation.simple_mutant_skeleton.uppercut";
								}
							} else {
								attackProgress = 5;
								if (attackType == 3) {
									animationType = "animation.simple_mutant_skeleton.left_smash_end";
								} else if (attackType == 4) {
									animationType = "animation.simple_mutant_skeleton.right_smash_end";
								} else {
									animationType = "animation.simple_mutant_skeleton.smash_end";
								}
							}
						} else if (attackTicks > 27 && attackProgress == 5) {
							attackProgress = 0;
							attackType = 0;
							attackTicks = 0;
						}
					} else if (attackType <= 8) {
						if (attackTicks > 0 && attackProgress == 0) {
							attackProgress = 1;
							particleSettings = attackType - 5;
							{
								Entity _ent = entity;
								_ent.setYRot((float) entity.getYHeadRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
							entity.setDeltaMovement(new Vec3((lookX * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue()),
									(((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue() * 3),
									(lookZ * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue())));
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 0.5);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 0.5, false);
								}
							}
						} else if (attackTicks > 1 && attackProgress == 1) {
							attackProgress = 2;
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth()), (y + entity.getBbWidth()), (z + lookZ * entity.getBbWidth()));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.invulnerableTime = 0;
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
										entityiterator.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().y()), (entity.getDeltaMovement().z() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5)));
									}
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth()), (y + 2 * entity.getBbWidth()), (z + lookZ * entity.getBbWidth()));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
										entityiterator.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5),
												(entity.getDeltaMovement().y()), (entity.getDeltaMovement().z() * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_KNOCKBACK).getValue() * 2.5)));
									}
								}
							}
						} else if (attackTicks > 5 && attackProgress == 2) {
							attackProgress = 3;
							particleSettings = 0;
						} else if (attackTicks > 11 && attackProgress == 3) {
							attackProgress = 0;
							attackType = 0;
							attackTicks = 0;
						}
					} else if (attackType <= 11) {
						if (attackTicks > 4 && attackProgress == 0) {
							attackProgress = 1;
							if ((entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0) == 9) {
								entity.getPersistentData().putDouble("wantedYaw", (entity.getYRot() - 90));
								entity.getPersistentData().putDouble("wantedPitch", 0);
							} else if ((entity instanceof MutantSkeletonEntity _datEntI ? _datEntI.getEntityData().get(MutantSkeletonEntity.DATA_attackType) : 0) == 10) {
								entity.getPersistentData().putDouble("wantedYaw", (entity.getYRot() + 90));
								entity.getPersistentData().putDouble("wantedPitch", 0);
							} else {
								entity.getPersistentData().putDouble("wantedYaw", (entity.getYRot()));
								entity.getPersistentData().putDouble("wantedPitch", 0);
							}
						} else if (attackTicks > 5 && attackProgress == 1) {
							attackProgress = 2;
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_avoid")), SoundSource.HOSTILE, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_avoid")), SoundSource.HOSTILE, 1, 1, false);
								}
							}
							entity.setDeltaMovement(new Vec3(
									(CalculateViewVectorXProcedure.execute(entity.getPersistentData().getDouble("wantedPitch"), entity.getPersistentData().getDouble("wantedYaw")) * 5
											* ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue()),
									(entity.getDeltaMovement().y() + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue()),
									(CalculateViewVectorZProcedure.execute(entity.getPersistentData().getDouble("wantedPitch"), entity.getPersistentData().getDouble("wantedYaw")) * 5
											* ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue())));
						} else if (attackTicks > 11 && attackProgress == 2) {
							if (entity.tickCount % 8 < 2) {
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 2;
								attackType = attackType - 6;
								if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
									target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
									entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
								}
								if (attackType == 3) {
									animationType = "animation.simple_mutant_skeleton.left_leap_to_smash";
								} else if (attackType == 4) {
									animationType = "animation.simple_mutant_skeleton.right_leap_to_smash";
								} else {
									animationType = "animation.simple_mutant_skeleton.front_leap_to_smash";
								}
							} else {
								attackProgress = 3;
								if (attackType == 9) {
									animationType = "animation.simple_mutant_skeleton.left_leap_end";
								} else if (attackType == 10) {
									animationType = "animation.simple_mutant_skeleton.right_leap_end";
								} else {
									animationType = "animation.simple_mutant_skeleton.front_leap_end";
								}
							}
						} else if (attackTicks > 14 && attackProgress == 3) {
							attackProgress = 0;
							attackType = 0;
							attackTicks = 0;
						}
					} else if (attackType <= 13) {
						if (attackTicks > 7 && attackProgress == 0) {
							attackProgress = 1;
							particleSettings = attackType - 8;
						} else if (attackTicks > 8 && attackProgress == 1) {
							attackProgress = 2;
							{
								Entity _ent = entity;
								_ent.setYRot((float) entity.getYHeadRot());
								_ent.setXRot(entity.getXRot());
								_ent.setYBodyRot(_ent.getYRot());
								_ent.setYHeadRot(_ent.getYRot());
								_ent.yRotO = _ent.getYRot();
								_ent.xRotO = _ent.getXRot();
								if (_ent instanceof LivingEntity _entity) {
									_entity.yBodyRotO = _entity.getYRot();
									_entity.yHeadRotO = _entity.getYRot();
								}
							}
						} else if (attackTicks > 9 && attackProgress == 2) {
							attackProgress = 3;
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 0.7);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 0.7, false);
								}
							}
							{
								final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth() * 1.3), (y + 1.25 * entity.getBbWidth()), (z + lookZ * entity.getBbWidth() * 1.3));
								List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((2.5 * entity.getBbWidth()) / 2d), e -> true).stream()
										.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
								for (Entity entityiterator : _entfound) {
									if (!(entity == entityiterator)) {
										entityiterator.invulnerableTime = 0;
										entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity),
												(float) (((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue() * dmgMul));
										entityiterator.setDeltaMovement(new Vec3((lookX * 4 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue()),
												(entity.getDeltaMovement().y() + ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue()),
												(lookZ * 4 * ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue())));
									}
								}
							}
						} else if (attackTicks > 10 && attackProgress == 3) {
							random = Mth.nextInt(RandomSource.create(), 1, 10);
							particleSettings = 0;
							if (random <= 4) {
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 1.6;
								if (attackType == 12) {
									attackType = 2;
									animationType = "animation.simple_mutant_skeleton.left_kick_to_punch";
								} else {
									attackType = 1;
									animationType = "animation.simple_mutant_skeleton.right_kick_to_punch";
								}
							} else if (random == 6) {
								attackProgress = 0;
								attackTicks = 0;
								dmgMul = 2;
								if (attackType == 12) {
									attackType = 4;
									animationType = "animation.simple_mutant_skeleton.left_kick_to_smash";
								} else {
									attackType = 3;
									animationType = "animation.simple_mutant_skeleton.right_kick_to_smash";
								}
							} else {
								attackProgress = 4;
								if (attackType == 12) {
									animationType = "animation.simple_mutant_skeleton.left_kick_end";
								} else {
									animationType = "animation.simple_mutant_skeleton.right_kick_end";
								}
							}
						} else if (attackTicks > 14 && attackProgress == 4) {
							attackProgress = 0;
							attackType = 0;
							attackTicks = 0;
						}
					}
					entity.getPersistentData().putDouble("attackTicks", attackTicks);
					if (entity instanceof MutantSkeletonEntity _datEntSetI)
						_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackType, (int) attackType);
					if (entity instanceof MutantSkeletonEntity _datEntSetI)
						_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_attackProgress, (int) attackProgress);
					if (entity instanceof MutantSkeletonEntity _datEntSetI)
						_datEntSetI.getEntityData().set(MutantSkeletonEntity.DATA_particleSettings, (int) particleSettings);
				}
			} else {
				animationType = "empty";
			}
		}
		if (entity instanceof MutantSkeletonEntity) {
			((MutantSkeletonEntity) entity).setAnimation(animationType);
		}
		entity.getPersistentData().putDouble("damageMultiplier", dmgMul);
	}
}
