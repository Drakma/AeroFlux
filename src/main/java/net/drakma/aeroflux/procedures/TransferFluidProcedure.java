package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.init.AerofluxModFluids;

public class TransferFluidProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, double mbToSend, double sx, double sy, double sz) {
		double AmountToDrain = 0;
		double AmountCanFill = 0;
		double AmountCanDrain = 0;
		double AmountWillDrain = 0;
		if (getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) > 0) {
			AmountCanDrain = drainTankSimulate(world, BlockPos.containing(x, y, z), (int) mbToSend, null);
			AmountCanFill = getFluidTankCapacity(world, BlockPos.containing(sx, sy, sz), 0, null) - getFluidTankLevel(world, BlockPos.containing(sx, sy, sz), 0, null);
			AmountWillDrain = GetSmallestProcedure.execute(AmountCanDrain, AmountCanFill);
			if (world instanceof ILevelExtension _ext) {
				if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(x, y, z), null) instanceof ResourceHandler<FluidResource> _fluidHandler) {
					int _drainAmount = (int) AmountWillDrain;
					if (_drainAmount > 0) {
						try (var _tx = Transaction.openRoot()) {
							ResourceHandlerUtil.extractFirst(_fluidHandler, _ -> true, _drainAmount, _tx);
							_tx.commit();
						}
					}
				}
			}
			if (world instanceof ILevelExtension _ext) {
				if (_ext.getCapability(Capabilities.Fluid.BLOCK, BlockPos.containing(sx, sy, sz), null) instanceof ResourceHandler<FluidResource> _fluidHandler) {
					int _fillAmount = (int) AmountWillDrain;
					if (_fillAmount > 0) {
						try (var _tx = Transaction.openRoot()) {
							_fluidHandler.insert(FluidResource.of(AerofluxModFluids.AERO_FLUX_FLUID.get()), _fillAmount, _tx);
							_tx.commit();
						}
					}
				}
			}
		}
	}

	private static int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return net.neoforged.neoforge.transfer.fluid.FluidUtil.getStack(fluidHandler, tank).amount();
		}
		return 0;
	}

	private static int drainTankSimulate(LevelAccessor level, BlockPos pos, int amount, Direction direction) {
		if (amount > 0 && level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler) {
				try (var tx = Transaction.openRoot()) {
					return ResourceHandlerUtil.extractFirst(fluidHandler, _ -> true, amount, tx).amount();
				}
			}
		}
		return 0;
	}

	private static int getFluidTankCapacity(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return fluidHandler.getCapacityAsInt(tank, FluidResource.EMPTY);
		}
		return 0;
	}
}