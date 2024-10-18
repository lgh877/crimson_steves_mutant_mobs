package net.crimsonsteve.simplemutantmobs.procedures;

public class GetDistanceToTargetFlatProcedure {
	public static double execute(double x0, double x1, double z0, double z1) {
		double z2 = 0;
		double x2 = 0;
		x2 = x0 - x1;
		z2 = z0 - z1;
		return Math.sqrt(Math.pow(x2, 2) + Math.pow(z2, 2));
	}
}
