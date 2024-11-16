package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.crimsonsteve.simplemutantmobs.entity.StuntSkeletonUpperBodyEntity;

public class StuntSkeletonUpperBodyModel extends GeoModel<StuntSkeletonUpperBodyEntity> {
	@Override
	public ResourceLocation getAnimationResource(StuntSkeletonUpperBodyEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "animations/simple_mutant_skeleton_upper_body.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(StuntSkeletonUpperBodyEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "geo/simple_mutant_skeleton_upper_body.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(StuntSkeletonUpperBodyEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(StuntSkeletonUpperBodyEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("real_head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
