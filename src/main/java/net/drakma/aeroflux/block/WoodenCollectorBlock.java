package net.drakma.aeroflux.block;

import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

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
import net.minecraft.util.RandomSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.color.block.BlockTintSources;

import net.drakma.aeroflux.world.inventory.CollectorGUIMenu;
import net.drakma.aeroflux.procedures.*;
import net.drakma.aeroflux.init.AerofluxModBlocks;
import net.drakma.aeroflux.block.entity.WoodenCollectorBlockEntity;

import javax.annotation.Nullable;

import java.util.function.Function;
import java.util.List;

import io.netty.buffer.Unpooled;

public class WoodenCollectorBlock extends Block implements EntityBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	private final Function<BlockState, VoxelShape> shapes = this.makeShapes();

	public WoodenCollectorBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	private Function<BlockState, VoxelShape> makeShapes() {
		return this.getShapeForEachState(state -> {
			return switch (state.getValue(FACING)) {
				case NORTH -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(2, 1, 2, 14, 2, 14), box(14, 12, 2, 15, 13, 14), box(1, 2, 1, 15, 11, 15), box(1, 11, 1, 15, 12, 3), box(1, 11, 13, 15, 12, 15), box(1, 11, 3, 3, 12, 13),
						box(13, 11, 3, 15, 12, 13), box(1, 12, 1, 15, 13, 2), box(1, 12, 14, 15, 13, 15), box(1, 12, 2, 2, 13, 14), box(7, 11, 7, 9, 12, 9), box(7.5, 12, 7.5, 8.5, 15, 8.5), box(6, 15, 6, 10, 15.5, 10), box(4, 5, 0.75, 12, 10, 1));
				case EAST -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(2, 1, 2, 14, 2, 14), box(2, 12, 14, 14, 13, 15), box(1, 2, 1, 15, 11, 15), box(13, 11, 1, 15, 12, 15), box(1, 11, 1, 3, 12, 15), box(3, 11, 1, 13, 12, 3), box(3, 11, 13, 13, 12, 15),
						box(14, 12, 1, 15, 13, 15), box(1, 12, 1, 2, 13, 15), box(2, 12, 1, 14, 13, 2), box(7, 11, 7, 9, 12, 9), box(7.5, 12, 7.5, 8.5, 15, 8.5), box(6, 15, 6, 10, 15.5, 10), box(15, 5, 4, 15.25, 10, 12));
				case WEST -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(2, 1, 2, 14, 2, 14), box(2, 12, 1, 14, 13, 2), box(1, 2, 1, 15, 11, 15), box(1, 11, 1, 3, 12, 15), box(13, 11, 1, 15, 12, 15), box(3, 11, 13, 13, 12, 15), box(3, 11, 1, 13, 12, 3),
						box(1, 12, 1, 2, 13, 15), box(14, 12, 1, 15, 13, 15), box(2, 12, 14, 14, 13, 15), box(7, 11, 7, 9, 12, 9), box(7.5, 12, 7.5, 8.5, 15, 8.5), box(6, 15, 6, 10, 15.5, 10), box(0.75, 5, 4, 1, 10, 12));
				default -> Shapes.or(box(3, 0, 3, 13, 1, 13), box(2, 1, 2, 14, 2, 14), box(1, 12, 2, 2, 13, 14), box(1, 2, 1, 15, 11, 15), box(1, 11, 13, 15, 12, 15), box(1, 11, 1, 15, 12, 3), box(13, 11, 3, 15, 12, 13), box(1, 11, 3, 3, 12, 13),
						box(1, 12, 14, 15, 13, 15), box(1, 12, 1, 15, 13, 2), box(14, 12, 2, 15, 13, 14), box(7, 11, 7, 9, 12, 9), box(7.5, 12, 7.5, 8.5, 15, 8.5), box(6, 15, 6, 10, 15.5, 10), box(4, 5, 15, 12, 10, 15.25));
			};
		});
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return shapes.apply(state);
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
		WoodenCollectorOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		world.scheduleTick(pos, this, 1);
	}

	@Override
	public void animateTick(BlockState blockstate, Level world, BlockPos pos, RandomSource random) {
		super.animateTick(blockstate, world, pos, random);
		CollectorOnClientTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		if (entity instanceof ServerPlayer player) {
			player.openMenu(new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Wooden Collector");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new CollectorGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(pos));
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
		return new WoodenCollectorBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity != null && blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos, Direction direction) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof WoodenCollectorBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	public static void blockColorLoad(RegisterColorHandlersEvent.BlockTintSources event) {
		event.getBlockColors().register(List.of(BlockTintSources.grass()), AerofluxModBlocks.WOODEN_COLLECTOR.get());
	}
}