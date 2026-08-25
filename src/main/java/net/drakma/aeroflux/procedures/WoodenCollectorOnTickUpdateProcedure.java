package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.init.AerofluxModFluids;
import net.drakma.aeroflux.configuration.AeroFluxConfigurationConfiguration;

public class WoodenCollectorOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double SunAngle = 0;
		double AmountSent = 0;
		double GenerationAmount = 0;
		String OutputSide = "";
		Vec3 OutputBlock = Vec3.ZERO;
		Direction OutputDirection = Direction.NORTH;
		SetMachineCapabilitiesProcedure.execute(world, x, y, z, false, true, false, true);
		SunAngle = GetCelestialAngleProcedure.execute(world) * 2 * 3.14159;
		OutputDirection = ConvertSideToDirectionProcedure.execute(world, x, y, z, getBlockNBTString(world, BlockPos.containing(x, y, z), "OutputSide"));
		OutputBlock = GetBlockInDirectionProcedure.execute(x, y, z, OutputDirection);
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putBoolean("CanSeeSky", (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (AeroFluxConfigurationConfiguration.RAIN_HALVES_GENERATION.get() && (IsRainingProcedure.execute(world) || IsStormingProcedure.execute(world))) {
			GenerationAmount = (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get() / 2;
			if (GenerationAmount < 1) {
				GenerationAmount = 1;
			}
		} else {
			GenerationAmount = (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get();
		}
		if (GetCelestialAngleProcedure.execute(world) > 0.26 && GetCelestialAngleProcedure.execute(world) < 0.74 || CantSeeSkyProcedure.execute(world, x, y, z)) {
			GenerationAmount = 0;
		}
		if (!getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") && !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RedstonePowered")) {
			GenerationAmount = 0;
		}
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("GenerationPerMinute", (GenerationAmount * (60 / (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxProgress") / 20))));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") || !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RedstonePowered")) {
			if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y + 1.1, z))) {
				if (Math.cos(SunAngle) > 0) {
					if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Progress") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxProgress")) {
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("Progress", (new Object() {
									public double change(Object _obj) {
										if (_obj instanceof Integer _i)
											return _i + 1;
										if (_obj instanceof Long _l)
											return _l + 1;
										if (_obj instanceof Float _f)
											return _f + 1.0f;
										if (_obj instanceof Double _d)
											return _d + 1.0d;
										if (_obj instanceof Number _n)
											return _n.doubleValue() + 1;
										return 0;
									}
								}.change((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Progress")))));
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
					} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "Progress") == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxProgress")) {
						if (fillTankSimulate(world, BlockPos.containing(x, y, z), (int) GenerationAmount, null, AerofluxModFluids.AERO_FLUX_FLUID.get()) > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), null) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) GenerationAmount;
									if (_fillAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
						}
						if (!world.isClientSide()) {
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null) {
								_blockEntity.getPersistentData().putDouble("Progress", 0);
							}
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						BlockPos _blockPos24 = BlockPos.containing((int) x, (int) y, (int) z);
						BlockState _blockState24 = world.getBlockState(_blockPos24);
						BlockEntity _blockEnt24 = world.getBlockEntity(_blockPos24);
						if (_blockEnt24 != null)
							_blockEnt24.setChanged();
						((Level) world).sendBlockUpdated(_blockPos24, _blockState24, _blockState24, 3);
						if (world instanceof Level _level)
							_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
					}
				}
			}
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AutoExport")) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "OutputProgress") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxOutputProgress")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("OutputProgress", (new Object() {
								public double change(Object _obj) {
									if (_obj instanceof Integer _i)
										return _i + 1;
									if (_obj instanceof Long _l)
										return _l + 1;
									if (_obj instanceof Float _f)
										return _f + 1.0f;
									if (_obj instanceof Double _d)
										return _d + 1.0d;
									if (_obj instanceof Number _n)
										return _n.doubleValue() + 1;
									return 0;
								}
							}.change((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "OutputProgress")))));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "OutputProgress") == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxOutputProgress")) {
					if (getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, OutputDirection) >= (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get()) {
						AmountSent = fillTankSimulate(world, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (int) (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get(), (OutputDirection.getOpposite()),
								AerofluxModFluids.AERO_FLUX_FLUID.get());
						if (AmountSent > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (OutputDirection.getOpposite())) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) AmountSent;
									if (_fillAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), OutputDirection) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _drainAmount = (int) AmountSent;
									if (_drainAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							BlockPos _blockPos48 = BlockPos.containing((int) x, (int) y, (int) z);
							BlockState _blockState48 = world.getBlockState(_blockPos48);
							BlockEntity _blockEnt48 = world.getBlockEntity(_blockPos48);
							if (_blockEnt48 != null)
								_blockEnt48.setChanged();
							((Level) world).sendBlockUpdated(_blockPos48, _blockState48, _blockState48, 3);
							if (world instanceof Level _level)
								_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("OutputProgress", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		}
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
		return false;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static int fillTankSimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction, Fluid fluid) {
		if (amount > 0 && level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler) {
				try (var tx = Transaction.openRoot()) {
					return fluidHandler.insert(FluidResource.of(fluid), amount, tx);
				}
			}
		}
		return 0;
	}

	private static int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return net.neoforged.neoforge.transfer.fluid.FluidUtil.getStack(fluidHandler, tank).amount();
		}
		return 0;
	}
}