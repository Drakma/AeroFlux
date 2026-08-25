package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class SettingsEnergyToggleProcedure {
	public static void execute(LevelAccessor world) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_ENERGY_SETTINGS) {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_ENERGY_SETTINGS = false;
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		} else {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_ENERGY_SETTINGS = true;
			AerofluxModVariables.MapVariables.get(world).GLOBAL_FLUID_SETTINGS = false;
			AerofluxModVariables.MapVariables.get(world).GLOBAL_ITEM_SETTINGS = false;
			AerofluxModVariables.MapVariables.get(world).GLOBAL_REDSTONE_SETTINGS = false;
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		}
	}
}