
package net.crimsonsteve.simplemutantmobs.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.geo.render.built.GeoBone;

import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.crimsonsteve.simplemutantmobs.procedures.MutantSkeletonEntityScaleProcedure;
import net.crimsonsteve.simplemutantmobs.entity.model.MutantSkeletonModel;
import net.crimsonsteve.simplemutantmobs.entity.MutantSkeletonEntity;

import javax.annotation.Nullable;

import com.mojang.math.Vector3d;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class MutantSkeletonRenderer extends GeoEntityRenderer<MutantSkeletonEntity> {
	public MutantSkeletonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new MutantSkeletonModel());
	}

	@Override
	public void render(GeoModel model, MutantSkeletonEntity animatable, float partialTick, RenderType type, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		super.render(model, animatable, partialTick, type, poseStack, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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
	public RenderType getRenderType(MutantSkeletonEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		Level world = entity.level;
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		float scale = (float) MutantSkeletonEntityScaleProcedure.execute(entity);
		stack.scale(scale, scale, scale);
		this.shadowRadius = scale;
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
