package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class SettingsToggleProcedure {
	public static void execute(LevelAccessor world) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_SETTINGS_PANEL) {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_SETTINGS_PANEL = false;
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		} else {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_SETTINGS_PANEL = true;
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		}
	}
}