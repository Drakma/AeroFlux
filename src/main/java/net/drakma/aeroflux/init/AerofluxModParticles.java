/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.drakma.aeroflux.client.particle.FluxParticleParticle;

@EventBusSubscriber(Dist.CLIENT)
public class AerofluxModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(AerofluxModParticleTypes.FLUX_PARTICLE.get(), FluxParticleParticle::provider);
	}
}