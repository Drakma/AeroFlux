package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;

public class GetHardnessProcedure {
	public static double execute(ItemStack Block) {
		BlockState BlockItem = Blocks.AIR.defaultBlockState();
		double DestroyTime = 0;
		BlockItem = (Block.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState());
		DestroyTime = BlockItem.getBlock().defaultDestroyTime();;
		return DestroyTime;
	}
}