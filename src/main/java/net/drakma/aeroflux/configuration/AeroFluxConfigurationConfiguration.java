package net.drakma.aeroflux.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class AeroFluxConfigurationConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<Boolean> EMIT_PARTICLES;
	public static final ModConfigSpec.ConfigValue<Boolean> RAIN_HALVES_GENERATION;
	public static final ModConfigSpec.ConfigValue<Double> PIPE_MB_TICK;
	public static final ModConfigSpec.ConfigValue<Double> WOODEN_MB_FILLED;
	public static final ModConfigSpec.ConfigValue<Double> WOODEN_MAX_TICK;
	public static final ModConfigSpec.ConfigValue<Double> WOODEN_FLUX_PER_TICK;
	public static final ModConfigSpec.ConfigValue<Double> WOODEN_INFUSION_MAX_TICK;
	public static final ModConfigSpec.ConfigValue<Double> STONE_MB_FILLED;
	public static final ModConfigSpec.ConfigValue<Double> STONE_MAX_TICK;
	static {
		EMIT_PARTICLES = BUILDER.comment("Will particles be emitted").define("emit_particles", true);
		RAIN_HALVES_GENERATION = BUILDER.comment("Will rain halve the generation of Aero Flux?").define("rain_halves_generation", true);
		PIPE_MB_TICK = BUILDER.comment("mB/tick for pipe").define("pipe_mb_tick", (double) 125);
		BUILDER.push("wooden");
		BUILDER.push("collector");
		WOODEN_MB_FILLED = BUILDER.comment("mb of Fluid filled per generation").define("wooden_mb_filled", (double) 2);
		WOODEN_MAX_TICK = BUILDER.comment("Ticks between Fluid Generation").define("wooden_max_tick", (double) 3);
		BUILDER.pop();
		BUILDER.push("infuser");
		WOODEN_FLUX_PER_TICK = BUILDER.comment("mb of Fluid used per generation").define("wooden_flux_per_tick", (double) 2);
		WOODEN_INFUSION_MAX_TICK = BUILDER.comment("Ticks to infused a block").define("wooden_infusion_max_tick", (double) 400);
		BUILDER.pop();
		BUILDER.pop();
		BUILDER.push("stone");
		STONE_MB_FILLED = BUILDER.comment("mb of Fluid filled per generation").define("stone_mb_filled", (double) 5);
		STONE_MAX_TICK = BUILDER.comment("Ticks between Fluid Generation").define("stone_max_tick", (double) 3);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}