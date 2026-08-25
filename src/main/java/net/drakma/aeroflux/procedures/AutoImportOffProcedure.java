package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class AutoImportOffProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_FLUID_SETTINGS && (getBlockNBTString(world, BlockPos.containing(x, y, z), "TransferSettings")).equals("IMPORT")) {
			return !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AutoImport");
		}
		return false;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}