/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.crimsonsteve.simplemutantmobs as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.crimsonsteve.simplemutantmobs;

public class IsEntityInSightMethod {
	/*
		public static boolean isEntityInSight(Player player, Entity targetEntity, double maxDistance) {
			Vec3 eyePosition = player.getEyePosition(1.0F);
			Vec3 lookVector = player.getLookAngle().scale(maxDistance);
			Vec3 targetPosition = eyePosition.add(lookVector);
			// Define an axis-aligned bounding box around the player's look vector
			double expandAmount = 1.0D;
			Level world = player.level();
			// Use ProjectileUtil to trace towards entities
			EntityHitResult result = ProjectileUtil.getEntityHitResult(world, player, eyePosition, targetPosition, player.getBoundingBox().inflate(maxDistance, maxDistance, maxDistance), entity -> entity.equals(targetEntity) && !entity.isSpectator());
			// Check if the ray hits the target entity
			return result != null && result.getEntity().equals(targetEntity);
		}
		public static boolean isEntityOnScreen(Entity entity) {
			Minecraft mc = Minecraft.getInstance();
			Camera camera = mc.gameRenderer.getMainCamera();
			// Get camera (player's) position and orientation
			Vec3 cameraPosition = camera.getPosition();
			// Set up the frustum using projection and model-view matrices from the RenderSystem
			Matrix4f matrix = new Matrix4f();
			Frustum frustum = new Frustum(matrix.set(camera.rotation()).scale(mc.gameRenderer.getRenderDistance()), RenderSystem.getModelViewMatrix());
			// Prepare the frustum to account for the camera's exact position and orientation
			frustum.prepare(cameraPosition.x, cameraPosition.y, cameraPosition.z);
			// Check if the entity's bounding box (AABB) is within the frustum
			AABB entityBox = entity.getBoundingBoxForCulling();
			return frustum.isVisible(entityBox);
		}
		*/
}
