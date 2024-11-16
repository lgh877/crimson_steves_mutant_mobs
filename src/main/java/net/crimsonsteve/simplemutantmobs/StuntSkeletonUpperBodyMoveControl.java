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

public class StuntSkeletonUpperBodyMoveControl extends MoveControl {
	public StuntSkeletonUpperBodyMoveControl(Mob mob) {
		super(mob);
	}

	//long tick;
	public void tick() {
		int modCount = 6;
		if (mob.tickCount % modCount == 2) {
			super.tick();
		} else {
			mob.setSpeed(0);
		}
	}
}
