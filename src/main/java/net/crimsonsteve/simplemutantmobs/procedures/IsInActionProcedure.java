package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.crimsonsteve.simplemutantmobs.init.CrimsonstevesMutantMobsModAttributes;

public class IsInActionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return ((LivingEntity) entity).getAttribute(CrimsonstevesMutantMobsModAttributes.ACTIONSTATE.get()).getBaseValue() == 0;
	}
}
