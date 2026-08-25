package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class IsSettingsLightProcedure {
	public static boolean execute(LevelAccessor world) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_REDSTONE_SETTINGS || AerofluxModVariables.MapVariables.get(world).GLOBAL_FLUID_SETTINGS || AerofluxModVariables.MapVariables.get(world).GLOBAL_ITEM_SETTINGS
				|| AerofluxModVariables.MapVariables.get(world).GLOBAL_ENERGY_SETTINGS) {
			if ((AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS).equals("LIGHT")) {
				return true;
			}
		}
		return false;
	}
}