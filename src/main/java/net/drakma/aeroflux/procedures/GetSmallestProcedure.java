package net.drakma.aeroflux.procedures;

public class GetSmallestProcedure {
	public static double execute(double num1, double num2) {
		if (num1 < num2) {
			return num1;
		}
		return num2;
	}
}