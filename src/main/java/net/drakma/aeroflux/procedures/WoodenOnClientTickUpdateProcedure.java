package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;

import net.drakma.aeroflux.init.AerofluxModParticleTypes;

public class WoodenOnClientTickUpdateProcedure {
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
		if (Math.cos(SunAngle) > 0) {
			world.addParticle((SimpleParticleType) (AerofluxModParticleTypes.FLUX_PARTICLE.get()), ((x + 0.5) - Math.sin(SunAngle) * Distance), (y + 1 + Math.cos(SunAngle) * Distance), (z + 0.5), (Math.sin(SunAngle) * ParticleSpeed),
					(0 - Math.cos(SunAngle) * ParticleSpeed), 0);
		}
	}
}