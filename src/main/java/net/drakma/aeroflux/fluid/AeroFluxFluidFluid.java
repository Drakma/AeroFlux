package net.drakma.aeroflux.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.drakma.aeroflux.init.AerofluxModItems;
import net.drakma.aeroflux.init.AerofluxModFluids;
import net.drakma.aeroflux.init.AerofluxModFluidTypes;
import net.drakma.aeroflux.init.AerofluxModBlocks;

public abstract class AeroFluxFluidFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> AerofluxModFluidTypes.AERO_FLUX_FLUID_TYPE.get(), () -> AerofluxModFluids.AERO_FLUX_FLUID.get(),
			() -> AerofluxModFluids.FLOWING_AERO_FLUX_FLUID.get()).explosionResistance(100f).tickRate(7).bucket(() -> AerofluxModItems.AERO_FLUX_FLUID_BUCKET.get()).block(() -> (LiquidBlock) AerofluxModBlocks.AERO_FLUX_FLUID.get());

	private AeroFluxFluidFluid() {
		super(PROPERTIES);
	}

	public static class Source extends AeroFluxFluidFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends AeroFluxFluidFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}