package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.ProblemReporter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.init.AerofluxModBlocks;
import net.drakma.aeroflux.configuration.AeroFluxConfigurationConfiguration;

public class TankBlockNewAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Direction StartingDirection = Direction.NORTH;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("MaxProgress", ((double) AeroFluxConfigurationConfiguration.WOODEN_MAX_TICK.get()));
				_blockEntity.getPersistentData().putDouble("Progress", 0);
				_blockEntity.getPersistentData().putDouble("MaxOutputProgress", ((double) AeroFluxConfigurationConfiguration.WOODEN_MAX_TICK.get()));
				_blockEntity.getPersistentData().putDouble("OutputProgress", 0);
				_blockEntity.getPersistentData().putDouble("MaxInputProgress", ((double) AeroFluxConfigurationConfiguration.WOODEN_MAX_TICK.get()));
				_blockEntity.getPersistentData().putDouble("InputProgress", 0);
				_blockEntity.getPersistentData().putString("OutputSide", "");
				_blockEntity.getPersistentData().putString("InputSide", "");
				_blockEntity.getPersistentData().putBoolean("AlwaysOn", true);
				_blockEntity.getPersistentData().putBoolean("RedstonePowered", false);
				_blockEntity.getPersistentData().putBoolean("AutoExport", false);
				_blockEntity.getPersistentData().putBoolean("AutoImport", false);
				_blockEntity.getPersistentData().putString("TransferSettings", "EXPORT");
				_blockEntity.getPersistentData().putString("RedstoneSignal", "OFF");
				_blockEntity.getPersistentData().putDouble("level", 0);
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		BlockPos _blockPos18 = BlockPos.containing((int) x, (int) y, (int) z);
		BlockState _blockState18 = world.getBlockState(_blockPos18);
		BlockEntity _blockEnt18 = world.getBlockEntity(_blockPos18);
		if (_blockEnt18 != null)
			_blockEnt18.setChanged();
		((Level) world).sendBlockUpdated(_blockPos18, _blockState18, _blockState18, 3);
		if (world instanceof Level _level)
			_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
		{
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockState _bs = AerofluxModBlocks.WOODEN_TANK.get().defaultBlockState();
			BlockState _bso = world.getBlockState(_bp);
			for (Property<?> _propertyOld : _bso.getProperties()) {
				Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
				if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
					try {
						_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
					} catch (Exception e) {
					}
			}
			BlockEntity _be = world.getBlockEntity(_bp);
			CompoundTag _bnbt = null;
			if (_be != null) {
				_bnbt = _be.saveWithFullMetadata(world.registryAccess());
				_be.setRemoved();
			}
			world.setBlock(_bp, _bs, 3);
			if (_bnbt != null) {
				_be = world.getBlockEntity(_bp);
				if (_be != null) {
					try {
						_be.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, world.registryAccess(), _bnbt));
					} catch (Exception ignored) {
					}
				}
			}
		}
	}
}