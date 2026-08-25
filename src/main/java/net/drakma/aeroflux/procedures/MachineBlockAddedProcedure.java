package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.configuration.AeroFluxConfigurationConfiguration;

import de.maxhenkel.pipez.corelib.tag.ItemTag;
import de.maxhenkel.pipez.corelib.tag.FluidTag;
import de.maxhenkel.pipez.corelib.tag.BlockTag;

public class MachineBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		CompoundTag TagTemplate = new CompoundTag();
		CompoundTag InTemplate = new CompoundTag();
		CompoundTag OutTemplate = new CompoundTag();
		CompoundTag BlockTag = new CompoundTag();
		CompoundTag FluidTag = new CompoundTag();
		CompoundTag ItemTag = new CompoundTag();
		CompoundTag EnergyTag = new CompoundTag();
		CompoundTag RedstoneTag = new CompoundTag();
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
	}
}