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
public record InfuserGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<InfuserGUIButtonMessage> TYPE = new Type<>(Identifier.fromNamespaceAndPath(AerofluxMod.MODID, "infuser_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, InfuserGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, InfuserGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new InfuserGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<InfuserGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final InfuserGUIButtonMessage message, final IPayloadContext context) {
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

			RotateBlockProcedure.execute(world, x, y, z);
		}
		if (buttonID == 1) {

			DarknessToggleProcedure.execute(world);
		}
		if (buttonID == 2) {

			SettingsRedstoneToggleProcedure.execute(world);
		}
		if (buttonID == 3) {

			SettingsRedstoneToggleProcedure.execute(world);
		}
		if (buttonID == 4) {

			SettingsFluidToggleProcedure.execute(world);
		}
		if (buttonID == 5) {

			SettingsItemToggleProcedure.execute(world);
		}
		if (buttonID == 6) {

			SettingsEnergyToggleProcedure.execute(world);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AerofluxMod.addNetworkMessage(InfuserGUIButtonMessage.TYPE, InfuserGUIButtonMessage.STREAM_CODEC, InfuserGUIButtonMessage::handleData);
	}
}