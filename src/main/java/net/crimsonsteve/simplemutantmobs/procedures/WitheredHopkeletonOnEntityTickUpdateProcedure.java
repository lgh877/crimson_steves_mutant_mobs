package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.EntityDamageSource;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;
import net.crimsonsteve.simplemutantmobs.entity.WitheredHopkeletonEntity;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class WitheredHopkeletonOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		String attackTriggerAnimation = "";
		String currentAnimation = "";
		String attackProgressAnimation = "";
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() != 0) {
			if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 2 && !((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				{
					final Vec3 _center = new Vec3(x, (y + entity.getBbWidth() * 0.8), z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((entity.getBbWidth() * 2) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
							if (entityiterator instanceof LivingEntity _entity && !_entity.level.isClientSide())
								_entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 360, 1));
							entityiterator.hurt(new EntityDamageSource("generic", entity), (float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE).getValue());
						}
					}
				}
			}
			((LivingEntity) entity).setYRot(entity.getYHeadRot());
			((LivingEntity) entity).setYBodyRot(entity.getYHeadRot());
		}
		currentAnimation = ((WitheredHopkeletonEntity) entity).animationprocedure;
		if (!world.isClientSide()) {
			attackTriggerAnimation = HopkeletonAttackTriggerProcedure.execute(entity);
			attackProgressAnimation = HopkeletonAttackProgressProcedure.execute(entity);
			currentAnimation = !(attackTriggerAnimation).isEmpty() ? attackTriggerAnimation : currentAnimation;
			currentAnimation = !(attackProgressAnimation).isEmpty() ? attackProgressAnimation : currentAnimation;
		}
		if (entity instanceof WitheredHopkeletonEntity) {
			((WitheredHopkeletonEntity) entity).setAnimation(currentAnimation);
		}
	}
}
