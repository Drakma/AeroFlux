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

import net.drakma.aeroflux.world.inventory.CollectorGUIMenu;
import net.drakma.aeroflux.procedures.*;
import net.drakma.aeroflux.network.CollectorGUIButtonMessage;
import net.drakma.aeroflux.init.AerofluxModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.platform.InputConstants;

public class CollectorGUIScreen extends AbstractContainerScreen<CollectorGUIMenu>
    implements AerofluxModScreens.ScreenAccessor {
  private final Level world;
  private final int x, y, z;
  private final Player entity;
  private boolean menuStateUpdateActive = false;
  private ImageButton imagebutton_side_button_no;
  private ImageButton imagebutton_side_button_no1;
  private ImageButton imagebutton_side_button_no2;
  private ImageButton imagebutton_side_button_no3;
  private ImageButton imagebutton_side_button_no4;
  private ImageButton imagebutton_side_button_no5;
  private ImageButton imagebutton_redstone_alwayson;
  private ImageButton imagebutton_redstone_required;
  private ImageButton imagebutton_redstone_signal_off;
  private ImageButton imagebutton_redstone_signal_on;
  private ImageButton imagebutton_redstone_signal_level;
  private ImageButton imagebutton_auto_export;
  private ImageButton imagebutton_tab_redstone;
  private ImageButton imagebutton_tab_redstone_selected;
  private ImageButton imagebutton_tab_fluid;
  private ImageButton imagebutton_tab_fluid_selected;
  private ImageButton imagebutton_tab_items;
  private ImageButton imagebutton_tab_items_selected;
  private ImageButton imagebutton_tab_energy;
  private ImageButton imagebutton_tab_energy_selected;
  private ImageButton imagebutton_mode;
  private ImageButton imagebutton_rotate;
  private ImageButton imagebutton_settings_close;
  private static final Identifier BACKGROUND = Identifier.parse("aeroflux:textures/screens/collector_gui.png");
  private static final Identifier IMAGE_0 = Identifier.parse("aeroflux:textures/screens/gui_light.png");
  private static final Identifier IMAGE_1 = Identifier.parse("aeroflux:textures/screens/gui_dark.png");
  private static final Identifier IMAGE_2 = Identifier.parse("aeroflux:textures/screens/gui_medium.png");
  private static final Identifier IMAGE_3 = Identifier.parse("aeroflux:textures/screens/settings_light.png");
  private static final Identifier IMAGE_4 = Identifier.parse("aeroflux:textures/screens/settings_dark.png");
  private static final Identifier IMAGE_5 = Identifier.parse("aeroflux:textures/screens/settings_medium.png");
  private static final Identifier IMAGE_6 = Identifier.parse("aeroflux:textures/screens/sky_progression.png");
  private static final Identifier IMAGE_7 = Identifier.parse("aeroflux:textures/screens/weather_raining.png");
  private static final Identifier IMAGE_8 = Identifier.parse("aeroflux:textures/screens/weather_storming.png");
  private static final Identifier IMAGE_9 = Identifier.parse("aeroflux:textures/screens/sky_time_outline.png");
  private static final Identifier IMAGE_10 = Identifier.parse("aeroflux:textures/screens/sky_time_outline.png");
  private static final Identifier IMAGE_11 = Identifier.parse("aeroflux:textures/screens/sky_time_outline.png");
  private static final Identifier IMAGE_12 = Identifier.parse("aeroflux:textures/screens/sky_time_outline.png");
  private static final Identifier IMAGE_13 = Identifier.parse("aeroflux:textures/screens/sky_cantbeseen.png");
  private static final Identifier IMAGE_14 = Identifier.parse("aeroflux:textures/screens/h_progress_empty.png");
  private static final Identifier IMAGE_15 = Identifier.parse("aeroflux:textures/screens/h_progress_some.png");
  private static final Identifier IMAGE_16 = Identifier.parse("aeroflux:textures/screens/h_progress_10.png");
  private static final Identifier IMAGE_17 = Identifier.parse("aeroflux:textures/screens/h_progress_20.png");
  private static final Identifier IMAGE_18 = Identifier.parse("aeroflux:textures/screens/h_progress_30.png");
  private static final Identifier IMAGE_19 = Identifier.parse("aeroflux:textures/screens/h_progress_40.png");
  private static final Identifier IMAGE_20 = Identifier.parse("aeroflux:textures/screens/h_progress_50.png");
  private static final Identifier IMAGE_21 = Identifier.parse("aeroflux:textures/screens/h_progress_60.png");
  private static final Identifier IMAGE_22 = Identifier.parse("aeroflux:textures/screens/h_progress_70.png");
  private static final Identifier IMAGE_23 = Identifier.parse("aeroflux:textures/screens/h_progress_80.png");
  private static final Identifier IMAGE_24 = Identifier.parse("aeroflux:textures/screens/h_progress_90.png");
  private static final Identifier IMAGE_25 = Identifier.parse("aeroflux:textures/screens/h_progress_full.png");
  private static final Identifier IMAGE_26 = Identifier.parse("aeroflux:textures/screens/h_progress_overlay.png");

  public CollectorGUIScreen(CollectorGUIMenu container, Inventory inventory, Component text) {
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
    if (mouseX > leftPos + 13 && mouseX < leftPos + 163 && mouseY > topPos + 29 && mouseY < topPos + 54) {
      String hoverText = GetTimeProcedure.execute(world);
      if (hoverText != null) {
        guiGraphics.setComponentTooltipForNextFrame(font,
            Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
      }
    }
    if (mouseX > leftPos + 8 && mouseX < leftPos + 169 && mouseY > topPos + 65 && mouseY < topPos + 75) {
      String hoverText = GetFluidLevelProcedure.execute(world, x, y, z);
      if (hoverText != null) {
        guiGraphics.setComponentTooltipForNextFrame(font,
            Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
      }
    }
    if (AlwaysOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -61 && mouseX < leftPos + -49 && mouseY > topPos + 13 && mouseY < topPos + 25) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_always_on"),
            mouseX, mouseY);
      }
    if (RedstoneRequiredProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -61 && mouseX < leftPos + -49 && mouseY > topPos + 13 && mouseY < topPos + 25) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_redstone_required"), mouseX, mouseY);
      }
    if (RedstoneSignalOffProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -48 && mouseX < leftPos + -23 && mouseY > topPos + 13 && mouseY < topPos + 25) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_redstone_signal_off"), mouseX, mouseY);
      }
    if (RedstoneSignalOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -48 && mouseX < leftPos + -23 && mouseY > topPos + 13 && mouseY < topPos + 25) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_redstone_signal_on"), mouseX, mouseY);
      }
    if (RedstoneSignalLevelProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -48 && mouseX < leftPos + -23 && mouseY > topPos + 13 && mouseY < topPos + 25) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_redstone_signal_levels"), mouseX, mouseY);
      }
    if (CapabilityRedstoneProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -15 && mouseX < leftPos + -1 && mouseY > topPos + 0 && mouseY < topPos + 14) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_redstone_settings"), mouseX, mouseY);
      }
    if (CapabilityFluidProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -15 && mouseX < leftPos + -1 && mouseY > topPos + 15 && mouseY < topPos + 29) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_fluid_settings"), mouseX, mouseY);
      }
    if (CapabilityItemsProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -15 && mouseX < leftPos + -1 && mouseY > topPos + 30 && mouseY < topPos + 44) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_item_settings"), mouseX, mouseY);
      }
    if (CapabilityEnergyProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -15 && mouseX < leftPos + -1 && mouseY > topPos + 46 && mouseY < topPos + 60) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.collector_gui.tooltip_energy_settings"), mouseX, mouseY);
      }
    if (mouseX > leftPos + -14 && mouseX < leftPos + -2 && mouseY > topPos + 141 && mouseY < topPos + 153) {
      guiGraphics.setTooltipForNextFrame(font,
          Component.translatable("gui.aeroflux.collector_gui.tooltip_change_light_mode"), mouseX, mouseY);
    }
    if (mouseX > leftPos + -14 && mouseX < leftPos + -2 && mouseY > topPos + 155 && mouseY < topPos + 167) {
      String hoverText = RotateTooltipProcedure.execute(world, x, y, z);
      if (hoverText != null) {
        guiGraphics.setComponentTooltipForNextFrame(font,
            Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
      }
    }
    if (mouseX > leftPos + -66 && mouseX < leftPos + -57 && mouseY > topPos + 1 && mouseY < topPos + 10) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_close"),
          mouseX, mouseY);
    }
    if (mouseX > leftPos + -48 && mouseX < leftPos + -36 && mouseY > topPos + 36 && mouseY < topPos + 48) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_up"), mouseX,
          mouseY);
    }
    if (mouseX > leftPos + -61 && mouseX < leftPos + -49 && mouseY > topPos + 49 && mouseY < topPos + 61) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_left"),
          mouseX, mouseY);
    }
    if (mouseX > leftPos + -48 && mouseX < leftPos + -36 && mouseY > topPos + 49 && mouseY < topPos + 61) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_front"),
          mouseX, mouseY);
    }
    if (mouseX > leftPos + -35 && mouseX < leftPos + -23 && mouseY > topPos + 49 && mouseY < topPos + 61) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_right"),
          mouseX, mouseY);
    }
    if (mouseX > leftPos + -48 && mouseX < leftPos + -36 && mouseY > topPos + 62 && mouseY < topPos + 74) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_down"),
          mouseX, mouseY);
    }
    if (mouseX > leftPos + -35 && mouseX < leftPos + -23 && mouseY > topPos + 62 && mouseY < topPos + 74) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.collector_gui.tooltip_back"),
          mouseX, mouseY);
    }
    super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
  }

  @Override
  public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
    super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth,
        this.imageHeight, this.imageWidth, this.imageHeight);
    if (IsDarknessLightProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0,
          this.leftPos + 0, this.topPos + 0, 0, 0,
          177, 167,
          177, 167);
    }
    if (IsDarknessDarkProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1,
          this.leftPos + 0, this.topPos + 0, 0, 0,
          177, 167,
          177, 167);
    }
    if (IsDarknessMediumProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2,
          this.leftPos + 0, this.topPos + 0, 0, 0,
          177, 167,
          177, 167);
    }
    if (IsSettingsLightProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3,
          this.leftPos + -67, this.topPos + 0, 0, 0,
          51, 80,
          51, 80);
    }
    if (IsSettingsDarkProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4,
          this.leftPos + -67, this.topPos + 0, 0, 0,
          51, 80,
          51, 80);
    }
    if (IsSettingsMediumProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5,
          this.leftPos + -67, this.topPos + 0, 0, 0,
          51, 80,
          51, 80);
    }
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6,
        this.leftPos + 13, this.topPos + 29, 0, 0,
        150, 25,
        150, 25);
    if (IsRainingProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7,
          this.leftPos + 13, this.topPos + 29, 0, 0,
          150, 25,
          150, 25);
    }
    if (IsStormingProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8,
          this.leftPos + 13, this.topPos + 29, 0, 0,
          150, 25,
          150, 25);
    }
    if (IsSunriseProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9,
          this.leftPos + 11, this.topPos + 27, 0, 0,
          41, 29,
          41, 29);
    }
    if (IsDaytimeProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10,
          this.leftPos + 50, this.topPos + 27, 0, 0,
          41, 29,
          41, 29);
    }
    if (IsSunsetProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11,
          this.leftPos + 89, this.topPos + 27, 0, 0,
          41, 29,
          41, 29);
    }
    if (IsNightProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12,
          this.leftPos + 124, this.topPos + 27, 0, 0,
          41, 29,
          41, 29);
    }
    if (CantSeeSkyProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_13,
          this.leftPos + 13, this.topPos + 29, 0, 0,
          150, 25,
          150, 25);
    }
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_14,
        this.leftPos + 8, this.topPos + 65, 0, 0,
        161, 10,
        161, 10);
    if (FilledSomeProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_15,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled10Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_16,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled20Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_17,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled30Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_18,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled40Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_19,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled50Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_20,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled60Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_21,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled70Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_22,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled80Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_23,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled90Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_24,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    if (Filled100Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_25,
          this.leftPos + 8, this.topPos + 65, 0, 0,
          161, 10,
          161, 10);
    }
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_26,
        this.leftPos + 8, this.topPos + 65, 0, 0,
        161, 10,
        161, 10);
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
    guiGraphics.text(this.font,
        GetBlockNameProcedure.execute(world, x, y, z),
        12, 7, -12829636, false);
    if (CantSeeSkyProcedure.execute(world, x, y, z))
      guiGraphics.text(this.font,
          Component.translatable("gui.aeroflux.collector_gui.label_sky_ubstructed"),
          55, 38, -3355444, false);
  }

  @Override
  public void init() {
    super.init();
    imagebutton_side_button_no = new ImageButton(
        this.leftPos + -48, this.topPos + 36,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(0, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no);
    imagebutton_side_button_no1 = new ImageButton(
        this.leftPos + -61, this.topPos + 49,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(1, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no1);
    imagebutton_side_button_no2 = new ImageButton(
        this.leftPos + -48, this.topPos + 49,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(2, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no2);
    imagebutton_side_button_no3 = new ImageButton(
        this.leftPos + -35, this.topPos + 49,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(3, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no3);
    imagebutton_side_button_no4 = new ImageButton(
        this.leftPos + -48, this.topPos + 62,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(4, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no4);
    imagebutton_side_button_no5 = new ImageButton(
        this.leftPos + -35, this.topPos + 62,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/off.png"),
            Identifier.parse("aeroflux:textures/screens/off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(5, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no5);
    imagebutton_redstone_alwayson = new ImageButton(
        this.leftPos + -61, this.topPos + 13,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_alwayson.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_alwayson.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (AlwaysOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(6, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_alwayson);
    imagebutton_redstone_required = new ImageButton(
        this.leftPos + -61, this.topPos + 13,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_required.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_required.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (RedstoneRequiredProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(7, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_required);
    imagebutton_redstone_signal_off = new ImageButton(
        this.leftPos + -48, this.topPos + 13,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_off.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (RedstoneSignalOffProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(8, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 8, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_signal_off);
    imagebutton_redstone_signal_on = new ImageButton(
        this.leftPos + -48, this.topPos + 13,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_on.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_on.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (RedstoneSignalOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(9, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 9, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_signal_on);
    imagebutton_redstone_signal_level = new ImageButton(
        this.leftPos + -48, this.topPos + 13,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_level.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_level.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (RedstoneSignalLevelProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(10, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 10, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_signal_level);
    imagebutton_auto_export = new ImageButton(
        this.leftPos + -97, this.topPos + 59,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/auto_export.png"),
            Identifier.parse("aeroflux:textures/screens/auto_export.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (AutoExportOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(11, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 11, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_auto_export);
    imagebutton_tab_redstone = new ImageButton(
        this.leftPos + -15, this.topPos + 0,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_redstone_off.png"),
            Identifier.parse("aeroflux:textures/screens/tab_redstone_off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (CapabilityRedstoneProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(12, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 12, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_redstone);
    imagebutton_tab_redstone_selected = new ImageButton(
        this.leftPos + -15, this.topPos + 0,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_redstone_on.png"),
            Identifier.parse("aeroflux:textures/screens/tab_redstone_on.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsRedstoneStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(13, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 13, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_redstone_selected);
    imagebutton_tab_fluid = new ImageButton(
        this.leftPos + -15, this.topPos + 15,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_fluid_off.png"),
            Identifier.parse("aeroflux:textures/screens/tab_fluid_off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (CapabilityFluidProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(14, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 14, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_fluid);
    imagebutton_tab_fluid_selected = new ImageButton(
        this.leftPos + -15, this.topPos + 15,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_fluid_on.png"),
            Identifier.parse("aeroflux:textures/screens/tab_fluid_on.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsFluidStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(15, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 15, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_fluid_selected);
    imagebutton_tab_items = new ImageButton(
        this.leftPos + -15, this.topPos + 30,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_item_off.png"),
            Identifier.parse("aeroflux:textures/screens/tab_item_off.png")),
        e -> {
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_items);
    imagebutton_tab_items_selected = new ImageButton(
        this.leftPos + -15, this.topPos + 30,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_item_on.png"),
            Identifier.parse("aeroflux:textures/screens/tab_item_on.png")),
        e -> {
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_items_selected);
    imagebutton_tab_energy = new ImageButton(
        this.leftPos + -15, this.topPos + 46,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_energy_off.png"),
            Identifier.parse("aeroflux:textures/screens/tab_energy_off.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (CapabilityEnergyProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(18, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 18, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_energy);
    imagebutton_tab_energy_selected = new ImageButton(
        this.leftPos + -15, this.topPos + 46,
        14, 14,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/tab_energy_on.png"),
            Identifier.parse("aeroflux:textures/screens/tab_energy_on.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsEnergyStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(19, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 19, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_tab_energy_selected);
    imagebutton_mode = new ImageButton(
        this.leftPos + -14, this.topPos + 141,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/mode.png"),
            Identifier.parse("aeroflux:textures/screens/mode.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (true) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(20, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 20, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_mode);
    imagebutton_rotate = new ImageButton(
        this.leftPos + -14, this.topPos + 155,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/rotate.png"),
            Identifier.parse("aeroflux:textures/screens/rotate.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (true) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(21, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 21, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_rotate);
    imagebutton_settings_close = new ImageButton(
        this.leftPos + -66, this.topPos + 1,
        9, 9,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/settings_close.png"),
            Identifier.parse("aeroflux:textures/screens/settings_close.png")),
        e -> {
          int x = CollectorGUIScreen.this.x;
          int y = CollectorGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new CollectorGUIButtonMessage(22, x, y, z));
            CollectorGUIButtonMessage.handleButtonAction(entity, 22, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_settings_close);
  }

  @Override
  protected void containerTick() {
    super.containerTick();
    this.imagebutton_side_button_no.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_side_button_no1.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_side_button_no2.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_side_button_no3.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_side_button_no4.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_side_button_no5.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_redstone_alwayson.visible = AlwaysOnProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_required.visible = RedstoneRequiredProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_off.visible = RedstoneSignalOffProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_on.visible = RedstoneSignalOnProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_level.visible = RedstoneSignalLevelProcedure.execute(world, x, y, z);
    this.imagebutton_auto_export.visible = AutoExportOnProcedure.execute(world, x, y, z);
    this.imagebutton_tab_redstone.visible = CapabilityRedstoneProcedure.execute(world, x, y, z);
    this.imagebutton_tab_redstone_selected.visible = SettingsRedstoneStatusProcedure.execute(world);
    this.imagebutton_tab_fluid.visible = CapabilityFluidProcedure.execute(world, x, y, z);
    this.imagebutton_tab_fluid_selected.visible = SettingsFluidStatusProcedure.execute(world);
    this.imagebutton_tab_items.visible = CapabilityItemsProcedure.execute(world, x, y, z);
    this.imagebutton_tab_items_selected.visible = CapabilityItemsProcedure.execute(world, x, y, z);
    this.imagebutton_tab_energy.visible = CapabilityEnergyProcedure.execute(world, x, y, z);
    this.imagebutton_tab_energy_selected.visible = SettingsEnergyStatusProcedure.execute(world);
    this.imagebutton_settings_close.visible = SettingsStatusProcedure.execute(world);
  }
}
