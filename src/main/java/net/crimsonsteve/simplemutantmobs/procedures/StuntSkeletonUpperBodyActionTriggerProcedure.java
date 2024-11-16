package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class StuntSkeletonUpperBodyActionTriggerProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return "";
		String currentAnimation = "";
		Entity target = null;
		double random = 0;
		if (entity.tickCount % 10 == 0) {
			if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0 && !((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				target = entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null;
				random = Mth.nextInt(RandomSource.create(), 1, 10);
				if (random <= 8) {
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 3) / 2d), e -> true).stream()
								.sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (target == entityiterator) {
								entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ())));
								((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue((Mth.nextInt(RandomSource.create(), 1, 2)));
								entity.getPersistentData().putDouble("dmgMul", 1);
								entity.getPersistentData().putDouble("attackProgress", 0);
								entity.getPersistentData().putDouble("attackTicks", 0);
								currentAnimation = 1 == ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() ? "left_punch_start" : "right_punch_start";
							}
						}
					}
				}
			}
		}
		return currentAnimation;
	}
}
