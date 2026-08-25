package net.drakma.aeroflux.jade;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;

/**
 * Auto-generated Jade Plugin for MCreator
 * Live dynamic progress bars (Arrow, Jade Bar, Unicode Bar) and NBT watchers for Jade HUD.
 */
@WailaPlugin("aeroflux")
public class AerofluxJadePlugin implements IWailaPlugin {

    public static final Identifier JADE_BLOCK_PROVIDER = Identifier.fromNamespaceAndPath("aeroflux", "jade_block_provider");

    static {
        disableJadeTranslationCheck();
    }

    @Override
    public void register(IWailaCommonRegistration registration) {
        IServerDataProvider<BlockAccessor> dataProvider = new IServerDataProvider<BlockAccessor>() {
            @Override
            public void appendServerData(CompoundTag data, BlockAccessor accessor) {
                BlockEntity be = accessor.getBlockEntity();
                if (be != null) {
                    CompoundTag persistent = be.getPersistentData();
                    if (persistent != null) {
                        copyAllTags(persistent, data);
                        try {
                            Tag forgeData = persistent.get("ForgeData");
                            if (forgeData instanceof CompoundTag) {
                                copyAllTags((CompoundTag) forgeData, data);
                            }
                        } catch (Throwable ignored) {}
                    }
                    try {
                        CompoundTag beTag = be.saveWithoutMetadata(accessor.getLevel().registryAccess());
                        if (beTag != null) {
                            copyAllTags(beTag, data);
                        }
                    } catch (Throwable ignored) {}
                    data.putBoolean("JadeSynced", true);
                }
            }

            @Override
            public Identifier getUid() {
                return JADE_BLOCK_PROVIDER;
            }
        };

        registration.registerBlockDataProvider(dataProvider, Block.class);
        registration.registerBlockDataProvider(dataProvider, BlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        disableJadeTranslationCheck();

        registration.registerBlockComponent(new IBlockComponentProvider() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                CompoundTag serverData = accessor.getServerData();
                BlockEntity be = accessor.getBlockEntity();

                CompoundTag data = new CompoundTag();
                if (be != null && be.getPersistentData() != null) {
                    copyAllTags(be.getPersistentData(), data);
                    try {
                        Tag forgeData = be.getPersistentData().get("ForgeData");
                        if (forgeData instanceof CompoundTag) {
                            copyAllTags((CompoundTag) forgeData, data);
                        }
                    } catch (Throwable ignored) {}
                }
                if (serverData != null) {
                    copyAllTags(serverData, data);
                }

                boolean extended = isShiftPressed(accessor);
                String blockId = BuiltInRegistries.BLOCK.getKey(accessor.getBlock()).toString();
                String blockPath = BuiltInRegistries.BLOCK.getKey(accessor.getBlock()).getPath();
                boolean blockMatched = false;

                // Block Config: Wooden Collector [aeroflux:wooden_collector]
                if (blockId.equalsIgnoreCase("aeroflux:wooden_collector") || blockPath.equalsIgnoreCase("wooden_collector")) {
                    blockMatched = true;
                    double cur_Progress = findNumber(data, "Progress", "progress", "PROGRESS");
                    double max_Progress = findNumber(data, "MaxProgress", "maxprogress", "MAXPROGRESS");
                    if (cur_Progress >= 0) {
                        if (max_Progress <= 0) max_Progress = 100.0;
                        float ratio = (float) Math.min(Math.max(cur_Progress / max_Progress, 0.0), 1.0);
                        int percent = (int) (ratio * 100);
                        renderJadeBar(tooltip, "aeroflux:wooden_collector_Progress", "", ratio, percent, "IN_BAR", 12, 0xff4caf50, 0xff2a2a2a, 0xff121212, 0xffffffff);
                        if (extended) {
                            tooltip.add(Component.literal("§7Details: §f" + (int) cur_Progress + " / " + (int) max_Progress));
                        }
                    }
                }

                // Block Config: Stone Collector [aeroflux:stone_collector]
                if (blockId.equalsIgnoreCase("aeroflux:stone_collector") || blockPath.equalsIgnoreCase("stone_collector")) {
                    blockMatched = true;
                    double cur_Progress = findNumber(data, "Progress", "progress", "PROGRESS");
                    double max_Progress = findNumber(data, "MaxProgress", "maxprogress", "MAXPROGRESS");
                    if (cur_Progress >= 0) {
                        if (max_Progress <= 0) max_Progress = 100.0;
                        float ratio = (float) Math.min(Math.max(cur_Progress / max_Progress, 0.0), 1.0);
                        int percent = (int) (ratio * 100);
                        renderJadeBar(tooltip, "aeroflux:stone_collector_Progress", "", ratio, percent, "IN_BAR", 12, 0xff4caf50, 0xff2a2a2a, 0xff121212, 0xffffffff);
                        if (extended) {
                            tooltip.add(Component.literal("§7Details: §f" + (int) cur_Progress + " / " + (int) max_Progress));
                        }
                    }
                }

            }

            @Override
            public Identifier getUid() {
                return JADE_BLOCK_PROVIDER;
            }
        }, Block.class);
    }

    private static void renderArrow(ITooltip tooltip, String label, float ratio, int percent, String pctPos, int barColor, int bgColor, int borderColor) {
        try {
            if ("ABOVE".equals(pctPos)) {
                tooltip.add(Component.literal((label.isEmpty() ? "" : (label + ": ")) + "§e" + percent + "%"));
                tooltip.add(snownee.jade.api.ui.JadeUI.progressArrow(ratio).alignSelfCenter());
            } else {
                if (!label.isEmpty()) {
                    tooltip.add(Component.literal("§f" + label + ": "));
                    tooltip.append(snownee.jade.api.ui.JadeUI.progressArrow(ratio).alignSelfCenter());
                } else {
                    tooltip.add(snownee.jade.api.ui.JadeUI.progressArrow(ratio).alignSelfCenter());
                }
                if (!"NONE".equals(pctPos)) {
                    tooltip.append(snownee.jade.api.ui.JadeUI.text(Component.literal(" §e" + percent + "%")).alignSelfCenter());
                }
            }
        } catch (Throwable fallback) {
            renderBlockUnicodeBar(tooltip, label, ratio, percent, pctPos, barColor, bgColor, borderColor);
        }
    }

    private static void renderJadeBar(ITooltip tooltip, String animKey, String label, float ratio, int percent, String pctPos, int barHeight, int barColor, int bgStartColor, int bgEndColor, int borderColor) {
        try {
            if (!label.isEmpty()) {
                if ("ABOVE".equals(pctPos)) {
                    tooltip.add(Component.literal("§f" + label + ": §e" + percent + "%"));
                } else {
                    tooltip.add(Component.literal("§f" + label + "\n"));
                }
                try {
                    tooltip.setLineMargin(-1, snownee.jade.api.ui.ScreenDirection.DOWN, 2);
                } catch (Throwable ignored) {}
            } else if ("ABOVE".equals(pctPos)) {
                tooltip.add(Component.literal("§e" + percent + "%"));
                try {
                    tooltip.setLineMargin(-1, snownee.jade.api.ui.ScreenDirection.DOWN, 2);
                } catch (Throwable ignored) {}
            }
            snownee.jade.api.ui.Element barElem = new CustomProgressBarElement(animKey, 120, barHeight, ratio, percent, pctPos, barColor, bgStartColor, bgEndColor, borderColor);
            if ("TRAILING".equals(pctPos)) {
                tooltip.add(barElem.alignSelfCenter());
                tooltip.append(snownee.jade.api.ui.JadeUI.text(Component.literal(" §e" + percent + "%")).alignSelfCenter());
            } else {
                tooltip.add(barElem);
            }
        } catch (Throwable fallback) {
            renderBlockUnicodeBar(tooltip, label, ratio, percent, pctPos, barColor, bgStartColor, borderColor);
        }
    }

    public static class CustomProgressBarElement extends snownee.jade.api.ui.Element {
        private static final java.util.Map<String, Float> ANIMATED_RATIOS = new java.util.concurrent.ConcurrentHashMap<>();
        private static final java.util.Map<String, Long> LAST_RENDER_TIMES = new java.util.concurrent.ConcurrentHashMap<>();

        private final String animKey;
        private final float ratio;
        private final int percent;
        private final String pctPos;
        private final int barColor;
        private final int bgStartColor;
        private final int bgEndColor;
        private final int borderColor;

        public CustomProgressBarElement(String animKey, int width, int height, float ratio, int percent, String pctPos, int barColor, int bgStartColor, int bgEndColor, int borderColor) {
            this.animKey = animKey != null ? animKey : "default_bar";
            this.width = width;
            this.height = Math.max(4, height);
            this.ratio = Math.min(Math.max(ratio, 0.0f), 1.0f);
            this.percent = percent;
            this.pctPos = pctPos != null ? pctPos : "NONE";
            this.barColor = (barColor & 0xFF000000) == 0 ? (0xFF000000 | barColor) : barColor;
            this.bgStartColor = (bgStartColor & 0xFF000000) == 0 ? (0xFF000000 | bgStartColor) : bgStartColor;
            this.bgEndColor = (bgEndColor & 0xFF000000) == 0 ? (0xFF000000 | bgEndColor) : bgEndColor;
            this.borderColor = (borderColor & 0xFF000000) == 0 ? (0xFF000000 | borderColor) : borderColor;
        }

        private float getSmoothedRatio() {
            long now = System.currentTimeMillis();
            Long lastTime = LAST_RENDER_TIMES.put(animKey, now);
            Float current = ANIMATED_RATIOS.get(animKey);
            if (current == null || lastTime == null || (now - lastTime) > 1000) {
                ANIMATED_RATIOS.put(animKey, ratio);
                return ratio;
            }
            float deltaSec = Math.min(0.1f, (now - lastTime) / 1000.0f);
            if (ratio < current - 0.2f) {
                current = ratio;
            } else {
                float speed = 12.0f;
                current = current + (ratio - current) * Math.min(1.0f, deltaSec * speed);
            }
            ANIMATED_RATIOS.put(animKey, current);
            return current;
        }

        @Override
        public Component getNarration() {
            return Component.literal(percent + "%");
        }

        @Override
        public void extractRenderState(net.minecraft.client.gui.GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
            int x = getX();
            int y = getY();
            int w = getWidth();
            int h = getHeight();

            // 1. Draw Border (1px outline)
            graphics.fill(x, y, x + w, y + 1, borderColor);
            graphics.fill(x, y + h - 1, x + w, y + h, borderColor);
            graphics.fill(x, y + 1, x + 1, y + h - 1, borderColor);
            graphics.fill(x + w - 1, y + 1, x + w, y + h - 1, borderColor);

            // 2. Draw Background Horizontal Gradient from Left to Right
            int innerW = w - 2;
            int innerH = h - 2;
            if (innerW > 0 && innerH > 0) {
                fillHorizontalGradient(graphics, x + 1, y + 1, innerW, innerH, bgStartColor, bgEndColor);
            }

            // 3. Draw Smoothly Interpolated Progress Bar Fill
            float smoothRatio = getSmoothedRatio();
            int fillW = (int) Math.round(innerW * smoothRatio);
            if (fillW > 0 && innerH > 0) {
                graphics.fill(x + 1, y + 1, x + 1 + Math.min(innerW, fillW), y + 1 + innerH, barColor);
            }

            // 4. Draw Scaled & Centered Percentage in Middle if IN_BAR
            if ("IN_BAR".equals(pctPos)) {
                String text = percent + "%";
                net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                if (mc.font != null) {
                    int strW = mc.font.width(text);
                    float availableH = Math.max(4.0f, (float) (h - 2));
                    float fontH = 9.0f;
                    float scale = Math.min(1.0f, (availableH * 0.85f) / fontH);
                    if (scale <= 0.1f) scale = 0.5f;

                    float scaledW = strW * scale;
                    float scaledH = fontH * scale;
                    float textX = x + (w - scaledW) / 2.0f;
                    float textY = y + (h - scaledH) / 2.0f + 1.0f;

                    graphics.pose().pushMatrix();
                    graphics.pose().translate(textX, textY);
                    graphics.pose().scale(scale, scale);
                    snownee.jade.api.ui.IDisplayHelper.get().drawText(graphics, text, 0f, 0f, 0xFFFFFFFF);
                    graphics.pose().popMatrix();
                }
            }
        }

        private static void fillHorizontalGradient(net.minecraft.client.gui.GuiGraphicsExtractor graphics, int x, int y, int w, int h, int colorStart, int colorEnd) {
            if (w <= 0 || h <= 0) return;
            if (colorStart == colorEnd) {
                graphics.fill(x, y, x + w, y + h, colorStart);
                return;
            }
            int a1 = (colorStart >> 24) & 0xFF;
            int r1 = (colorStart >> 16) & 0xFF;
            int g1 = (colorStart >> 8) & 0xFF;
            int b1 = colorStart & 0xFF;

            int a2 = (colorEnd >> 24) & 0xFF;
            int r2 = (colorEnd >> 16) & 0xFF;
            int g2 = (colorEnd >> 8) & 0xFF;
            int b2 = colorEnd & 0xFF;

            for (int col = 0; col < w; col++) {
                float f = (float) col / Math.max(1, w - 1);
                int a = (int) (a1 + (a2 - a1) * f);
                int r = (int) (r1 + (r2 - r1) * f);
                int g = (int) (g1 + (g2 - g1) * f);
                int b = (int) (b1 + (b2 - b1) * f);
                int colColor = (a << 24) | (r << 16) | (g << 8) | b;
                graphics.fill(x + col, y, x + col + 1, y + h, colColor);
            }
        }
    }

    private static void renderBlockUnicodeBar(ITooltip tooltip, String label, float ratio, int percent, String pctPos, int barColor, int bgColor, int borderColor) {
        int totalBars = 12;
        int filled = (int) Math.round(ratio * totalBars);
        MutableComponent barComp = Component.literal("");
        barComp.append(Component.literal("[").withStyle(s -> s.withColor(borderColor)));
        for (int i = 0; i < totalBars; i++) {
            if ("IN_BAR".equals(pctPos) && i == totalBars / 2) {
                barComp.append(Component.literal(" " + percent + "% ").withStyle(s -> s.withColor(0xFFFFFFFF)));
            }
            if (i < filled) {
                barComp.append(Component.literal("█").withStyle(s -> s.withColor(barColor)));
            } else {
                barComp.append(Component.literal("░").withStyle(s -> s.withColor(bgColor)));
            }
        }
        barComp.append(Component.literal("]").withStyle(s -> s.withColor(borderColor)));
        MutableComponent line = label.isEmpty() ? Component.literal("") : Component.literal("§f" + label + ": ");
        line.append(barComp);
        if ("TRAILING".equals(pctPos)) {
            line.append(Component.literal(" §e" + percent + "%"));
        }
        tooltip.add(line);
    }

    private static void disableJadeTranslationCheck() {
        try {
            Class<?> jc = Class.forName("snownee.jade.JadeClient");
            for (java.lang.reflect.Field f : jc.getDeclaredFields()) {
                if (f.getName().equals("translationChecked") || (f.getType() == boolean.class && java.lang.reflect.Modifier.isStatic(f.getModifiers()))) {
                    f.setAccessible(true);
                    f.setBoolean(null, true);
                }
            }
        } catch (Throwable ignored) {}
    }

    private static boolean isShiftPressed(BlockAccessor accessor) {
        try {
            if (accessor.showDetails()) return true;
        } catch (Throwable ignored) {}
        try {
            if (accessor.getPlayer() != null && accessor.getPlayer().isShiftKeyDown()) return true;
        } catch (Throwable ignored) {}
        try {
            Class<?> screenCls = Class.forName("net.minecraft.client.gui.screens.Screen");
            java.lang.reflect.Method hasShift = screenCls.getMethod("hasShiftDown");
            if ((Boolean) hasShift.invoke(null)) return true;
        } catch (Throwable ignored) {}
        return false;
    }

    private static void copyAllTags(CompoundTag src, CompoundTag dest) {
        if (src == null || dest == null) return;
        try {
            for (String key : src.keySet()) {
                if (isSystemTag(key)) continue;
                Tag tag = src.get(key);
                if (tag != null) {
                    dest.put(key, tag.copy());
                }
            }
        } catch (Throwable ignored) {}
    }

    private static double findNumber(CompoundTag data, String... keys) {
        if (data == null) return -1.0;
        for (String key : keys) {
            if (data.contains(key)) {
                try {
                    Tag tag = data.get(key);
                    if (tag != null) {
                        String raw = tag.toString().replaceAll("^\"|\"$", "").replaceAll("[^0-9.-]", "");
                        if (!raw.isEmpty()) {
                            return Double.parseDouble(raw);
                        }
                    }
                } catch (Throwable ignored) {}
            }
        }
        // Case-insensitive fallback scan
        for (String key : keys) {
            for (String actualKey : data.keySet()) {
                if (actualKey.equalsIgnoreCase(key)) {
                    try {
                        Tag tag = data.get(actualKey);
                        if (tag != null) {
                            String raw = tag.toString().replaceAll("^\"|\"$", "").replaceAll("[^0-9.-]", "");
                            if (!raw.isEmpty()) {
                                return Double.parseDouble(raw);
                            }
                        }
                    } catch (Throwable ignored) {}
                }
            }
        }
        return -1.0;
    }

    private static boolean isSystemTag(String key) {
        if (key == null) return true;
        String lower = key.toLowerCase();
        return lower.equals("id") || lower.equals("x") || lower.equals("y") || lower.equals("z") ||
               lower.equals("items") || lower.equals("item_storage") || lower.contains("minecraft:") ||
               lower.equals("blockid") || lower.equals("components") || lower.equals("blockentitytag") ||
               lower.equals("neoforge:attachments") || lower.equals("neoforge:data") || lower.startsWith("neoforge:");
    }
}
