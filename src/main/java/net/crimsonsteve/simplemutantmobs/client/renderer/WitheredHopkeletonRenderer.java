
package net.crimsonsteve.simplemutantmobs.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

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
	public RenderType getRenderType(WitheredHopkeletonEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1.2f, 1.2f, 1.2f);
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
