/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;

import net.drakma.aeroflux.fluid.AeroFluxFluidFluid;
import net.drakma.aeroflux.AerofluxMod;

public class AerofluxModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, AerofluxMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> AERO_FLUX_FLUID = REGISTRY.register("aero_flux_fluid", AeroFluxFluidFluid.Source::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_AERO_FLUX_FLUID = REGISTRY.register("flowing_aero_flux_fluid", AeroFluxFluidFluid.Flowing::new);
}