package net.drakma.aeroflux.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.Identifier;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import net.drakma.aeroflux.world.inventory.InfuserGUIMenu;
import net.drakma.aeroflux.procedures.*;
import net.drakma.aeroflux.network.InfuserGUIButtonMessage;
import net.drakma.aeroflux.init.AerofluxModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.platform.InputConstants;

public class InfuserGUIScreen extends AbstractContainerScreen<InfuserGUIMenu> implements AerofluxModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private ImageButton imagebutton_rotate;
	private ImageButton imagebutton_mode;
	private ImageButton imagebutton_tab_redstone_off;
	private ImageButton imagebutton_tab_redstone_on;
	private ImageButton imagebutton_tab_fluid_off;
	private ImageButton imagebutton_tab_item_off;
	private ImageButton imagebutton_tab_energy_off;
	private static final Identifier BACKGROUND = Identifier.parse("aeroflux:textures/screens/infuser_gui.png");
	private static final Identifier IMAGE_0 = Identifier.parse("aeroflux:textures/screens/gui_light.png");
	private static final Identifier IMAGE_1 = Identifier.parse("aeroflux:textures/screens/gui_medium.png");
	private static final Identifier IMAGE_2 = Identifier.parse("aeroflux:textures/screens/gui_dark.png");
	private static final Identifier IMAGE_3 = Identifier.parse("aeroflux:textures/screens/aeroflux_slot.png");
	private static final Identifier IMAGE_4 = Identifier.parse("aeroflux:textures/screens/aeroflux_slot.png");
	private static final Identifier IMAGE_5 = Identifier.parse("aeroflux:textures/screens/aeroflux_slot.png");
	private static final Identifier IMAGE_6 = Identifier.parse("aeroflux:textures/screens/aeroflux_slot.png");
	private static final Identifier IMAGE_7 = Identifier.parse("aeroflux:textures/screens/aeroflux_slot.png");
	private static final Identifier IMAGE_8 = Identifier.parse("aeroflux:textures/screens/v_progress_empty.png");
	private static final Identifier IMAGE_9 = Identifier.parse("aeroflux:textures/screens/v_progress_some.png");
	private static final Identifier IMAGE_10 = Identifier.parse("aeroflux:textures/screens/v_progress_10.png");
	private static final Identifier IMAGE_11 = Identifier.parse("aeroflux:textures/screens/v_progress_20.png");
	private static final Identifier IMAGE_12 = Identifier.parse("aeroflux:textures/screens/v_progress_30.png");
	private static final Identifier IMAGE_13 = Identifier.parse("aeroflux:textures/screens/v_progress_40.png");
	private static final Identifier IMAGE_14 = Identifier.parse("aeroflux:textures/screens/v_progress_50.png");
	private static final Identifier IMAGE_15 = Identifier.parse("aeroflux:textures/screens/v_progress_60.png");
	private static final Identifier IMAGE_16 = Identifier.parse("aeroflux:textures/screens/v_progress_70.png");
	private static final Identifier IMAGE_17 = Identifier.parse("aeroflux:textures/screens/v_progress_80.png");
	private static final Identifier IMAGE_18 = Identifier.parse("aeroflux:textures/screens/v_progress_90.png");
	private static final Identifier IMAGE_19 = Identifier.parse("aeroflux:textures/screens/v_progress_almost.png");
	private static final Identifier IMAGE_20 = Identifier.parse("aeroflux:textures/screens/v_progress_full.png");
	private static final Identifier IMAGE_21 = Identifier.parse("aeroflux:textures/screens/v_progress_overlay.png");
	private static final Identifier IMAGE_22 = Identifier.parse("aeroflux:textures/screens/v_timer_empty.png");
	private static final Identifier IMAGE_23 = Identifier.parse("aeroflux:textures/screens/v_timer_some.png");
	private static final Identifier IMAGE_24 = Identifier.parse("aeroflux:textures/screens/v_timer_10.png");
	private static final Identifier IMAGE_25 = Identifier.parse("aeroflux:textures/screens/v_timer_20.png");
	private static final Identifier IMAGE_26 = Identifier.parse("aeroflux:textures/screens/v_timer_30.png");
	private static final Identifier IMAGE_27 = Identifier.parse("aeroflux:textures/screens/v_timer_40.png");
	private static final Identifier IMAGE_28 = Identifier.parse("aeroflux:textures/screens/v_timer_50.png");
	private static final Identifier IMAGE_29 = Identifier.parse("aeroflux:textures/screens/v_timer_60.png");
	private static final Identifier IMAGE_30 = Identifier.parse("aeroflux:textures/screens/v_timer_70.png");
	private static final Identifier IMAGE_31 = Identifier.parse("aeroflux:textures/screens/v_timer_80.png");
	private static final Identifier IMAGE_32 = Identifier.parse("aeroflux:textures/screens/v_timer_90.png");
	private static final Identifier IMAGE_33 = Identifier.parse("aeroflux:textures/screens/v_timer_almost.png");
	private static final Identifier IMAGE_34 = Identifier.parse("aeroflux:textures/screens/v_timer_100.png");
	private static final Identifier IMAGE_35 = Identifier.parse("aeroflux:textures/screens/settings_medium.png");
	private static final Identifier IMAGE_36 = Identifier.parse("aeroflux:textures/screens/settings_light.png");
	private static final Identifier IMAGE_37 = Identifier.parse("aeroflux:textures/screens/settings_dark.png");

	public InfuserGUIScreen(InfuserGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text, 176, 166);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		if (mouseX > leftPos + -13 && mouseX < leftPos + -1 && mouseY > topPos + 155 && mouseY < topPos + 167) {
			String hoverText = RotateTooltipProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
		}
		if (mouseX > leftPos + -13 && mouseX < leftPos + -1 && mouseY > topPos + 142 && mouseY < topPos + 154) {
			guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.infuser_gui.tooltip_switch_mode"), mouseX, mouseY);
		}
		if (mouseX > leftPos + 12 && mouseX < leftPos + 20 && mouseY > topPos + 25 && mouseY < topPos + 79) {
			String hoverText = GetFluidLevelOnlyProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
		}
		if (mouseX > leftPos + 22 && mouseX < leftPos + 30 && mouseY > topPos + 25 && mouseY < topPos + 79) {
			String hoverText = GetProgressProcedure.execute(world, x, y, z);
			if (hoverText != null) {
				guiGraphics.setComponentTooltipForNextFrame(font, Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
			}
		}
		if (CapabilityRedstoneProcedure.execute(world, x, y, z))
			if (mouseX > leftPos + -15 && mouseX < leftPos + -1 && mouseY > topPos + 0 && mouseY < topPos + 14) {
				guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.infuser_gui.tooltip_redstone_settings"), mouseX, mouseY);
			}
		super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		if (IsDarknessLightProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0, this.leftPos + 0, this.topPos + 0, 0, 0, 177, 167, 177, 167);
		}
		if (IsDarknessMediumProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1, this.leftPos + 0, this.topPos + 0, 0, 0, 177, 167, 177, 167);
		}
		if (IsDarknessDarkProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2, this.leftPos + 0, this.topPos + 0, 0, 0, 177, 167, 177, 167);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3, this.leftPos + 62, this.topPos + 40, 0, 0, 17, 17, 17, 17);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4, this.leftPos + 116, this.topPos + 31, 0, 0, 17, 17, 17, 17);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5, this.leftPos + 116, this.topPos + 49, 0, 0, 17, 17, 17, 17);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6, this.leftPos + 134, this.topPos + 31, 0, 0, 17, 17, 17, 17);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7, this.leftPos + 134, this.topPos + 49, 0, 0, 17, 17, 17, 17);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		if (FilledSomeProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled10Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled20Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled30Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled40Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_13, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled50Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_14, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled60Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_15, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled70Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_16, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled80Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_17, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled90Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_18, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (FilledAlmostProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_19, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Filled100Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_20, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_21, this.leftPos + 12, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_22, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		if (ProgressSomeProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_23, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress10Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_24, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress20Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_25, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress30Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_26, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress40Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_27, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress50Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_28, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress60Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_29, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress70Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_30, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress80Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_31, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress90Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_32, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (ProgressAlmostProcedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_33, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (Progress100Procedure.execute(world, x, y, z)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_34, this.leftPos + 22, this.topPos + 25, 0, 0, 8, 54, 8, 54);
		}
		if (IsSettingsMediumProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_35, this.leftPos + -67, this.topPos + 0, 0, 0, 51, 80, 51, 80);
		}
		if (IsSettingsLightProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_36, this.leftPos + -67, this.topPos + 0, 0, 0, 51, 80, 51, 80);
		}
		if (IsSettingsDarkProcedure.execute(world)) {
			guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_37, this.leftPos + -67, this.topPos + 0, 0, 0, 51, 80, 51, 80);
		}
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		int key = InputConstants.getKey(event).getValue();
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(event);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
		guiGraphics.text(this.font, GetBlockNameProcedure.execute(world, x, y, z), 15, 8, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_rotate = new ImageButton(this.leftPos + -13, this.topPos + 155, 12, 12, new WidgetSprites(Identifier.parse("aeroflux:textures/screens/rotate.png"), Identifier.parse("aeroflux:textures/screens/rotate.png")), e -> {
			int x = InfuserGUIScreen.this.x;
			int y = InfuserGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(0, x, y, z));
				InfuserGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_rotate);
		imagebutton_mode = new ImageButton(this.leftPos + -13, this.topPos + 142, 12, 12, new WidgetSprites(Identifier.parse("aeroflux:textures/screens/mode.png"), Identifier.parse("aeroflux:textures/screens/mode.png")), e -> {
			int x = InfuserGUIScreen.this.x;
			int y = InfuserGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(1, x, y, z));
				InfuserGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_mode);
		imagebutton_tab_redstone_off = new ImageButton(this.leftPos + -15, this.topPos + 0, 14, 14,
				new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_redstone_off.png"), Identifier.parse("aeroflux:textures/screens/tab_redstone_off.png")), e -> {
					int x = InfuserGUIScreen.this.x;
					int y = InfuserGUIScreen.this.y;
					if (CapabilityRedstoneProcedure.execute(world, x, y, z)) {
						ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(2, x, y, z));
						InfuserGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_redstone_off);
		imagebutton_tab_redstone_on = new ImageButton(this.leftPos + -15, this.topPos + 0, 14, 14,
				new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_redstone_on.png"), Identifier.parse("aeroflux:textures/screens/tab_redstone_on.png")), e -> {
					int x = InfuserGUIScreen.this.x;
					int y = InfuserGUIScreen.this.y;
					if (SettingsRedstoneStatusProcedure.execute(world)) {
						ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(3, x, y, z));
						InfuserGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_redstone_on);
		imagebutton_tab_fluid_off = new ImageButton(this.leftPos + -15, this.topPos + 15, 14, 14, new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_fluid_off.png"), Identifier.parse("aeroflux:textures/screens/tab_fluid_off.png")),
				e -> {
					int x = InfuserGUIScreen.this.x;
					int y = InfuserGUIScreen.this.y;
					if (CapabilityFluidProcedure.execute(world, x, y, z)) {
						ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(4, x, y, z));
						InfuserGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
					}
				}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_fluid_off);
		imagebutton_tab_item_off = new ImageButton(this.leftPos + -15, this.topPos + 30, 14, 14, new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_item_off.png"), Identifier.parse("aeroflux:textures/screens/tab_item_off.png")), e -> {
			int x = InfuserGUIScreen.this.x;
			int y = InfuserGUIScreen.this.y;
			if (CapabilityItemsProcedure.execute(world, x, y, z)) {
				ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(5, x, y, z));
				InfuserGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_item_off);
		imagebutton_tab_energy_off = new ImageButton(this.leftPos + -15, this.topPos + 45, 14, 14, new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_energy_off.png"), Identifier.parse("aeroflux:textures/screens/tab_energy_off.png")),
				e -> {
					int x = InfuserGUIScreen.this.x;
					int y = InfuserGUIScreen.this.y;
					if (CapabilityEnergyProcedure.execute(world, x, y, z)) {
						ClientPacketDistributor.sendToServer(new InfuserGUIButtonMessage(6, x, y, z));
						InfuserGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
					}
				}) {
			@Override
			public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_energy_off);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.imagebutton_tab_redstone_off.visible = CapabilityRedstoneProcedure.execute(world, x, y, z);
		this.imagebutton_tab_redstone_on.visible = SettingsRedstoneStatusProcedure.execute(world);
		this.imagebutton_tab_fluid_off.visible = CapabilityFluidProcedure.execute(world, x, y, z);
		this.imagebutton_tab_item_off.visible = CapabilityItemsProcedure.execute(world, x, y, z);
		this.imagebutton_tab_energy_off.visible = CapabilityEnergyProcedure.execute(world, x, y, z);
	}
}