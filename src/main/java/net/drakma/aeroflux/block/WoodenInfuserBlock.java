package net.drakma.aeroflux.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.Containers;
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.drakma.aeroflux.world.inventory.InfuserGUIMenu;
import net.drakma.aeroflux.procedures.WoodenInfuserOnTickUpdateProcedure;
import net.drakma.aeroflux.procedures.MachineRedstoneOnProcedure;
import net.drakma.aeroflux.procedures.MachineRedstoneOffProcedure;
import net.drakma.aeroflux.procedures.MachineBlockAddedProcedure;
import net.drakma.aeroflux.procedures.GetEmittedRedstoneLevelProcedure;
import net.drakma.aeroflux.block.entity.WoodenInfuserBlockEntity;

import javax.annotation.Nullable;

import java.util.function.Function;

import io.netty.buffer.Unpooled;

public class WoodenInfuserBlock extends Block implements EntityBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public WoodenInfuserBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				case NORTH -> Shapes.or(box(2, 0, 2, 4, 1, 4), box(2, 0, 12, 4, 1, 14), box(12, 0, 12, 14, 1, 14), box(12, 0, 2, 14, 1, 4), box(1, 1, 1, 15, 8, 15), box(1, 10, 1, 15, 15, 15), box(2, 15, 2, 14, 16, 14), box(13, 8, 13, 15, 10, 15),
						box(13, 8, 1, 15, 10, 3), box(1, 8, 1, 3, 10, 3), box(1, 8, 13, 3, 10, 15), box(4, 2, 0.75, 12, 7, 1));
				case EAST -> Shapes.or(box(12, 0, 2, 14, 1, 4), box(2, 0, 2, 4, 1, 4), box(2, 0, 12, 4, 1, 14), box(12, 0, 12, 14, 1, 14), box(1, 1, 1, 15, 8, 15), box(1, 10, 1, 15, 15, 15), box(2, 15, 2, 14, 16, 14), box(1, 8, 13, 3, 10, 15),
						box(13, 8, 13, 15, 10, 15), box(13, 8, 1, 15, 10, 3), box(1, 8, 1, 3, 10, 3), box(15, 2, 4, 15.25, 7, 12));
				case WEST -> Shapes.or(box(2, 0, 12, 4, 1, 14), box(12, 0, 12, 14, 1, 14), box(12, 0, 2, 14, 1, 4), box(2, 0, 2, 4, 1, 4), box(1, 1, 1, 15, 8, 15), box(1, 10, 1, 15, 15, 15), box(2, 15, 2, 14, 16, 14), box(13, 8, 1, 15, 10, 3),
						box(1, 8, 1, 3, 10, 3), box(1, 8, 13, 3, 10, 15), box(13, 8, 13, 15, 10, 15), box(0.75, 2, 4, 1, 7, 12));
				default -> Shapes.or(box(12, 0, 12, 14, 1, 14), box(12, 0, 2, 14, 1, 4), box(2, 0, 2, 4, 1, 4), box(2, 0, 12, 4, 1, 14), box(1, 1, 1, 15, 8, 15), box(1, 10, 1, 15, 15, 15), box(2, 15, 2, 14, 16, 14), box(1, 8, 1, 3, 10, 3),
						box(1, 8, 13, 3, 10, 15), box(13, 8, 13, 15, 10, 15), box(13, 8, 1, 15, 10, 3), box(4, 2, 15, 12, 7, 15.25));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

	@Override
	public int getLightDampening(BlockState state) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		return state.setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		Level world = (Level) blockAccess;
		return (int) GetEmittedRedstoneLevelProcedure.execute(world, x, y, z);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 1);
		MachineBlockAddedProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			MachineRedstoneOnProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		} else {
			MachineRedstoneOffProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
		super.tick(blockstate, world, pos, random);
		WoodenInfuserOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Wooden Infuser");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new InfuserGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
				}
			}, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new WoodenInfuserBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	protected void affectNeighborsAfterRemoval(BlockState blockstate, ServerLevel world, BlockPos blockpos, boolean flag) {
		Containers.updateNeighboursAfterDestroy(blockstate, world, blockpos);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos, Direction direction) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof WoodenInfuserBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}
}