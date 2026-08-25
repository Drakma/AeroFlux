package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GenerationPerMinuteProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GenerationPerMinute") >= 1000) {
			return new java.text.DecimalFormat("##.# B/min").format(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GenerationPerMinute") / 1000);
		}
		return new java.text.DecimalFormat("## mB/min").format(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "GenerationPerMinute"));
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}