package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class TankBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof Level _level)
			_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		BlockPos _blockPos1 = BlockPos.containing((int) x, (int) y, (int) z);
		BlockState _blockState1 = world.getBlockState(_blockPos1);
		BlockEntity _blockEnt1 = world.getBlockEntity(_blockPos1);
		if (_blockEnt1 != null)
			_blockEnt1.setChanged();
		((Level) world).sendBlockUpdated(_blockPos1, _blockState1, _blockState1, 3);
	}
}