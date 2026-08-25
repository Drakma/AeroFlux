package net.drakma.aeroflux.procedures;

import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.init.AerofluxModParticleTypes;

public class CollectorOnClientTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double SunAngle = 0;
		double Distance = 0;
		double ParticleSpeed = 0;
		double PartialTick = 0;
		double CelestialAngle = 0;
		CelestialAngle = GetCelestialAngleProcedure.execute(world);
		SunAngle = CelestialAngle * 2 * 3.14159;
		Distance = 9;
		ParticleSpeed = 0.25;
		if (getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") || !getBlockNBTLogic(world, BlockPos.containing(x, y, z), "AlwaysOn") && getBlockNBTLogic(world, BlockPos.containing(x, y, z), "RedstonePowered")) {
			if (getFluidTankLevel(world, BlockPos.containing(x, y, z), 0, null) < getFluidTankCapacity(world, BlockPos.containing(x, y, z), 0, null)) {
				if (!CantSeeSkyProcedure.execute(world, x, y, z)) {
					if (Math.cos(SunAngle) > 0) {
						world.addParticle((SimpleParticleType) (AerofluxModParticleTypes.FLUX_PARTICLE.get()), ((x + 0.5) - Math.sin(SunAngle) * Distance), (y + 1 + Math.cos(SunAngle) * Distance), (z + 0.5), (Math.sin(SunAngle) * ParticleSpeed),
								(0 - Math.cos(SunAngle) * ParticleSpeed), 0);
					}
				}
			}
		}
	}

	private static boolean getBlockNBTLogic(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getBooleanOr(tag, false);
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