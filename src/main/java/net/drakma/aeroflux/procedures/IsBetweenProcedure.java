package net.drakma.aeroflux.procedures;

public class IsBetweenProcedure {
	public static boolean execute(double max, double min, double value) {
		if (value >= min && value <= max) {
			return true;
		}
		return false;
	}
}