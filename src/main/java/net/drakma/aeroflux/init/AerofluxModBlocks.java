/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.drakma.aeroflux.block.*;
import net.drakma.aeroflux.AerofluxMod;

import java.util.function.Function;

public class AerofluxModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(AerofluxMod.MODID);
	public static final DeferredBlock<Block> WOODEN_COLLECTOR;
	public static final DeferredBlock<Block> AERO_FLUX_FLUID;
	public static final DeferredBlock<Block> WOODEN_TANK;
	public static final DeferredBlock<Block> FLUX_ENRICHED_COBBLESTONE;
	public static final DeferredBlock<Block> WOODEN_TANK_ITEM;
	public static final DeferredBlock<Block> STONE_COLLECTOR;
	public static final DeferredBlock<Block> WOODEN_INFUSER;
	public static final DeferredBlock<Block> PIPE;
	static {
		WOODEN_COLLECTOR = register("wooden_collector", WoodenCollectorBlock::new);
		AERO_FLUX_FLUID = register("aero_flux_fluid", AeroFluxFluidBlock::new);
		WOODEN_TANK = register("wooden_tank", WoodenTankBlock::new);
		FLUX_ENRICHED_COBBLESTONE = register("flux_enriched_cobblestone", FluxEnrichedCobblestoneBlock::new);
		WOODEN_TANK_ITEM = register("wooden_tank_item", WoodenTankItemBlock::new);
		STONE_COLLECTOR = register("stone_collector", StoneCollectorBlock::new);
		WOODEN_INFUSER = register("wooden_infuser", WoodenInfuserBlock::new);
		PIPE = register("pipe", PipeBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void blockColorLoad(RegisterColorHandlersEvent.BlockTintSources event) {
			WoodenCollectorBlock.blockColorLoad(event);
		}
	}
}