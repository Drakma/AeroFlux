package net.drakma.aeroflux.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Direction;

public class GetBlockInDirectionProcedure {
	public static Vec3 execute(double x, double y, double z, Direction OutputDirection) {
		if (OutputDirection == null)
			return Vec3.ZERO;
		double NewX = 0;
		double NewY = 0;
		double NewZ = 0;
		Vec3 ResultBlockLocation = Vec3.ZERO;
		if (OutputDirection == Direction.DOWN) {
			NewX = x;
			NewY = y - 1;
			NewZ = z;
		} else if (OutputDirection == Direction.UP) {
			NewX = x;
			NewY = y + 1;
			NewZ = z;
		} else if (OutputDirection == Direction.EAST) {
			NewX = x + 1;
			NewY = y;
			NewZ = z;
		} else if (OutputDirection == Direction.WEST) {
			NewX = x - 1;
			NewY = y;
			NewZ = z;
		} else if (OutputDirection == Direction.NORTH) {
			NewX = x;
			NewY = y;
			NewZ = z - 1;
		} else if (OutputDirection == Direction.SOUTH) {
			NewX = x;
			NewY = y;
			NewZ = z + 1;
		}
		ResultBlockLocation = new Vec3(NewX, NewY, NewZ);
		return ResultBlockLocation;
	}
}