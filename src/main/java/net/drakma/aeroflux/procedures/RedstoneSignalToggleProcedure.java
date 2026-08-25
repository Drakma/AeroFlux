package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class RedstoneSignalToggleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "RedstoneSignal")).equals("OFF")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString("RedstoneSignal", "ON");
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "RedstoneSignal")).equals("ON")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString("RedstoneSignal", "LEVEL");
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "RedstoneSignal")).equals("LEVEL")) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putString("RedstoneSignal", "OFF");
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if (world instanceof Level _level)
			_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		BlockPos _blockPos7 = BlockPos.containing((int) x, (int) y, (int) z);
		BlockState _blockState7 = world.getBlockState(_blockPos7);
		BlockEntity _blockEnt7 = world.getBlockEntity(_blockPos7);
		if (_blockEnt7 != null)
			_blockEnt7.setChanged();
		((Level) world).sendBlockUpdated(_blockPos7, _blockState7, _blockState7, 3);
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}
}