package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.drakma.aeroflux.init.AerofluxModBlocks;

public class ScrewdriverRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Direction direction, Entity entity) {
		if (direction == null || entity == null)
			return;
		double lhitX = 0;
		double lhitY = 0;
		double lhitZ = 0;
		double bX = 0;
		double bY = 0;
		double bZ = 0;
		double dX = 0;
		double dY = 0;
		double dZ = 0;
		String ClickLocation = "";
		if (Minecraft.getInstance().hasControlDown()) {
			if (entity instanceof ServerPlayer _player)
				_player.sendSystemMessage(Component.literal(("" + direction)), true);
		}
		lhitX = (float) entity.pick(20, 1.0F, false).getLocation().x;;
		lhitY = (float) entity.pick(20, 1.0F, false).getLocation().y;;
		lhitZ = (float) entity.pick(20, 1.0F, false).getLocation().z;;
		bX = x + 0.5;
		bY = y + 0.5;
		bZ = z + 0.5;
		dX = lhitX - bX;
		dY = lhitY - bY;
		dZ = lhitZ - bZ;
		if (dX > 0.11 && IsBetweenProcedure.execute(0.11, -0.11, dY) && IsBetweenProcedure.execute(0.11, -0.11, dZ)) {
			ClickLocation = "east";
		}
		if (dX < -0.11 && IsBetweenProcedure.execute(0.11, -0.11, dY) && IsBetweenProcedure.execute(0.11, -0.11, dZ)) {
			ClickLocation = "west";
		}
		if (dY > 0.11 && IsBetweenProcedure.execute(0.11, -0.11, dX) && IsBetweenProcedure.execute(0.11, -0.11, dZ)) {
			ClickLocation = "up";
		}
		if (dY < -0.11 && IsBetweenProcedure.execute(0.11, -0.11, dX) && IsBetweenProcedure.execute(0.11, -0.11, dZ)) {
			ClickLocation = "down";
		}
		if (dZ > 0.11 && IsBetweenProcedure.execute(0.11, -0.11, dX) && IsBetweenProcedure.execute(0.11, -0.11, dY)) {
			ClickLocation = "south";
		}
		if (dZ < -0.11 && IsBetweenProcedure.execute(0.11, -0.11, dX) && IsBetweenProcedure.execute(0.11, -0.11, dY)) {
			ClickLocation = "north";
		}
		if (IsBetweenProcedure.execute(0.11, -0.11, dZ) && IsBetweenProcedure.execute(0.11, -0.11, dX) && IsBetweenProcedure.execute(0.11, -0.11, dY)) {
			ClickLocation = "center";
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == AerofluxModBlocks.PIPE.get()) {
			if (Minecraft.getInstance().hasShiftDown()) {
				if ((ClickLocation).equals("center")) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(AerofluxModBlocks.PIPE.get()));
						entityToSpawn.setPickUpDelay(10);
						_level.addFreshEntity(entityToSpawn);
					}
				} else {
					{
						String _value = "off";
						BlockPos _pos = BlockPos.containing(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty(ClickLocation) instanceof EnumProperty _enumProp && _enumProp.getValue(_value).isPresent())
							world.setBlock(_pos, _bs.setValue(_enumProp, (Enum) _enumProp.getValue(_value).get()), 3);
					}
				}
			}
		}
	}
}