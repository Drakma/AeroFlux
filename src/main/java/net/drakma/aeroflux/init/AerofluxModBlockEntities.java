/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.drakma.aeroflux.init;

import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.drakma.aeroflux.block.entity.*;
import net.drakma.aeroflux.AerofluxMod;

@EventBusSubscriber
public class AerofluxModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AerofluxMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodenCollectorBlockEntity>> WOODEN_COLLECTOR = register("wooden_collector", AerofluxModBlocks.WOODEN_COLLECTOR, WoodenCollectorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodenTankBlockEntity>> WOODEN_TANK = register("wooden_tank", AerofluxModBlocks.WOODEN_TANK, WoodenTankBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodenTankItemBlockEntity>> WOODEN_TANK_ITEM = register("wooden_tank_item", AerofluxModBlocks.WOODEN_TANK_ITEM, WoodenTankItemBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoneCollectorBlockEntity>> STONE_COLLECTOR = register("stone_collector", AerofluxModBlocks.STONE_COLLECTOR, StoneCollectorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WoodenInfuserBlockEntity>> WOODEN_INFUSER = register("wooden_infuser", AerofluxModBlocks.WOODEN_INFUSER, WoodenInfuserBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PipeBlockEntity>> PIPE = register("pipe", AerofluxModBlocks.PIPE, PipeBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.Item.BLOCK, WOODEN_COLLECTOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, WOODEN_COLLECTOR.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.Item.BLOCK, WOODEN_TANK.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, WOODEN_TANK.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.Item.BLOCK, WOODEN_TANK_ITEM.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, WOODEN_TANK_ITEM.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.Item.BLOCK, STONE_COLLECTOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, STONE_COLLECTOR.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.Item.BLOCK, WOODEN_INFUSER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, WOODEN_INFUSER.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.Item.BLOCK, PIPE.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, PIPE.get(), (blockEntity, side) -> blockEntity.getFluidTank());
	}
}