package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;
import net.crimsonsteve.simplemutantmobs.CustomMathHelper;

public class MutantSkeletonModel extends AnimatedGeoModel<MutantSkeletonEntity> {
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
	public void setCustomAnimations(MutantSkeletonEntity animatable, int instanceId, AnimationEvent animationEvent) {
		super.setCustomAnimations(animatable, instanceId, animationEvent);
		IBone head = this.getAnimationProcessor().getBone("real_head");
		EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
		AnimationData manager = animatable.getFactory().getOrCreateAnimationData(instanceId);
		int unpausedMultiplier = !Minecraft.getInstance().isPaused() || manager.shouldPlayWhilePaused ? 1 : 0;
		head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F) * unpausedMultiplier);
		head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F) * unpausedMultiplier);
		float partialTicks = (float) Minecraft.getInstance().getFrameTime();
		IBone lowerBody = this.getAnimationProcessor().getBone("lowerBody");
		IBone upperBody = this.getAnimationProcessor().getBone("upperBody");
		if (animatable.hurtTime > 0) {
			Vec3 hurtVec = CustomMathHelper.calculateViewVector(0, animatable.getEntityData().get(MutantSkeletonEntity.DATA_damagedDirection));
			float value = Mth.sin((animatable.hurtTime - partialTicks) * (float) Math.PI / 10);
			value = value * value * value;
			//value = 1 - value;
			lowerBody.setRotationX(lowerBody.getRotationX() + (float) (value * hurtVec.z() / 4));
			lowerBody.setRotationZ(lowerBody.getRotationZ() - (float) (value * hurtVec.x() / 4));
			upperBody.setRotationX(upperBody.getRotationX() + (float) (value * hurtVec.z() / 4));
			upperBody.setRotationZ(upperBody.getRotationZ() - (float) (value * hurtVec.x() / 4));
			head.setRotationX(head.getRotationX() + (float) (value * hurtVec.z()) / 3);
			head.setRotationZ(head.getRotationZ() - (float) (value * hurtVec.x()) / 3);
		} else {
			lowerBody.setRotationX(lowerBody.getRotationX());
			lowerBody.setRotationZ(lowerBody.getRotationZ());
			upperBody.setRotationX(upperBody.getRotationX());
			upperBody.setRotationZ(upperBody.getRotationZ());
			head.setRotationZ(head.getRotationZ());
		}
	}
}
