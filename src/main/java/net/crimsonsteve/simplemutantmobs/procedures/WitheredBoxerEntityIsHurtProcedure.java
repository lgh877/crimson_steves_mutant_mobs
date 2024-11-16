package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

public class WitheredBoxerEntityIsHurtProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 3 || ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 4) {
			return false;
		}
		return true;
	}
}
