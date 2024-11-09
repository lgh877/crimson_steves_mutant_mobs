
package net.crimsonsteve.simplemutantmobs.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.crimsonsteve.simplemutantmobs.entity.model.HopkeletonModel;
import net.crimsonsteve.simplemutantmobs.entity.HopkeletonEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class HopkeletonRenderer extends GeoEntityRenderer<HopkeletonEntity> {
	public HopkeletonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new HopkeletonModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(HopkeletonEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1f, 1f, 1f);
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
