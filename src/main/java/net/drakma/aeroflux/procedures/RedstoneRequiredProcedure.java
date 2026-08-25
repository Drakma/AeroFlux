package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.network.AerofluxModVariables;

public class RedstoneRequiredProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (AerofluxModVariables.MapVariables.get(world).GLOBAL_REDSTONE_SETTINGS) {
			return !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn");
		}
		return false;
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}
}