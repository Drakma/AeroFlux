package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class Filled80Procedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if ((double) getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) / getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null) >= 0.8
				&& (double) getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) / getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null) < 0.9) {
			return true;
		}
		return false;
	}

	private static int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			if (levelExtension.getCapability(Capabilities.Fluid.BLOCK, pos, direction) instanceof ResourceHandler<FluidResource> fluidHandler)
				return net.neoforged.neoforge.transfer.fluid.FluidUtil.getStack(fluidHandler, tank).amount();
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