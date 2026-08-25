package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class IsSunsetProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((world instanceof Level _level0 ? _level0.getDefaultClockTime() : 0) % 24000 > 12000 && (world instanceof Level _level1 ? _level1.getDefaultClockTime() : 0) % 24000 <= 13000 && !CantSeeSkyProcedure.execute(world, x, y, z)) {
			return true;
		}
		return false;
	}
}