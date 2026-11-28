package dev.jcm.arbor.components.utils;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.SimpleVec2i;
import dev.jcm.arbor.utils.Size;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Renderable;

import java.util.ArrayList;
import java.util.List;

public final class Clip implements PrimitiveUIComponent {

    private final UIComponent child;
    private final SimpleVec2i offset;
    private final Size size;

    public Clip(UIComponent child, SimpleVec2i offset, Size size) {
        this.child = child;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {

        List<Renderable> childrenRenderables = new ArrayList<>();
        BuildContext innerContext = new BuildContext(childrenRenderables, context.tooltips(), context.dynamicUIComponents(), context.eventListeners(), context.slots());
        SimpleVec2i childSize = UIBuilder.build(layout, theme, child, innerContext);

        Window window = Minecraft.getInstance().getWindow();

        SimpleVec2i scaledClipSize = size.get(childSize);
        SimpleVec2i scaledPosition = layout.getPosition(childSize);
        scaledPosition = scaledPosition.addNew(offset);

        double guiScale = window.getGuiScale();

        int glX = (int) (scaledPosition.x() * guiScale);
        int glY = (int) ((window.getGuiScaledHeight() - (scaledPosition.y() + scaledClipSize.y())) * guiScale);
        int glWidth = (int) (scaledClipSize.x() * guiScale);
        int glHeight = (int) (scaledClipSize.y() * guiScale);

        context.renderables().add((graphics, pMouseX, pMouseY, pPartialTicks) -> {
            RenderSystem.enableScissor(glX, glY, glWidth, glHeight);

            for (Renderable renderable : childrenRenderables) {
                renderable.render(graphics, pMouseX, pMouseY, pPartialTicks);
            }

            RenderSystem.disableScissor();
        });

        return childSize;
    }

    public static final class Builder {
        private SimpleVec2i offset;
        private Size size;

        public Builder() {
        }

        public Builder withOffset(SimpleVec2i offset) {
            this.offset = offset;
            return this;
        }

        public Builder withSize(Size size) {
            this.size = size;
            return this;
        }

        public Clip build(UIComponent child) {
            return new Clip(child, offset == null ? SimpleVec2i.zero() : offset, size == null ? Size.percentage(1f) : size);
        }
    }
}
