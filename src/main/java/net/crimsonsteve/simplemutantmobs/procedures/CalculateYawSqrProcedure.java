package net.crimsonsteve.simplemutantmobs.procedures;

import net.minecraft.util.Mth;

public class CalculateYawSqrProcedure {
	public static double execute(double x0, double x1, double z0, double z1) {
		double x = 0;
		double z = 0;
		x = x1 - x0;
		z = z1 - z0;
		return Mth.atan2(z, x) * (180 / Math.PI) - 90;
	}
}
