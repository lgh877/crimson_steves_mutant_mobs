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

import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;

public class CustomMathHelper {
	public static Vec3 calculateViewVector(float p_20172_, float p_20173_) {
		float f = p_20172_ * ((float) Math.PI / 180F);
		float f1 = -p_20173_ * ((float) Math.PI / 180F);
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		float f4 = Mth.cos(f);
		float f5 = Mth.sin(f);
		return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
	}
}
