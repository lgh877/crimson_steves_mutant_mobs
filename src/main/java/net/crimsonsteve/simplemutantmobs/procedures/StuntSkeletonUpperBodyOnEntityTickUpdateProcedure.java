package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.crimsonsteve.simplemutantmobs.entity.StuntSkeletonUpperBodyEntity;

public class StuntSkeletonUpperBodyOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String attackTriggerAnimation = "";
		String currentAnimation = "";
		String attackProgressAnimation = "";
		currentAnimation = ((StuntSkeletonUpperBodyEntity) entity).animationprocedure;
		if (!world.isClientSide()) {
			if (entity.onGround() && entity.tickCount % 6 == 1 && GetDistanceToTargetFlatProcedure.execute(0, entity.getDeltaMovement().x(), 0, entity.getDeltaMovement().z()) > 0.01) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.NEUTRAL, (float) 0.3, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("crimsonsteves_mutant_mobs:mutant_skeleton_step")), SoundSource.NEUTRAL, (float) 0.3, 1, false);
					}
				}
			}
			attackTriggerAnimation = StuntSkeletonUpperBodyActionTriggerProcedure.execute(world, x, y, z, entity);
			attackProgressAnimation = StuntSkeletonUpperBodyActionProgressProcedure.execute(world, x, y, z, entity);
			currentAnimation = !(attackTriggerAnimation).isEmpty() ? attackTriggerAnimation : currentAnimation;
			currentAnimation = !(attackProgressAnimation).isEmpty() ? attackProgressAnimation : currentAnimation;
		}
		if (entity instanceof StuntSkeletonUpperBodyEntity) {
			((StuntSkeletonUpperBodyEntity) entity).setAnimation(currentAnimation);
		}
	}
}
