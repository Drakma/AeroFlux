/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.drakma.aeroflux.AerofluxMod;

public class AerofluxModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AerofluxMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERO_FLUX = REGISTRY.register("aero_flux",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.aeroflux.aero_flux")).icon(() -> new ItemStack(AerofluxModBlocks.WOODEN_COLLECTOR.get())).displayItems((parameters, tabData) -> {
				tabData.accept(AerofluxModBlocks.WOODEN_COLLECTOR.get().asItem());
				tabData.accept(AerofluxModItems.AERO_FLUX_FLUID_BUCKET.get());
				tabData.accept(AerofluxModBlocks.FLUX_ENRICHED_COBBLESTONE.get().asItem());
				tabData.accept(AerofluxModItems.SCREWDRIVER.get());
				tabData.accept(AerofluxModBlocks.WOODEN_TANK_ITEM.get().asItem());
				tabData.accept(AerofluxModBlocks.STONE_COLLECTOR.get().asItem());
				tabData.accept(AerofluxModBlocks.PIPE.get().asItem());
			}).withSearchBar().build());
}