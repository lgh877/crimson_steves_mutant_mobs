
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.crimsonsteve.simplemutantmobs.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.crimsonsteve.simplemutantmobs.client.renderer.MutantSkeletonRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CrimsonstevesMutantMobsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CrimsonstevesMutantMobsModEntities.MUTANT_SKELETON.get(), MutantSkeletonRenderer::new);
	}
}
