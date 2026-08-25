package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class DarknessToggleProcedure {
	public static void execute(LevelAccessor world) {
		if ((AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS).equals("LIGHT")) {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS = "MEDIUM";
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		} else if ((AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS).equals("MEDIUM")) {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS = "DARK";
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		} else {
			AerofluxModVariables.MapVariables.get(world).GLOBAL_DARKNESS = "LIGHT";
			AerofluxModVariables.MapVariables.get(world).markSyncDirty();
		}
	}
}