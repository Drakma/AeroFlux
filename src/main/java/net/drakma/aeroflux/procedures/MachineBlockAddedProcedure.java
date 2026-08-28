package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.ProblemReporter;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.configuration.AeroFluxConfigurationConfiguration;

public class MachineBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		CompoundTag Settings = new CompoundTag();
		CompoundTag Directions = new CompoundTag();
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("MaxProgress", ((double) AeroFluxConfigurationConfiguration.STONE_MAX_TICK.get()));
				_blockEntity.getPersistentData().putDouble("Progress", 0);
				_blockEntity.getPersistentData().putDouble("MaxOutputProgress", ((double) AeroFluxConfigurationConfiguration.STONE_MAX_TICK.get()));
				_blockEntity.getPersistentData().putDouble("OutputProgress", 0);
				_blockEntity.getPersistentData().putString("OutputSide", "DOWN");
				_blockEntity.getPersistentData().putBoolean("CanSeeSky", true);
				_blockEntity.getPersistentData().putBoolean("AlwaysOn", true);
				_blockEntity.getPersistentData().putBoolean("RedstonePowered", false);
				_blockEntity.getPersistentData().putBoolean("AutoExport", true);
				_blockEntity.getPersistentData().putString("RedstoneSignal", "OFF");
				_blockEntity.getPersistentData().putString("TransferSettings", "EXPORT");
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		Directions.put("north", ByteTag.valueOf(false));
		Directions.put("south", ByteTag.valueOf(false));
		Directions.put("east", ByteTag.valueOf(false));
		Directions.put("west", ByteTag.valueOf(false));
		Directions.put("up", ByteTag.valueOf(false));
		Directions.put("down", ByteTag.valueOf(false));
		Settings.put("Input", Directions.copy());
		if (world.getBlockEntity(BlockPos.containing((int) x, (int) y, (int) z)) instanceof BlockEntity _blockEnt26)
			_blockEnt26.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), Settings));
		BlockPos _blockPos27 = BlockPos.containing((int) x, (int) y, (int) z);
		BlockState _blockState27 = world.getBlockState(_blockPos27);
		BlockEntity _blockEnt27 = world.getBlockEntity(_blockPos27);
		if (_blockEnt27 != null)
			_blockEnt27.setChanged();
		((Level) world).sendBlockUpdated(_blockPos27, _blockState27, _blockState27, 3);
	}
}