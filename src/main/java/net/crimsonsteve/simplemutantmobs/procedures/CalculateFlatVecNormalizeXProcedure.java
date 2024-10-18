package net.crimsonsteve.simplemutantmobs.procedures;

public class CalculateFlatVecNormalizeXProcedure {
	public static double execute(double x1, double z1) {
		return x1 / Math.sqrt(x1 * x1 + z1 * z1);
	}
}
