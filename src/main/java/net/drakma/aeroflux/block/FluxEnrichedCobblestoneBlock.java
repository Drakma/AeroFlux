package net.drakma.aeroflux.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class FluxEnrichedCobblestoneBlock extends Block {
	public FluxEnrichedCobblestoneBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(1f, 10f).lightLevel(blockstate -> 1));
	}
}