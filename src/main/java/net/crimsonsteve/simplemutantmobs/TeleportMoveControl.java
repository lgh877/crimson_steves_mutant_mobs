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

import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.Mob;
import net.minecraft.core.BlockPos;

public class TeleportMoveControl extends MoveControl {
	public TeleportMoveControl(Mob mob) {
		super(mob);
	}

	public void tick() {
		int modCount = 40;
		if (mob.tickCount % modCount == 2) {
			super.tick();
		} else {
			if (mob.tickCount % modCount == 3) {
				mob.setDeltaMovement(0, 0, 0);
				BlockPos blockpos = mob.blockPosition();
				for (; blockpos.getY() > mob.level().getMinBuildHeight() && mob.level().getBlockState(blockpos).isAir(); blockpos = blockpos.below()) {
				}
				mob.moveTo(mob.getX(), blockpos.getY(), mob.getZ());
			}
			mob.setSpeed(0);
		}
	}
}
