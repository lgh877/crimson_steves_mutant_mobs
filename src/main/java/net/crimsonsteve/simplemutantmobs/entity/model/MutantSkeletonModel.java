package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

public class MutantSkeletonModel extends GeoModel<MutantSkeletonEntity> {
	@Override
	public ResourceLocation getAnimationResource(MutantSkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "animations/simple_mutant_skeleton.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MutantSkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "geo/simple_mutant_skeleton.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MutantSkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(MutantSkeletonEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("real_head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
