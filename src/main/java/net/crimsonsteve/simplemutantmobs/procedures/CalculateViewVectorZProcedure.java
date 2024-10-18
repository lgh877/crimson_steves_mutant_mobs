package net.crimsonsteve.simplemutantmobs.procedures;

public class CalculateViewVectorZProcedure {
	public static double execute(double wantedPitch, double wantedYaw) {
		double f = 0;
		double f1 = 0;
		double f2 = 0;
		double f3 = 0;
		double f4 = 0;
		double f5 = 0;
		f = wantedYaw * (Math.PI / 180);
		f1 = wantedPitch * (Math.PI / (-180));
		f2 = Math.cos(f1);
		f3 = Math.sin(f1);
		f4 = Math.cos(f);
		f5 = Math.sin(f);
		return f2 * f4;
	}
}
