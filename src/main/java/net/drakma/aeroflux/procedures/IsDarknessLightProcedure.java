package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class IsDarknessLightProcedure {
	public static boolean execute(LevelAccessor world) {
		if ((AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS).equals("LIGHT")) {
			return true;
		}
		return false;
	}
}