package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class DwellerThingOnEntityTickUpdateProcedure {
	public static void execute(double x, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.tickCount % 40 == 0) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 8, 0, false, false));
			if ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null) {
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(10);
			} else {
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED).setBaseValue(
						Math.min(GetDistanceToTargetFlatProcedure.execute(x, (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX(), z, (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ()), 10));
			}
		}
	}
}
