package net.drakma.aeroflux.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class ConvertSideToDirectionProcedure {
	public static Direction execute(LevelAccessor world, double x, double y, double z, String OutputSide) {
		if (OutputSide == null)
			return Direction.NORTH;
		Direction FacingDirection = Direction.NORTH;
		Direction OutputDirection = Direction.NORTH;
		FacingDirection = getBlockDirection(world, BlockPos.containing(x, y, z));
		if (((OutputSide).toUpperCase()).equals("FRONT")) {
			OutputDirection = FacingDirection;
		} else if (((OutputSide).toUpperCase()).equals("BACK")) {
			OutputDirection = FacingDirection.getOpposite();
		} else if (((OutputSide).toUpperCase()).equals("UP")) {
			OutputDirection = Direction.UP;
		} else if (((OutputSide).toUpperCase()).equals("DOWN")) {
			OutputDirection = Direction.DOWN;
		} else if (((OutputSide).toUpperCase()).equals("RIGHT")) {
			OutputDirection = (FacingDirection.getClockWise(Direction.Axis.Y)).getOpposite();
		} else if (((OutputSide).toUpperCase()).equals("LEFT")) {
			OutputDirection = FacingDirection.getClockWise(Direction.Axis.Y);
		}
		return OutputDirection;
	}

	private static Direction getBlockDirection(LevelAccessor world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (property != null && blockState.getValue(property) instanceof Direction direction)
			return direction;
		else if (blockState.hasProperty(BlockStateProperties.AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
		else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}