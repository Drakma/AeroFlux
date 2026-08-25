/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.drakma.aeroflux.fluid.types.AeroFluxFluidFluidType;
import net.drakma.aeroflux.AerofluxMod;

public class AerofluxModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AerofluxMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> AERO_FLUX_FLUID_TYPE = REGISTRY.register("aero_flux_fluid", AeroFluxFluidFluidType::new);
}