package net.drakma.aeroflux.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.LiquidBlock;

import net.drakma.aeroflux.init.AerofluxModFluids;

public class AeroFluxFluidBlock extends LiquidBlock {
	public AeroFluxFluidBlock(BlockBehaviour.Properties properties) {
		super(AerofluxModFluids.AERO_FLUX_FLUID.get(),
				properties.mapColor(MapColor.SAND).strength(100f).postProcess((bs, br, bp) -> bp).emissiveRendering((bs, br, bp) -> true).noCollision().noLootTable().liquid().pushReaction(PushReaction.DESTROY).sound(SoundType.EMPTY).replaceable());
	}
}