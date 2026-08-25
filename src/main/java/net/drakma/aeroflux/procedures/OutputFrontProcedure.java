package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class OutputFrontProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_SETTINGS_PANEL) {
			if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "TransferSettings")).equals("EXPORT")) {
				if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "OutputSide")).equals("FRONT")) {
					return true;
				}
			}
		}
		return false;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}
}