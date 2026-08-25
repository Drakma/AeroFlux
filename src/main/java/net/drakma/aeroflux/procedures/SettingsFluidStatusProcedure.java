package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class SettingsFluidStatusProcedure {
	public static boolean execute(LevelAccessor world) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_FLUID_SETTINGS) {
			return true;
		}
		return false;
	}
}