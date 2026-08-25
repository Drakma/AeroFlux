
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
public record CollectorGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<CollectorGUIButtonMessage> TYPE = new Type<>(
			Identifier.fromNamespaceAndPath(AerofluxMod.MODID, "collector_gui_buttons"));

	public static final StreamCodec<RegistryFriendlyByteBuf, CollectorGUIButtonMessage> STREAM_CODEC = StreamCodec.of(
			(RegistryFriendlyByteBuf buffer, CollectorGUIButtonMessage message) -> {
				buffer.writeInt(message.buttonID);
				buffer.writeInt(message.x);
				buffer.writeInt(message.y);
				buffer.writeInt(message.z);
			},
			(RegistryFriendlyByteBuf buffer) -> new CollectorGUIButtonMessage(buffer.readInt(), buffer.readInt(),
					buffer.readInt(), buffer.readInt()));

	@Override
	public Type<CollectorGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final CollectorGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z))
					.exceptionally(e -> {
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

			AlwaysOnToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 7) {

			AlwaysOnToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 8) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 9) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 10) {

			RedstoneSignalToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 11) {

			AutoExportToggleProcedure.execute(world, x, y, z);
		}
		if (buttonID == 12) {

			SettingsRedstoneToggleProcedure.execute(world);
		}
		if (buttonID == 13) {

			SettingsRedstoneToggleProcedure.execute(world);
		}
		if (buttonID == 14) {

			SettingsFluidToggleProcedure.execute(world);
		}
		if (buttonID == 15) {

			SettingsFluidToggleProcedure.execute(world);
		}
		if (buttonID == 18) {

			SettingsEnergyToggleProcedure.execute(world);
		}
		if (buttonID == 19) {

			SettingsEnergyToggleProcedure.execute(world);
		}
		if (buttonID == 20) {

			DarknessToggleProcedure.execute(world);
		}
		if (buttonID == 21) {

			RotateBlockProcedure.execute(world, x, y, z);
		}
		if (buttonID == 22) {

			SettingsAllOffProcedure.execute(world);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AerofluxMod.addNetworkMessage(CollectorGUIButtonMessage.TYPE, CollectorGUIButtonMessage.STREAM_CODEC,
				CollectorGUIButtonMessage::handleData);
	}

}
