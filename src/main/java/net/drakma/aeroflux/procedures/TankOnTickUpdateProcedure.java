package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.init.AerofluxModItems;
import net.drakma.aeroflux.init.AerofluxModFluids;
import net.drakma.aeroflux.configuration.AeroFluxConfigurationConfiguration;

public class TankOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double TankPercentage = 0;
		double OldLevel = 0;
		double NewLevel = 0;
		CompoundTag OldBlockstate = new CompoundTag();
		Direction OutputDirection = Direction.NORTH;
		Direction InputDirection = Direction.NORTH;
		Vec3 OutputBlock = Vec3.ZERO;
		Vec3 InputBlock = Vec3.ZERO;
		OutputDirection = ConvertSideToDirectionProcedure.execute(world, x, y, z, getBlockNBTString(world, BlockPos.containing(x, y, z), "OutputSide"));
		InputDirection = ConvertSideToDirectionProcedure.execute(world, x, y, z, getBlockNBTString(world, BlockPos.containing(x, y, z), "InputSide"));
		OutputBlock = GetBlockInDirectionProcedure.execute(x, y, z, OutputDirection);
		InputBlock = GetBlockInDirectionProcedure.execute(x, y, z, InputDirection);
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") || !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RedstonePowered")) {
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
						TankPercentage = fillTankSimulate(world, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (int) (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get(), (OutputDirection.getOpposite()),
								AerofluxModFluids.AERO_FLUX_FLUID.get());
						if (TankPercentage > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (OutputDirection.getOpposite())) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) TankPercentage;
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
									int _drainAmount = (int) TankPercentage;
									if (_drainAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							BlockPos _blockPos27 = BlockPos.containing((int) x, (int) y, (int) z);
							BlockState _blockState27 = world.getBlockState(_blockPos27);
							BlockEntity _blockEnt27 = world.getBlockEntity(_blockPos27);
							if (_blockEnt27 != null)
								_blockEnt27.setChanged();
							((Level) world).sendBlockUpdated(_blockPos27, _blockState27, _blockState27, 3);
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
			} else if ((ConvertSideToDirectionProcedure.execute(world, OutputBlock.x(), OutputBlock.y(), OutputBlock.z(), getBlockNBTString(world, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), "InputSide"))
					.getOpposite()) == OutputDirection) {
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
						TankPercentage = fillTankSimulate(world, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (int) (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get(), (OutputDirection.getOpposite()),
								AerofluxModFluids.AERO_FLUX_FLUID.get());
						if (TankPercentage > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(OutputBlock.x(), OutputBlock.y(), OutputBlock.z()), (OutputDirection.getOpposite())) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) TankPercentage;
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
									int _drainAmount = (int) TankPercentage;
									if (_drainAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							BlockPos _blockPos60 = BlockPos.containing((int) x, (int) y, (int) z);
							BlockState _blockState60 = world.getBlockState(_blockPos60);
							BlockEntity _blockEnt60 = world.getBlockEntity(_blockPos60);
							if (_blockEnt60 != null)
								_blockEnt60.setChanged();
							((Level) world).sendBlockUpdated(_blockPos60, _blockState60, _blockState60, 3);
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
			if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AutoImport")) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxInputProgress")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("InputProgress", (new Object() {
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
							}.change((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress")))));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress") == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxInputProgress")) {
					if (getFluidTankLevel(world, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), 0, (InputDirection.getOpposite())) >= (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get()) {
						TankPercentage = fillTankSimulate(world, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), (int) (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get(), (InputDirection.getOpposite()),
								AerofluxModFluids.AERO_FLUX_FLUID.get());
						if (TankPercentage > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), (InputDirection.getOpposite())) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _drainAmount = (int) TankPercentage;
									if (_drainAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), InputDirection) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) TankPercentage;
									if (_fillAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							BlockPos _blockPos89 = BlockPos.containing((int) x, (int) y, (int) z);
							BlockState _blockState89 = world.getBlockState(_blockPos89);
							BlockEntity _blockEnt89 = world.getBlockEntity(_blockPos89);
							if (_blockEnt89 != null)
								_blockEnt89.setChanged();
							((Level) world).sendBlockUpdated(_blockPos89, _blockState89, _blockState89, 3);
							if (world instanceof Level _level)
								_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("InputProgress", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			} else if ((ConvertSideToDirectionProcedure.execute(world, InputBlock.x(), InputBlock.y(), InputBlock.z(), getBlockNBTString(world, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), "OutputSide"))
					.getOpposite()) == InputDirection) {
				if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress") < getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaxInputProgress")) {
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("InputProgress", (new Object() {
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
							}.change((getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress")))));
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				} else if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "InputProgress") == getBlockNBTNumber(world, BlockPos.containing(x, y, z), "MaInputProgress")) {
					if (getFluidTankLevel(world, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), 0, (InputDirection.getOpposite())) >= (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get()) {
						TankPercentage = fillTankSimulate(world, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), (int) (double) AeroFluxConfigurationConfiguration.WOODEN_MB_FILLED.get(), (InputDirection.getOpposite()),
								AerofluxModFluids.AERO_FLUX_FLUID.get());
						if (TankPercentage > 0) {
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(InputBlock.x(), InputBlock.y(), InputBlock.z()), (InputDirection.getOpposite())) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _drainAmount = (int) TankPercentage;
									if (_drainAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							if (world instanceof ILevelExtension _ext) {
								if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), InputDirection) instanceof ResourceHandler<FluidResource> _fluidHandler) {
									int _fillAmount = (int) TankPercentage;
									if (_fillAmount > 0) {
										try (var _tx = Transaction.openRoot()) {
											_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
											_tx.commit();
										}
									}
								}
							}
							BlockPos _blockPos126 = BlockPos.containing((int) x, (int) y, (int) z);
							BlockState _blockState126 = world.getBlockState(_blockPos126);
							BlockEntity _blockEnt126 = world.getBlockEntity(_blockPos126);
							if (_blockEnt126 != null)
								_blockEnt126.setChanged();
							((Level) world).sendBlockUpdated(_blockPos126, _blockState126, _blockState126, 3);
							if (world instanceof Level _level)
								_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
						}
					}
					if (!world.isClientSide()) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockEntity _blockEntity = world.getBlockEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_blockEntity != null) {
							_blockEntity.getPersistentData().putDouble("InputProgress", 0);
						}
						if (world instanceof Level _level)
							_level.sendBlockUpdated(_bp, _bs, _bs, 3);
					}
				}
			}
		}
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Items.BUCKET && (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem()
				&& getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) >= 1000) {
			extractFromBlockInventory(world, BlockPos.containing(x, y, z), 0, 1, false);
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					ItemStack _setstack = new ItemStack(AerofluxModItems.AERO_FLUX_FLUID_BUCKET.get()).copy();
					_setstack.setCount(1);
					_container.setItem(1, _setstack);
				}
			}
			if (world instanceof ILevelExtension _ext) {
				if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), null) instanceof ResourceHandler<FluidResource> _fluidHandler) {
					int _drainAmount = 1000;
					if (_drainAmount > 0) {
						try (var _tx = Transaction.openRoot()) {
							ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
							_tx.commit();
						}
					}
				}
			}
		} else if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == AerofluxModItems.AERO_FLUX_FLUID_BUCKET.get()
				&& (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()).getItem() == Blocks.AIR.asItem()
				&& getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null) - getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) >= 1000) {
			extractFromBlockInventory(world, BlockPos.containing(x, y, z), 0, 1, false);
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					ItemStack _setstack = new ItemStack(Items.BUCKET).copy();
					_setstack.setCount(1);
					_container.setItem(1, _setstack);
				}
			}
			if (world instanceof ILevelExtension _ext) {
				if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), null) instanceof ResourceHandler<FluidResource> _fluidHandler) {
					int _fillAmount = 1000;
					if (_fillAmount > 0) {
						try (var _tx = Transaction.openRoot()) {
							_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
							_tx.commit();
						}
					}
				}
			}
		}
		TankPercentage = 100 * ((double) getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) / getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null));
		OldLevel = getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "level") instanceof IntegerProperty _getip149 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip149) : -1;
		if (TankPercentage == 0) {
			NewLevel = 0;
		} else if (TankPercentage > 0 && TankPercentage <= 5) {
			NewLevel = 1;
		} else if (TankPercentage > 5 && TankPercentage <= 10) {
			NewLevel = 2;
		} else if (TankPercentage > 10 && TankPercentage <= 20) {
			NewLevel = 3;
		} else if (TankPercentage > 20 && TankPercentage <= 30) {
			NewLevel = 4;
		} else if (TankPercentage > 30 && TankPercentage <= 40) {
			NewLevel = 5;
		} else if (TankPercentage > 40 && TankPercentage <= 50) {
			NewLevel = 6;
		} else if (TankPercentage > 50 && TankPercentage <= 60) {
			NewLevel = 7;
		} else if (TankPercentage > 60 && TankPercentage <= 70) {
			NewLevel = 8;
		} else if (TankPercentage > 70 && TankPercentage <= 80) {
			NewLevel = 9;
		} else if (TankPercentage > 80 && TankPercentage <= 90) {
			NewLevel = 10;
		} else if (TankPercentage > 90 && TankPercentage < 100) {
			NewLevel = 11;
		} else if (TankPercentage == 100) {
			NewLevel = 12;
		}
		if (OldLevel != NewLevel) {
			{
				int _value = (int) NewLevel;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("level") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			BlockPos _blockPos151 = BlockPos.containing((int) x, (int) y, (int) z);
			BlockState _blockState151 = world.getBlockState(_blockPos151);
			BlockEntity _blockEnt151 = world.getBlockEntity(_blockPos151);
			if (_blockEnt151 != null)
				_blockEnt151.setChanged();
			((Level) world).sendBlockUpdated(_blockPos151, _blockState151, _blockState151, 3);
			if (world instanceof Level _level)
				_level.updateNeighborsAt(BlockPos.containing(x, y, z), _level.getBlockState(BlockPos.containing(x, y, z)).getBlock());
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

	private static int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return net.neoforged.neoforge.transfer.fluid.FluidUtil.getStack(fluidHandler, tank).amount();
		}
		return 0;
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

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static ItemStack extractFromBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemResource extractedResource = itemHandler.getResource(slotId);
				ItemStack extractedStack = ItemStack.EMPTY;
				if (extractedResource.isEmpty() || amount < 0)
					return extractedStack;
				try (var tx = Transaction.openRoot()) {
					int extracted = itemHandler.extract(slotId, extractedResource, amount, tx);
					extractedStack = extractedResource.toStack(extracted);
					if (!simulate)
						tx.commit();
				}
				return extractedStack;
			}
		}
		return ItemStack.EMPTY;
	}

	private static int getFluidTankCapacity(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return fluidHandler.getCapacityAsInt(tank, FluidResource.EMPTY);
		}
		return 0;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}