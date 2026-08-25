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

import net.drakma.aeroflux.world.inventory.TankGUIMenu;
import net.drakma.aeroflux.procedures.*;
import net.drakma.aeroflux.network.TankGUIButtonMessage;
import net.drakma.aeroflux.init.AerofluxModScreens;

import java.util.stream.Collectors;
import java.util.Arrays;

import com.mojang.blaze3d.platform.InputConstants;

public class TankGUIScreen extends AbstractContainerScreen<TankGUIMenu> implements AerofluxModScreens.ScreenAccessor {
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
  private ImageButton imagebutton_settings_closed;
  private ImageButton imagebutton_settings_open;
  private ImageButton imagebutton_rotate;
  private ImageButton imagebutton_redstone_alwayson;
  private ImageButton imagebutton_redstone_required;
  private ImageButton imagebutton_redstone_signal_off;
  private ImageButton imagebutton_redstone_signal_on;
  private ImageButton imagebutton_redstone_signal_level;
  private ImageButton imagebutton_auto_export_off;
  private ImageButton imagebutton_auto_export;
  private ImageButton imagebutton_settings_export;
  private ImageButton imagebutton_settings_import;
  private ImageButton imagebutton_auto_import;
  private ImageButton imagebutton_auto_import_off;
  private ImageButton imagebutton_side_export;
  private ImageButton imagebutton_side_export1;
  private ImageButton imagebutton_side_export2;
  private ImageButton imagebutton_side_export3;
  private ImageButton imagebutton_side_export4;
  private ImageButton imagebutton_side_export5;
  private ImageButton imagebutton_side_button_no6;
  private ImageButton imagebutton_side_button_no7;
  private ImageButton imagebutton_side_button_no8;
  private ImageButton imagebutton_side_button_no9;
  private ImageButton imagebutton_side_button_no10;
  private ImageButton imagebutton_side_button_no11;
  private ImageButton imagebutton_side_export6;
  private ImageButton imagebutton_side_import;
  private ImageButton imagebutton_side_import1;
  private ImageButton imagebutton_side_import2;
  private ImageButton imagebutton_side_import3;
  private ImageButton imagebutton_side_import4;
  private ImageButton imagebutton_arrow_right;
  private ImageButton imagebutton_bucket;
  private static final Identifier BACKGROUND = Identifier.parse("aeroflux:textures/screens/tank_gui.png");
  private static final Identifier IMAGE_0 = Identifier.parse("aeroflux:textures/screens/aeroflux_30_gui.png");
  private static final Identifier IMAGE_1 = Identifier.parse("aeroflux:textures/screens/settings_panel.png");
  private static final Identifier IMAGE_2 = Identifier.parse("aeroflux:textures/screens/fluid_level.png");
  private static final Identifier IMAGE_3 = Identifier.parse("aeroflux:textures/screens/fluid_filled_some.png");
  private static final Identifier IMAGE_4 = Identifier.parse("aeroflux:textures/screens/fluid_filled_10.png");
  private static final Identifier IMAGE_5 = Identifier.parse("aeroflux:textures/screens/fluid_filled_20.png");
  private static final Identifier IMAGE_6 = Identifier.parse("aeroflux:textures/screens/fluid_filled_30.png");
  private static final Identifier IMAGE_7 = Identifier.parse("aeroflux:textures/screens/fluid_filled_40.png");
  private static final Identifier IMAGE_8 = Identifier.parse("aeroflux:textures/screens/fluid_filled_50.png");
  private static final Identifier IMAGE_9 = Identifier.parse("aeroflux:textures/screens/fluid_filled_60.png");
  private static final Identifier IMAGE_10 = Identifier.parse("aeroflux:textures/screens/fluid_filled_70.png");
  private static final Identifier IMAGE_11 = Identifier.parse("aeroflux:textures/screens/fluid_filled_80.png");
  private static final Identifier IMAGE_12 = Identifier.parse("aeroflux:textures/screens/fluid_filled_90.png");
  private static final Identifier IMAGE_13 = Identifier.parse("aeroflux:textures/screens/fluid_filled_100.png");
  private static final Identifier IMAGE_14 = Identifier.parse("aeroflux:textures/screens/slot.png");
  private static final Identifier IMAGE_15 = Identifier.parse("aeroflux:textures/screens/slot.png");

