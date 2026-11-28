package dev.jcm.arbor.components.utils;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.render.Render;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Color;
import dev.jcm.arbor.utils.RenderProvider;
import dev.jcm.arbor.utils.SimpleVec2i;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;

public final class Text implements PrimitiveUIComponent, RenderProvider {

    private static final String ellipsisText = "...";
    private static int ellipsisWidth = 0;
    private final FormattedText text;
    private final OverflowBehaviour overflowBehaviour;
    private Color color;

    public Text(FormattedText text, Color color, OverflowBehaviour overflowBehaviour) {
        this.text = text;
        this.color = color;
        this.overflowBehaviour = overflowBehaviour;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        color = color == null ? theme.getTextColor() : color;

        Font fontRenderer = Minecraft.getInstance().font;
        int width = fontRenderer.width(text);
        ellipsisWidth = fontRenderer.width(ellipsisText);

        SimpleVec2i size = this.measure(layout, theme);
        width = size.x();
        int height = size.y()

        int x = layout.getPosition(Axis.HORIZONTAL, width);
        int y = layout.getPosition(Axis.VERTICAL, height);

        int finalWidth = width;
        if (overflowBehaviour != OverflowBehaviour.CLIP) {
            context.renderables().add((graphics, pMouseX, pMouseY, pPartialTicks) -> render(graphics, pMouseX, pMouseY, pPartialTicks, x, y, finalWidth, height));
        } else {
            UIBuilder.build(
                    new Layout(width, height, x, y),
                    theme,
                    new Clip.Builder().build(new Render(this)),
                    context
            );
        }

        return new SimpleVec2i(width, height);
    }

    private SimpleVec2i computeSize(Layout layout) {
        Font font = Minecraft.getInstance().font;
        int width = font.width(text);
        boolean willOverflow = width > layout.getWidth();
        if (willOverflow) width = layout.getWidth();
        int height = willOverflow && overflowBehaviour == OverflowBehaviour.WRAP
                ? font.wordWrapHeight(text.getString(), width)
                : 8;
        return new SimpleVec2i(width, height);
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        return computeSize(layout);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks, int x, int y, int width, int height) {
        Font font = Minecraft.getInstance().font;

        switch (overflowBehaviour) {
            case WRAP -> graphics.drawWordWrap(font, text, x, y, width, color.getAARRGGBB());
            case ELLIPSIS ->
                    graphics.drawString(font, font.substrByWidth(text, width - ellipsisWidth).getString() + ellipsisText, x, y, color.getAARRGGBB(), true);
            default -> graphics.drawString(font, text.getString(), x, y, color.getAARRGGBB(), true);
        }
    }

    public enum OverflowBehaviour {
        OVERFLOW,
        WRAP,
        CLIP,
        ELLIPSIS
    }

    public static final class Builder implements UIComponent {
        private final Component text;
        private Color color;
        private OverflowBehaviour overflowBehaviour;

        public Builder(Component text) {
            this.text = text;
            this.color = Color.WHITE;
        }

        public Builder(String text) {
            this.text = Component.literal(text);
        }

        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder withOverflowBehaviour(OverflowBehaviour overflowBehaviour) {
            this.overflowBehaviour = overflowBehaviour;
            return this;
        }

        @Override
        public UIComponent build(Layout layout, Theme theme) {
            return build();
        }

        public Text build() {
            return new Text(
                    text,
                    color,
                    overflowBehaviour == null ? OverflowBehaviour.OVERFLOW : overflowBehaviour
            );
        }
    }
}
