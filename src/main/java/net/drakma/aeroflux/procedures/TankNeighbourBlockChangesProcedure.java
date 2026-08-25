package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class TankNeighbourBlockChangesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (0 == 1) {
			BlockPos _blockPos0 = BlockPos.containing((int) x, (int) y, (int) z);
			BlockState _blockState0 = world.getBlockState(_blockPos0);
			BlockEntity _blockEnt0 = world.getBlockEntity(_blockPos0);
			if (_blockEnt0 != null)
				_blockEnt0.setChanged();
			((Level) world).sendBlockUpdated(_blockPos0, _blockState0, _blockState0, 3);
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		}
	}
}