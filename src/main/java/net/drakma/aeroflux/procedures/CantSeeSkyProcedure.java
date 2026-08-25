package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CantSeeSkyProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (!world.canSeeSkyFromBelowWater(BlockPos.containing(x, y + 1, z))) {
			return true;
		}
		return false;
	}
}