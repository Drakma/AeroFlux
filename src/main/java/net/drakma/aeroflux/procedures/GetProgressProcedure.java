package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class GetProgressProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		return new java.text.DecimalFormat("##%").format(getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Progress") / getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxProgress"));
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}
}