  public TankGUIScreen(TankGUIMenu container, Inventory inventory, Component text) {
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
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -17 && mouseY > topPos + 30 && mouseY < topPos + 42) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_up"), mouseX,
            mouseY);
      }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -17 && mouseY > topPos + 43 && mouseY < topPos + 55) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_front"), mouseX,
            mouseY);
      }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -17 && mouseY > topPos + 56 && mouseY < topPos + 68) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_down"), mouseX,
            mouseY);
      }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -16 && mouseX < leftPos + -4 && mouseY > topPos + 56 && mouseY < topPos + 68) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_back"), mouseX,
            mouseY);
      }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -16 && mouseX < leftPos + -4 && mouseY > topPos + 43 && mouseY < topPos + 55) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_right"), mouseX,
            mouseY);
      }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 43 && mouseY < topPos + 55) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_left"), mouseX,
            mouseY);
      }
    if (mouseX > leftPos + 4 && mouseX < leftPos + 12 && mouseY > topPos + 5 && mouseY < topPos + 13) {
      guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_settings"), mouseX,
          mouseY);
    }
    if (mouseX > leftPos + 14 && mouseX < leftPos + 162 && mouseY > topPos + 35 && mouseY < topPos + 49) {
      String hoverText = GetFluidLevelOnlyProcedure.execute(world, x, y, z);
      if (hoverText != null) {
        guiGraphics.setComponentTooltipForNextFrame(font,
            Arrays.stream(hoverText.split("\n")).map(Component::literal).collect(Collectors.toList()), mouseX, mouseY);
      }
    }
    if (SettingsStatusProcedure.execute(world))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 56 && mouseY < topPos + 68) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_rotate"), mouseX,
            mouseY);
      }
    if (AlwaysOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 4 && mouseY < topPos + 16) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_always_on"),
            mouseX, mouseY);
      }
    if (RedstoneRequiredProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 4 && mouseY < topPos + 16) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_redstone_required"), mouseX, mouseY);
      }
    if (RedstoneSignalOffProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -4 && mouseY > topPos + 4 && mouseY < topPos + 16) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_redstone_signal_off"), mouseX, mouseY);
      }
    if (RedstoneSignalOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -4 && mouseY > topPos + 4 && mouseY < topPos + 16) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_redstone_signal_on"), mouseX, mouseY);
      }
    if (RedstoneSignalLevelProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -29 && mouseX < leftPos + -4 && mouseY > topPos + 4 && mouseY < topPos + 16) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_redstone_signal_levels"), mouseX, mouseY);
      }
    if (AutoExportOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 30 && mouseY < topPos + 42) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_auto_export"),
            mouseX, mouseY);
      }
    if (AutoExportOffProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -30 && mouseY > topPos + 30 && mouseY < topPos + 42) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_auto_export_off"), mouseX, mouseY);
      }
    if (ImportSettingsOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -4 && mouseY > topPos + 17 && mouseY < topPos + 29) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_import_settings"), mouseX, mouseY);
      }
    if (ExportSettingsOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -42 && mouseX < leftPos + -4 && mouseY > topPos + 17 && mouseY < topPos + 29) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_export_settings"), mouseX, mouseY);
      }
    if (AutoImportOnProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -16 && mouseX < leftPos + -4 && mouseY > topPos + 31 && mouseY < topPos + 42) {
        guiGraphics.setTooltipForNextFrame(font, Component.translatable("gui.aeroflux.tank_gui.tooltip_auto_import_on"),
            mouseX, mouseY);
      }
    if (AutoImportOffProcedure.execute(world, x, y, z))
      if (mouseX > leftPos + -16 && mouseX < leftPos + -4 && mouseY > topPos + 30 && mouseY < topPos + 42) {
        guiGraphics.setTooltipForNextFrame(font,
            Component.translatable("gui.aeroflux.tank_gui.tooltip_auto_import_off"), mouseX, mouseY);
      }
    if (mouseX > leftPos + 61 && mouseX < leftPos + 79 && mouseY > topPos + 58 && mouseY < topPos + 76) {
      guiGraphics.setTooltipForNextFrame(font,
          Component.translatable("gui.aeroflux.tank_gui.tooltip_fill_or_empty_buckets"), mouseX, mouseY);
    }
    super.extractRenderState(guiGraphics, mouseX, mouseY, partialTicks);
  }

  @Override
  public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
    super.extractBackground(guiGraphics, mouseX, mouseY, partialTicks);
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth,
        this.imageHeight, this.imageWidth, this.imageHeight);
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_0,
        this.leftPos + 0, this.topPos + 0, 0, 0,
        -1, -1,
        -1, -1);
    if (SettingsStatusProcedure.execute(world)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_1,
          this.leftPos + -47, this.topPos + 0, 0, 0,
          51, 61,
          51, 61);
    }
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_2,
        this.leftPos + 13, this.topPos + 34, 0, 0,
        -1, -1,
        -1, -1);
    if (FilledSomeProcedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_3,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled10Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_4,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled20Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_5,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled30Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_6,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled40Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_7,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled50Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_8,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled60Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_9,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled70Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_10,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled80Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_11,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled90Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_12,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    if (Filled100Procedure.execute(world, x, y, z)) {
      guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_13,
          this.leftPos + 14, this.topPos + 35, 0, 0,
          -1, -1,
          -1, -1);
    }
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_14,
        this.leftPos + 61, this.topPos + 58, 0, 0,
        -1, -1,
        -1, -1);
    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, IMAGE_15,
        this.leftPos + 97, this.topPos + 58, 0, 0,
        -1, -1,
        -1, -1);
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
        16, 5, -12829636, false);
  }

  @Override
  public void init() {
    super.init();
    imagebutton_side_button_no = new ImageButton(
        this.leftPos + -29, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(0, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
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
        this.leftPos + -42, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(1, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
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
        this.leftPos + -29, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(2, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
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
        this.leftPos + -16, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(3, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
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
        this.leftPos + -29, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(4, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 4, x, y, z);
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
        this.leftPos + -16, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusExportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(5, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 5, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no5);
    imagebutton_settings_closed = new ImageButton(
        this.leftPos + 2, this.topPos + 3,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/settings_closed.png"),
            Identifier.parse("aeroflux:textures/screens/settings_closed.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (true) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(6, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 6, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_settings_closed);
    imagebutton_settings_open = new ImageButton(
        this.leftPos + 2, this.topPos + 3,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/settings_open.png"),
            Identifier.parse("aeroflux:textures/screens/settings_open.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(7, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 7, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_settings_open);
    imagebutton_rotate = new ImageButton(
        this.leftPos + -42, this.topPos + 56,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/rotate.png"),
            Identifier.parse("aeroflux:textures/screens/rotate.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusProcedure.execute(world)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(8, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 8, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_rotate);
    imagebutton_redstone_alwayson = new ImageButton(
        this.leftPos + -42, this.topPos + 4,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_alwayson.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_alwayson.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (AlwaysOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(9, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 9, x, y, z);
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
        this.leftPos + -42, this.topPos + 4,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_required.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_required.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (RedstoneRequiredProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(10, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 10, x, y, z);
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
        this.leftPos + -29, this.topPos + 4,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_off.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_off.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (RedstoneSignalOffProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(11, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 11, x, y, z);
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
        this.leftPos + -29, this.topPos + 4,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_on.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_on.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (RedstoneSignalOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(12, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 12, x, y, z);
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
        this.leftPos + -29, this.topPos + 4,
        25, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/redstone_signal_level.png"),
            Identifier.parse("aeroflux:textures/screens/redstone_signal_level.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (RedstoneSignalLevelProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(13, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 13, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_redstone_signal_level);
    imagebutton_auto_export_off = new ImageButton(
        this.leftPos + -42, this.topPos + 30,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/auto_export_off.png"),
            Identifier.parse("aeroflux:textures/screens/auto_export_off.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (AutoExportOffProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(14, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 14, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_auto_export_off);
    imagebutton_auto_export = new ImageButton(
        this.leftPos + -42, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/auto_export.png"),
            Identifier.parse("aeroflux:textures/screens/auto_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (AutoExportOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(15, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 15, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_auto_export);
    imagebutton_settings_export = new ImageButton(
        this.leftPos + -42, this.topPos + 17,
        38, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/settings_export.png"),
            Identifier.parse("aeroflux:textures/screens/settings_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (ExportSettingsOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(16, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 16, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_settings_export);
    imagebutton_settings_import = new ImageButton(
        this.leftPos + -42, this.topPos + 17,
        38, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/settings_import.png"),
            Identifier.parse("aeroflux:textures/screens/settings_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (ImportSettingsOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(17, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 17, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_settings_import);
    imagebutton_auto_import = new ImageButton(
        this.leftPos + -16, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/auto_import.png"),
            Identifier.parse("aeroflux:textures/screens/auto_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (AutoImportOnProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(18, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 18, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_auto_import);
    imagebutton_auto_import_off = new ImageButton(
        this.leftPos + -16, this.topPos + 30,
        12, 12,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/auto_import_off.png"),
            Identifier.parse("aeroflux:textures/screens/auto_import_off.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (AutoImportOffProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(19, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 19, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_auto_import_off);
    imagebutton_side_export = new ImageButton(
        this.leftPos + -29, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputUpProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(20, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 20, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export);
    imagebutton_side_export1 = new ImageButton(
        this.leftPos + -42, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputLeftProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(21, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 21, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export1);
    imagebutton_side_export2 = new ImageButton(
        this.leftPos + -29, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputFrontProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(22, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 22, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export2);
    imagebutton_side_export3 = new ImageButton(
        this.leftPos + -16, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputRightProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(23, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 23, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export3);
    imagebutton_side_export4 = new ImageButton(
        this.leftPos + -29, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputDownProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(24, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 24, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export4);
    imagebutton_side_export5 = new ImageButton(
        this.leftPos + -16, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_export.png"),
            Identifier.parse("aeroflux:textures/screens/side_export.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (OutputBackProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(25, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 25, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export5);
    imagebutton_side_button_no6 = new ImageButton(
        this.leftPos + -29, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(26, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 26, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no6);
    imagebutton_side_button_no7 = new ImageButton(
        this.leftPos + -42, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(27, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 27, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no7);
    imagebutton_side_button_no8 = new ImageButton(
        this.leftPos + -29, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(28, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 28, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no8);
    imagebutton_side_button_no9 = new ImageButton(
        this.leftPos + -16, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(29, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 29, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no9);
    imagebutton_side_button_no10 = new ImageButton(
        this.leftPos + -29, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(30, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 30, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no10);
    imagebutton_side_button_no11 = new ImageButton(
        this.leftPos + -16, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_button_no.png"),
            Identifier.parse("aeroflux:textures/screens/side_button_no.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (SettingsStatusImportProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(31, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 31, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_button_no11);
    imagebutton_side_export6 = new ImageButton(
        this.leftPos + -29, this.topPos + 30,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputUpProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(32, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 32, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_export6);
    imagebutton_side_import = new ImageButton(
        this.leftPos + -42, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputLeftProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(33, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 33, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_import);
    imagebutton_side_import1 = new ImageButton(
        this.leftPos + -29, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputFrontProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(34, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 34, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_import1);
    imagebutton_side_import2 = new ImageButton(
        this.leftPos + -16, this.topPos + 43,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputRightProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(35, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 35, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_import2);
    imagebutton_side_import3 = new ImageButton(
        this.leftPos + -29, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputDownProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(36, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 36, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_import3);
    imagebutton_side_import4 = new ImageButton(
        this.leftPos + -16, this.topPos + 56,
        -1, -1,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/side_import.png"),
            Identifier.parse("aeroflux:textures/screens/side_import.png")),
        e -> {
          int x = TankGUIScreen.this.x;
          int y = TankGUIScreen.this.y;
          if (InputBackProcedure.execute(world, x, y, z)) {
            ClientPacketDistributor.sendToServer(new TankGUIButtonMessage(37, x, y, z));
            TankGUIButtonMessage.handleButtonAction(entity, 37, x, y, z);
          }
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_side_import4);
    imagebutton_arrow_right = new ImageButton(
        this.leftPos + 80, this.topPos + 59,
        16, 16,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/arrow_right.png"),
            Identifier.parse("aeroflux:textures/screens/arrow_right.png")),
        e -> {
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_arrow_right);
    imagebutton_bucket = new ImageButton(
        this.leftPos + 62, this.topPos + 59,
        16, 16,
        new WidgetSprites(Identifier.parse("aeroflux:textures/screens/bucket.png"),
            Identifier.parse("aeroflux:textures/screens/bucket.png")),
        e -> {
        }) {
      @Override
      public void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0,
            0, width, height, width, height);
      }
    };
    this.addRenderableWidget(imagebutton_bucket);
  }

  @Override
  protected void containerTick() {
    super.containerTick();
    this.imagebutton_side_button_no.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no1.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no2.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no3.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no4.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no5.visible = SettingsStatusExportProcedure.execute(world, x, y, z);
    this.imagebutton_settings_open.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_rotate.visible = SettingsStatusProcedure.execute(world);
    this.imagebutton_redstone_alwayson.visible = AlwaysOnProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_required.visible = RedstoneRequiredProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_off.visible = RedstoneSignalOffProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_on.visible = RedstoneSignalOnProcedure.execute(world, x, y, z);
    this.imagebutton_redstone_signal_level.visible = RedstoneSignalLevelProcedure.execute(world, x, y, z);
    this.imagebutton_auto_export_off.visible = AutoExportOffProcedure.execute(world, x, y, z);
    this.imagebutton_auto_export.visible = AutoExportOnProcedure.execute(world, x, y, z);
    this.imagebutton_settings_export.visible = ExportSettingsOnProcedure.execute(world, x, y, z);
    this.imagebutton_settings_import.visible = ImportSettingsOnProcedure.execute(world, x, y, z);
    this.imagebutton_auto_import.visible = AutoImportOnProcedure.execute(world, x, y, z);
    this.imagebutton_auto_import_off.visible = AutoImportOffProcedure.execute(world, x, y, z);
    this.imagebutton_side_export.visible = OutputUpProcedure.execute(world, x, y, z);
    this.imagebutton_side_export1.visible = OutputLeftProcedure.execute(world, x, y, z);
    this.imagebutton_side_export2.visible = OutputFrontProcedure.execute(world, x, y, z);
    this.imagebutton_side_export3.visible = OutputRightProcedure.execute(world, x, y, z);
    this.imagebutton_side_export4.visible = OutputDownProcedure.execute(world, x, y, z);
    this.imagebutton_side_export5.visible = OutputBackProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no6.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no7.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no8.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no9.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no10.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_button_no11.visible = SettingsStatusImportProcedure.execute(world, x, y, z);
    this.imagebutton_side_export6.visible = InputUpProcedure.execute(world, x, y, z);
    this.imagebutton_side_import.visible = InputLeftProcedure.execute(world, x, y, z);
    this.imagebutton_side_import1.visible = InputFrontProcedure.execute(world, x, y, z);
    this.imagebutton_side_import2.visible = InputRightProcedure.execute(world, x, y, z);
    this.imagebutton_side_import3.visible = InputDownProcedure.execute(world, x, y, z);
    this.imagebutton_side_import4.visible = InputBackProcedure.execute(world, x, y, z);
  }
}
