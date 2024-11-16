
package net.crimsonsteve.simplemutantmobs.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import org.joml.Vector3d;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.crimsonsteve.simplemutantmobs.entity.model.MutantSkeletonModel;
import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class MutantSkeletonRenderer extends GeoEntityRenderer<MutantSkeletonEntity> {
	public MutantSkeletonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new MutantSkeletonModel());
		this.shadowRadius = 1f;
	}

	@Override
	public RenderType getRenderType(MutantSkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void postRender(PoseStack poseStack, MutantSkeletonEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		super.postRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
		int particleSettings = animatable.getEntityData().get(MutantSkeletonEntity.DATA_particleSettings);
		GeoBone particle;
		Vector3d particlePos;
		if (particleSettings == 0) {
		} else if (particleSettings == 1) {
			particle = model.getBone("leftFist").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		} else if (particleSettings == 2) {
			particle = model.getBone("rightFist").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		} else if (particleSettings == 3) {
			particle = model.getBone("leftFist").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
			particle = model.getBone("rightFist").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		} else if (particleSettings == 4) {
			particle = model.getBone("leftFoot").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		} else if (particleSettings == 5) {
			particle = model.getBone("rightFoot").get();
			particlePos = particle.getWorldPosition();
			animatable.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		}
	}

	@Override
	public void preRender(PoseStack poseStack, MutantSkeletonEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
