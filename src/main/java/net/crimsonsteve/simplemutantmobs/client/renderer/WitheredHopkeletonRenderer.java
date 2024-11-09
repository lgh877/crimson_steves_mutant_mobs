
package net.crimsonsteve.simplemutantmobs.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.crimsonsteve.simplemutantmobs.entity.model.WitheredHopkeletonModel;
import net.crimsonsteve.simplemutantmobs.entity.WitheredHopkeletonEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class WitheredHopkeletonRenderer extends GeoEntityRenderer<WitheredHopkeletonEntity> {
	public WitheredHopkeletonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new WitheredHopkeletonModel());
		this.shadowRadius = 0.6f;
	}

	@Override
	public RenderType getRenderType(WitheredHopkeletonEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, WitheredHopkeletonEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		float scale = 1.2f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
