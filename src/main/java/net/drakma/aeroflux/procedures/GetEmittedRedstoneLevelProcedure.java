package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class GetEmittedRedstoneLevelProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z) {
		double OutputSignal = 0;
		double OldOutputSignal = 0;
		OutputSignal = 0;
		if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "RedstoneSignal")).equals("LEVEL")) {
			OutputSignal = Math.floor(((double) getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) / getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null)) * 15);
		} else if ((getBlockNBTString(world, BlockPos.containing(x, y, z), "RedstoneSignal")).equals("ON")) {
			if ((double) getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) / getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null) >= 1) {
				OutputSignal = 15;
			}
		}
		return OutputSignal;
	}

	private static String getBlockNBTString(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getStringOr(tag, "");
		return "";
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