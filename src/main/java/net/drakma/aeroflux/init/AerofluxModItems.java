/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.transfer.fluid.BucketResourceHandler;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.drakma.aeroflux.item.*;
import net.drakma.aeroflux.AerofluxMod;

import java.util.function.Function;

@EventBusSubscriber
public class AerofluxModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(AerofluxMod.MODID);
	public static final DeferredItem<Item> WOODEN_COLLECTOR;
	public static final DeferredItem<Item> AERO_FLUX_FLUID_BUCKET;
	public static final DeferredItem<Item> WOODEN_TANK;
	public static final DeferredItem<Item> FLUX_ENRICHED_COBBLESTONE;
	public static final DeferredItem<Item> WOODEN_TANK_ITEM;
	public static final DeferredItem<Item> STONE_COLLECTOR;
	public static final DeferredItem<Item> WOODEN_INFUSER;
	public static final DeferredItem<Item> PIPE;
	public static final DeferredItem<Item> SCREWDRIVER;
	static {
		WOODEN_COLLECTOR = block(AerofluxModBlocks.WOODEN_COLLECTOR);
		AERO_FLUX_FLUID_BUCKET = register("aero_flux_fluid_bucket", AeroFluxFluidItem::new);
		WOODEN_TANK = block(AerofluxModBlocks.WOODEN_TANK);
		FLUX_ENRICHED_COBBLESTONE = block(AerofluxModBlocks.FLUX_ENRICHED_COBBLESTONE);
		WOODEN_TANK_ITEM = block(AerofluxModBlocks.WOODEN_TANK_ITEM);
		STONE_COLLECTOR = block(AerofluxModBlocks.STONE_COLLECTOR);
		WOODEN_INFUSER = block(AerofluxModBlocks.WOODEN_INFUSER);
		PIPE = block(AerofluxModBlocks.PIPE);
		SCREWDRIVER = register("screwdriver", ScrewdriverItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name,
			Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.Fluid.ITEM, (stack, access) -> new BucketResourceHandler(access),
				AERO_FLUX_FLUID_BUCKET.get());
	}
}
