package net.drakma.aeroflux.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.drakma.aeroflux.init.AerofluxModFluids;

public class AeroFluxFluidItem extends BucketItem {
	public AeroFluxFluidItem(Item.Properties properties) {
		super(AerofluxModFluids.AERO_FLUX_FLUID.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}