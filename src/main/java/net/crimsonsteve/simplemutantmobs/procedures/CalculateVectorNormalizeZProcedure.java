package net.crimsonsteve.simplemutantmobs.procedures;

public class CalculateVectorNormalizeZProcedure {
	public static double execute(double x1, double y1, double z1) {
		return z1 / Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
	}
}
