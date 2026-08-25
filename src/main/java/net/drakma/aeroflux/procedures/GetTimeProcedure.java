package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.client.renderer.item.properties.numeric.Time;

public class GetTimeProcedure {
	public static String execute(LevelAccessor world) {
		double Time = 0;
		double Hour = 0;
		String AMPM = "";
		Time = world instanceof Level _level0 ? _level0.getDefaultClockTime() : 0;
		Hour = Math.floor(((Time % 24000) / 1000 + 6) % 24);
		if (Hour >= 0 && Hour < 12) {
			AMPM = "am";
		} else {
			AMPM = "pm";
		}
		if (Hour > 12) {
			Hour = Hour - 12;
		}
		return new java.text.DecimalFormat("Day ##").format(Math.ceil(Time / 24000)) + "" + new java.text.DecimalFormat(" ##:").format(Hour) + new java.text.DecimalFormat("00").format(Math.floor((Time % 1000) * 0.06)) + AMPM;
	}
}