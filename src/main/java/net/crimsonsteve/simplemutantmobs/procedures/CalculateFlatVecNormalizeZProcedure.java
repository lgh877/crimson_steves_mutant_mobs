package net.crimsonsteve.simplemutantmobs.procedures;

public class CalculateFlatVecNormalizeZProcedure {
	public static double execute(double x1, double z1) {
		return z1 / Math.sqrt(Math.abs(x1 * x1 + z1 * z1));
	}
}
