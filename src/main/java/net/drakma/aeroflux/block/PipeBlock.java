package net.drakma.aeroflux.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.ARGB;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.procedures.PipeNeighbourBlockChangesProcedure;
import net.drakma.aeroflux.block.entity.PipeBlockEntity;

import javax.annotation.Nullable;

import java.util.function.Function;

public class PipeBlock extends Block implements EntityBlock {
	public static final EnumProperty<WestProperty> WEST = EnumProperty.create("west", WestProperty.class);
	public static final EnumProperty<UpProperty> UP = EnumProperty.create("up", UpProperty.class);
	public static final EnumProperty<DownProperty> DOWN = EnumProperty.create("down", DownProperty.class);
	public static final EnumProperty<NorthProperty> NORTH = EnumProperty.create("north", NorthProperty.class);
	public static final EnumProperty<EastProperty> EAST = EnumProperty.create("east", EastProperty.class);
	public static final EnumProperty<SouthProperty> SOUTH = EnumProperty.create("south", SouthProperty.class);
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public PipeBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GLASS).strength(0.7f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(
				this.stateDefinition.any().setValue(WEST, WestProperty.OFF).setValue(UP, UpProperty.OFF).setValue(DOWN, DownProperty.OFF).setValue(NORTH, NorthProperty.ON).setValue(EAST, EastProperty.ON).setValue(SOUTH, SouthProperty.ON));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			VoxelShape shape = Shapes.empty();
			shape = Shapes.join(shape, box(6.5, 6.5, 6.5, 9.5, 9.5, 9.5), BooleanOp.OR);
			if (state.getValue(NORTH) == NorthProperty.ON) {
				shape = Shapes.join(shape, box(7, 7, 0, 9, 9, 6.5), BooleanOp.OR);
			}
			if (state.getValue(NORTH) == NorthProperty.CONNECTED) {
				shape = Shapes.join(shape, Shapes.or(box(7, 7, 0, 9, 9, 7), box(5, 5, 0, 11, 7, 0.5), box(5, 9, 0, 11, 11, 0.5), box(5, 7, 0, 7, 9, 0.5), box(9, 7, 0, 11, 9, 0.5), box(9, 7, 0.5, 10, 9, 1), box(6, 7, 0.5, 7, 9, 1),
						box(6, 9, 0.5, 10, 10, 1), box(6, 6, 0.5, 10, 7, 1)), BooleanOp.OR);
			}
			if (state.getValue(EAST) == EastProperty.ON) {
				shape = Shapes.join(shape, box(9.5, 7, 7, 16, 9, 9), BooleanOp.OR);
			}
			if (state.getValue(SOUTH) == SouthProperty.ON) {
				shape = Shapes.join(shape, box(7, 7, 9.5, 9, 9, 16), BooleanOp.OR);
			}
			if (state.getValue(SOUTH) == SouthProperty.CONNECTED) {
				shape = Shapes.join(shape, Shapes.or(box(7, 7, 9, 9, 9, 16), box(5, 5, 15.5, 11, 7, 16), box(5, 9, 15.5, 11, 11, 16), box(9, 7, 15.5, 11, 9, 16), box(5, 7, 15.5, 7, 9, 16), box(6, 7, 15, 7, 9, 15.5), box(9, 7, 15, 10, 9, 15.5),
						box(6, 9, 15, 10, 10, 15.5), box(6, 6, 15, 10, 7, 15.5)), BooleanOp.OR);
			}
			if (state.getValue(WEST) == WestProperty.ON) {
				shape = Shapes.join(shape, box(0, 7, 7, 6.5, 9, 9), BooleanOp.OR);
			}
			if (state.getValue(WEST) == WestProperty.CONNECTED) {
				shape = Shapes.join(shape, Shapes.or(box(0, 7, 7, 7, 9, 9), box(0, 5, 5, 0.5, 7, 11), box(0, 9, 5, 0.5, 11, 11), box(0, 7, 9, 0.5, 9, 11), box(0, 7, 5, 0.5, 9, 7), box(0.5, 7, 6, 1, 9, 7), box(0.5, 7, 9, 1, 9, 10),
						box(0.5, 9, 6, 1, 10, 10), box(0.5, 6, 6, 1, 7, 10)), BooleanOp.OR);
			}
			if (state.getValue(UP) == UpProperty.ON) {
				shape = Shapes.join(shape, box(7, 9.5, 7, 9, 16, 9), BooleanOp.OR);
			}
			if (state.getValue(UP) == UpProperty.CONNECTED) {
				shape = Shapes.join(shape, Shapes.or(box(7, 9, 7, 9, 16, 9), box(5, 15.5, 5, 11, 16, 7), box(5, 15.5, 9, 11, 16, 11), box(5, 15.5, 7, 7, 16, 9), box(9, 15.5, 7, 11, 16, 9), box(9, 15, 7, 10, 15.5, 9), box(6, 15, 7, 7, 15.5, 9),
						box(6, 15, 9, 10, 15.5, 10), box(6, 15, 6, 10, 15.5, 7)), BooleanOp.OR);
			}
			if (state.getValue(DOWN) == DownProperty.ON) {
				shape = Shapes.join(shape, box(7, 0, 7, 9, 6.5, 9), BooleanOp.OR);
			}
			if (state.getValue(DOWN) == DownProperty.CONNECTED) {
				shape = Shapes.join(shape, Shapes.or(box(7, 0, 7, 9, 7, 9), box(5, 0, 9, 11, 0.5, 11), box(5, 0, 5, 11, 0.5, 7), box(5, 0, 7, 7, 0.5, 9), box(9, 0, 7, 11, 0.5, 9), box(9, 0.5, 7, 10, 1, 9), box(6, 0.5, 7, 7, 1, 9),
						box(6, 0.5, 6, 10, 1, 7), box(6, 0.5, 9, 10, 1, 10)), BooleanOp.OR);
			}
			return shape;
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		Vec3 offset = state.getOffset(pos);
		return shapes.apply(state).move(offset.x, offset.y, offset.z);
	}

	@Override
	public Integer getBeaconColorMultiplier(BlockState state, LevelReader world, BlockPos pos, BlockPos beaconPos) {
		return ARGB.opaque(-1);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(WEST, UP, DOWN, NORTH, EAST, SOUTH);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		return state.setValue(WEST, WestProperty.OFF).setValue(UP, UpProperty.OFF).setValue(DOWN, DownProperty.OFF).setValue(NORTH, NorthProperty.ON).setValue(EAST, EastProperty.ON).setValue(SOUTH, SouthProperty.ON);
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		PipeNeighbourBlockChangesProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new PipeBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	public enum WestProperty implements StringRepresentable {
		OFF("off"), ON("on"), CONNECTED("connected");

		private final String name;

		private WestProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum UpProperty implements StringRepresentable {
		OFF("off"), ON("on"), CONNECTED("connected");

		private final String name;

		private UpProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum DownProperty implements StringRepresentable {
		OFF("off"), ON("on"), CONNECTED("connected");

		private final String name;

		private DownProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum NorthProperty implements StringRepresentable {
		ON("on"), OFF("off"), CONNECTED("connected");

		private final String name;

		private NorthProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum EastProperty implements StringRepresentable {
		ON("on"), OFF("off"), CONNECTED("connected");

		private final String name;

		private EastProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	public enum SouthProperty implements StringRepresentable {
		ON("on"), OFF("off"), CONNECTED("connected");

		private final String name;

		private SouthProperty(String name) {
			this.name = name;
		}

		@Override
		public String getSerializedName() {
			return this.name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}
}