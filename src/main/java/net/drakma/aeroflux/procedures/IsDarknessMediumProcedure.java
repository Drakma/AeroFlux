package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class IsDarknessMediumProcedure {
	public static boolean execute(LevelAccessor world) {
		if ((AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS).equals("MEDIUM")) {
			return true;
		}
		return false;
	}
}