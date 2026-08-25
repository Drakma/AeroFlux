package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

public class SetMachineCapabilitiesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, boolean UsesEnergy, boolean UsesFluid, boolean UsesItems, boolean UsesRedstone) {
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("UsesRedstone", UsesRedstone);
				_blockEntity.getPersistentData().putBoolean("UsesFluid", UsesFluid);
				_blockEntity.getPersistentData().putBoolean("UsesItems", UsesItems);
				_blockEntity.getPersistentData().putBoolean("UsesEnergy", UsesEnergy);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}
}