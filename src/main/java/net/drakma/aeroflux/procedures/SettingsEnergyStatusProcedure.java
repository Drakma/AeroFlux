package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class SettingsEnergyStatusProcedure {
	public static boolean execute(LevelAccessor world) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_ENERGY_SETTINGS) {
			return true;
		}
		return false;
	}
}