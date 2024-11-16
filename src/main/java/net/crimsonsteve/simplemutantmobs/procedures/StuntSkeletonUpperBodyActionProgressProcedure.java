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

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class StuntSkeletonUpperBodyActionProgressProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		Entity target = null;
		double actionState = 0;
		double attackProgress = 0;
		double speed = 0;
		double attackTicks = 0;
		double lookX = 0;
		double lookZ = 0;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0) {
			currentAnimation = "empty";
		} else if (entity.isAlive()) {
			actionState = ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue();
			attackProgress = entity.getPersistentData().getDouble("attackProgress");
			attackTicks = entity.getPersistentData().getDouble("attackTicks") + 1;
			if (actionState == 1) {
				if (attackTicks > 6 && attackProgress == 0) {
					attackProgress = 1;
					target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
					if (!(null == target)) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					}
				} else if (attackTicks > 7 && attackProgress == 1) {
					attackProgress = 2;
					lookX = CalculateFlatVecNormalizeXProcedure.execute(entity.getLookAngle().x, entity.getLookAngle().z);
					lookZ = CalculateFlatVecNormalizeZProcedure.execute(entity.getLookAngle().x, entity.getLookAngle().z);
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(
							new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * speed), (entity.getDeltaMovement().y() + entity.getLookAngle().y * speed), (entity.getDeltaMovement().z() + entity.getLookAngle().z * speed)));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 1.2);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 1.2, false);
						}
					}
					{
						final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth() * 1.5), (y + entity.getBbWidth() * 0.4), (z + lookZ * entity.getBbWidth() * 1.5));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth()) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
								.toList();
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity),
										(float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
								entityiterator.invulnerableTime = 0;
							}
						}
					}
				} else if (attackTicks > 8 && attackProgress == 2) {
					speed = Mth.nextInt(RandomSource.create(), 1, 5);
					if (speed >= 3) {
						actionState = 2;
						attackProgress = 0;
						attackTicks = 6;
						currentAnimation = "left_punch_to_right";
					} else {
						attackProgress = 3;
						currentAnimation = "left_punch_end";
					}
				} else if (attackTicks > 12 && attackProgress == 3) {
					actionState = 0;
					attackProgress = 0;
					attackTicks = 0;
				}
			} else if (actionState == 2) {
				if (attackTicks > 6 && attackProgress == 0) {
					attackProgress = 1;
					target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
					if (!(null == target)) {
						entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((target.getX()), (target.getY()), (target.getZ())));
					}
				} else if (attackTicks > 7 && attackProgress == 1) {
					attackProgress = 2;
					lookX = CalculateFlatVecNormalizeXProcedure.execute(entity.getLookAngle().x, entity.getLookAngle().z);
					lookZ = CalculateFlatVecNormalizeZProcedure.execute(entity.getLookAngle().x, entity.getLookAngle().z);
					speed = ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).getValue();
					entity.setDeltaMovement(
							new Vec3((entity.getDeltaMovement().x() + entity.getLookAngle().x * speed), (entity.getDeltaMovement().y() + entity.getLookAngle().y * speed), (entity.getDeltaMovement().z() + entity.getLookAngle().z * speed)));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 1.2);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_punch")), SoundSource.HOSTILE, 1, (float) 1.2, false);
						}
					}
					{
						final Vec3 _center = new Vec3((x + lookX * entity.getBbWidth() * 1.5), (y + entity.getBbWidth() * 0.4), (z + lookZ * entity.getBbWidth() * 1.5));
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth()) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
								.toList();
						for (Entity entityiterator : _entfound) {
							if (!(entity == entityiterator)) {
								entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity),
										(float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
								entityiterator.invulnerableTime = 0;
							}
						}
					}
				} else if (attackTicks > 8 && attackProgress == 2) {
					speed = Mth.nextInt(RandomSource.create(), 1, 5);
					if (speed >= 3) {
						actionState = 1;
						attackProgress = 0;
						attackTicks = 6;
						currentAnimation = "right_punch_to_left";
					} else {
						attackProgress = 3;
						currentAnimation = "right_punch_end";
					}
				} else if (attackTicks > 12 && attackProgress == 3) {
					actionState = 0;
					attackProgress = 0;
					attackTicks = 0;
				}
			}
			((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(actionState);
			entity.getPersistentData().putDouble("attackTicks", attackTicks);
			entity.getPersistentData().putDouble("attackProgress", attackProgress);
		} else {
			currentAnimation = "empty";
		}
		return currentAnimation;
	}
}
