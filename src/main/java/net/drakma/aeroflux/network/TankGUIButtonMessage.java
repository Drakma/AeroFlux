package net.drakma.aeroflux.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.Identifier;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.drakma.aeroflux.procedures.*;
import net.drakma.aeroflux.AerofluxMod;

@EventBusSubscriber
public record TankGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<TankGUIButtonMessage> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AerofluxMod.MODID, "tank_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, TankGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, TankGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new TankGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<TankGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final TankGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			SetOutputUpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			SetOutputLeftProcedure.execute(world, x, y, z);
		}
		if (buttonID == 2) {

			SetOutputFrontProcedure.execute(world, x, y, z);
		}
		if (buttonID == 3) {

			SetOutputRightProcedure.execute(world, x, y, z);
		}
		if (buttonID == 4) {

			SetOutputDownProcedure.execute(world, x, y, z);
		}
		if (buttonID == 5) {

			SetOutputBackProcedure.execute(world, x, y, z);
		}
		if (buttonID == 6) {

			SettingsToggleProcedure.execute(world);
		}
		if (buttonID == 7) {

			SettingsToggleProcedure.execute(world);
		}
		if (buttonID == 8) {

			RotateBlockProcedure.execute(world, x, y, z);
		}
		if (buttonID == 9) {

			AlwaysOnToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 10) {

			AlwaysOnToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 11) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 12) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 13) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 14) {

			AutoExportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 15) {

			AutoExportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 16) {

			ImportExportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 17) {

			ImportExportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 18) {

			AutoImportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 19) {

			AutoImportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 20) {

			SetOutputUpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 21) {

			SetOutputLeftProcedure.execute(world, x, y, z);
		}
		if (buttonID == 22) {

			SetOutputFrontProcedure.execute(world, x, y, z);
		}
		if (buttonID == 23) {

			SetOutputRightProcedure.execute(world, x, y, z);
		}
		if (buttonID == 24) {

			SetOutputDownProcedure.execute(world, x, y, z);
		}
		if (buttonID == 25) {

			SetOutputBackProcedure.execute(world, x, y, z);
		}
		if (buttonID == 26) {

			SetInputUpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 27) {

			SetInputLeftProcedure.execute(world, x, y, z);
		}
		if (buttonID == 28) {

			SetInputFrontProcedure.execute(world, x, y, z);
		}
		if (buttonID == 29) {

			SetInputRightProcedure.execute(world, x, y, z);
		}
		if (buttonID == 30) {

			SetInputDownProcedure.execute(world, x, y, z);
		}
		if (buttonID == 31) {

			SetInputBackProcedure.execute(world, x, y, z);
		}
		if (buttonID == 32) {

			SetInputUpProcedure.execute(world, x, y, z);
		}
		if (buttonID == 33) {

			SetInputLeftProcedure.execute(world, x, y, z);
		}
		if (buttonID == 34) {

			SetInputFrontProcedure.execute(world, x, y, z);
		}
		if (buttonID == 35) {

			SetInputRightProcedure.execute(world, x, y, z);
		}
		if (buttonID == 36) {

			SetInputDownProcedure.execute(world, x, y, z);
		}
		if (buttonID == 37) {

			SetInputBackProcedure.execute(world, x, y, z);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AerofluxMod.addNetworkMessage(TankGUIButtonMessage.TYPE, TankGUIButtonMessage.STREAM_CODEC, TankGUIButtonMessage::handleData);
	}
}