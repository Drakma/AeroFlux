/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.drakma.aeroflux.AerofluxMod;

public class AerofluxModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, AerofluxMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FLUX_PARTICLE = REGISTRY.register("flux_particle", () -> new SimpleParticleType(false));
}