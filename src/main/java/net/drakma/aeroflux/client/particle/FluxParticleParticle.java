package net.drakma.aeroflux.client.particle;

import net.minecraft.util.RandomSource;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

public class FluxParticleParticle extends SingleQuadParticle {
	public static FluxParticleParticleProvider provider(SpriteSet spriteSet) {
		return new FluxParticleParticleProvider(spriteSet);
	}

	public static class FluxParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public FluxParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
			return new FluxParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;
	private float angularVelocity;
	private float angularAcceleration;

	protected FluxParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z, spriteSet.first());
		this.spriteSet = spriteSet;
		this.setSize(0.3f, 0.3f);
		this.lifetime = (int) Math.max(1, 30 + (this.random.nextInt(10) - 5));
		this.gravity = 0f;
		this.hasPhysics = false;
		this.xd = vx * 1.5;
		this.yd = vy * 1.5;
		this.zd = vz * 1.5;
		this.angularVelocity = 0.15f;
		this.angularAcceleration = 0f;
	}

	@Override
	public SingleQuadParticle.Layer getLayer() {
		return SingleQuadParticle.Layer.TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		this.oRoll = this.roll;
		this.roll += this.angularVelocity;
		this.angularVelocity += this.angularAcceleration;
	}
}