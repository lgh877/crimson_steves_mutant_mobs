package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

import net.crimsonsteve.simplemutantmobs.entity.WitheredHopkeletonEntity;

public class WitheredHopkeletonModel extends GeoModel<WitheredHopkeletonEntity> {
	@Override
	public ResourceLocation getAnimationResource(WitheredHopkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "animations/hopkeleton.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(WitheredHopkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "geo/hopkeleton.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(WitheredHopkeletonEntity entity) {
		return new ResourceLocation("crimsonsteves_mutant_mobs", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(WitheredHopkeletonEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}