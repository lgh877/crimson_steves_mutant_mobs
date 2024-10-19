package net.crimsonsteve.simplemutantmobs.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;
import net.crimsonsteve.simplemutantmobs.CustomMathHelper;

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
		float partialTicks = (float) Minecraft.getInstance().getFrameTime();
		CoreGeoBone lowerBody = this.getAnimationProcessor().getBone("lowerBody");
		CoreGeoBone upperBody = this.getAnimationProcessor().getBone("upperBody");
		if (animatable.hurtTime > 0) {
			Vec3 hurtVec = CustomMathHelper.calculateViewVector(0, animatable.getEntityData().get(MutantSkeletonEntity.DATA_damagedDirection));
			float value = Mth.sin((animatable.hurtTime - partialTicks) * (float) Math.PI / 10);
			value = value * value * value;
			//value = 1 - value;
			lowerBody.setRotX(lowerBody.getRotX() + (float) (value * hurtVec.z() / 4));
			lowerBody.setRotZ(lowerBody.getRotZ() - (float) (value * hurtVec.x() / 4));
			upperBody.setRotX(upperBody.getRotX() + (float) (value * hurtVec.z() / 4));
			upperBody.setRotZ(upperBody.getRotZ() - (float) (value * hurtVec.x() / 4));
			head.setRotX(head.getRotX() + (float) (value * hurtVec.z()) / 3);
			head.setRotZ(head.getRotZ() - (float) (value * hurtVec.x()) / 3);
		} else {
			lowerBody.setRotX(lowerBody.getRotX());
			lowerBody.setRotZ(lowerBody.getRotZ());
			upperBody.setRotX(upperBody.getRotX());
			upperBody.setRotZ(upperBody.getRotZ());
			head.setRotX(head.getRotX());
			head.setRotZ(head.getRotZ());
		}
	}
}
