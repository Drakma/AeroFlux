package net.drakma.aeroflux.client.fluid;

import org.joml.Vector4f;

import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.Identifier;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.Camera;

import net.drakma.aeroflux.init.AerofluxModFluids;
import net.drakma.aeroflux.init.AerofluxModFluidTypes;

import javax.annotation.Nullable;

@EventBusSubscriber(Dist.CLIENT)
public class AeroFluxFluidFluidExtension {
	@SubscribeEvent
	public static void registerRegisterFluidModels(RegisterFluidModelsEvent event) {
		event.register(new FluidModel.Unbaked(new Material(Identifier.parse("aeroflux:block/aero_flux_fluid_still")), new Material(Identifier.parse("aeroflux:block/aero_flux_fluid_flow")), null, null), AerofluxModFluids.AERO_FLUX_FLUID,
				AerofluxModFluids.FLOWING_AERO_FLUX_FLUID);
	}

	@SubscribeEvent
	public static void registerFluidTypeExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			@Override
			public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
				fluidFogColor.set(1f, 1f, 0.4f, fluidFogColor.w);
			}

			@Override
			public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData) {
				float nearDistance = fogData.environmentalStart;
				float farDistance = fogData.environmentalEnd;
				Entity entity = camera.entity();
				Level world = entity.level();
				fogData.environmentalStart = 0f;
				fogData.environmentalEnd = Math.min(48f, renderDistance);
			}
		}, AerofluxModFluidTypes.AERO_FLUX_FLUID_TYPE);
	}
}