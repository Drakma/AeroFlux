package net.drakma.aeroflux.procedures;

import org.apache.commons.lang3.math.Fraction;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.item.properties.numeric.Time;

public class GetCelestialAngleProcedure {
	public static double execute(LevelAccessor world) {
		double SunAngle = 0;
		double Fraction = 0;
		double Curve = 0;
		double Time = 0;
		Time = world instanceof Level _level0 ? _level0.getDefaultClockTime() : 0;
		Fraction = Mth.frac((double) Time / 24000.0D - 0.25D);
		Curve = 0.5D - Math.cos(Fraction * Math.PI) / 2.0D;
		SunAngle = (float) ((Fraction * 2.0D + Curve) / 3.0D);
		return SunAngle;
	}
}