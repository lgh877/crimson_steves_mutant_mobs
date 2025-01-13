package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

import java.util.List;
import java.util.Comparator;

public class BasicAnimAttackProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double attackEndTime, double attackOccurTime, double damage, double hitBox) {
		if (entity == null)
			return;
		double actionTicks = 0;
		double actionProgress = 0;
		String currentAnimation = "";
		if (entity.getPersistentData().getDouble("actionTicks") > attackOccurTime && entity.getPersistentData().getDouble("actionProgress") == 0) {
			entity.getPersistentData().putDouble("actionProgress", 1);
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(hitBox / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (!(entityiterator == entity)) {
						entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), entity), (float) damage);
					}
				}
			}
		} else if (entity.getPersistentData().getDouble("actionTicks") > attackEndTime && entity.getPersistentData().getDouble("actionProgress") == 1) {
			((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).setBaseValue(0);
			entity.getPersistentData().putDouble("actionTicks", 0);
			entity.getPersistentData().putDouble("actionProgress", 0);
		}
	}
}
