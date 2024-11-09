package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.crimsonsteve.simplemutantmobs.entity.WitheredHopkeletonEntity;

public class WitheredHopkeletonModel extends AnimatedGeoModel<WitheredHopkeletonEntity> {
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
	public void setCustomAnimations(WitheredHopkeletonEntity animatable, int instanceId, AnimationEvent animationEvent) {
		super.setCustomAnimations(animatable, instanceId, animationEvent);
		IBone head = this.getAnimationProcessor().getBone("head");
		EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
		AnimationData manager = animatable.getFactory().getOrCreateAnimationData(instanceId);
		int unpausedMultiplier = !Minecraft.getInstance().isPaused() || manager.shouldPlayWhilePaused ? 1 : 0;
		head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F) * unpausedMultiplier);
		head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F) * unpausedMultiplier);
	}
}
