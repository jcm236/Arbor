package dev.jcm.arbor.components.layout;

import dev.jcm.arbor.build.BuildContext;
import dev.jcm.arbor.build.UIBuilder;
import dev.jcm.arbor.components.base.PrimitiveUIComponent;
import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.layout.Axis;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.layout.LayoutSetting;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.FlexSizeBehaviour;
import dev.jcm.arbor.utils.SimpleVec2i;

import java.util.ArrayList;
import java.util.List;

public class Flex implements PrimitiveUIComponent {

    private final Axis mainAxis;
    private final int spacing;
    private final FlexSizeBehaviour sizeBehaviour;
    private final List<UIComponent> children = new ArrayList<>();
    private final LayoutSetting alignment;

    public Flex(Axis mainAxis, int spacing, FlexSizeBehaviour sizeBehaviour, Iterable<UIComponent> children, LayoutSetting alignment) {
        this.mainAxis = mainAxis;
        this.spacing = spacing;
        this.sizeBehaviour = sizeBehaviour;
        this.alignment = alignment;
        children.forEach(this.children::add);
    }

    @Override
    public SimpleVec2i build(Layout layout, Theme theme, BuildContext context) {
        Axis cross = mainAxis == Axis.HORIZONTAL ? Axis.VERTICAL : Axis.HORIZONTAL;
        int n = children.size();

        SizePlan plan = this.plan(layout, theme);

        int cursor = layout.getPosition(mainAxis, plan.mainSize());
        int crossPos = layout.getPosition(cross, plan.crossSize());
        for (int i = 0; i < n; i++) {
            Layout slot = mainAxis == Axis.HORIZONTAL ? new Layout(plan.lengths()[i], plan.crossSize(), cursor, crossPos) : new Layout(plan.crossSize(), plan.lengths()[i], crossPos, cursor);
            slot.pushLayoutSetting(cross, alignment);
            UIBuilder.build(slot, theme, children.get(i), context);
            cursor += plan.lengths()[i] + spacing;
        }

        return mainAxis == Axis.HORIZONTAL ? new SimpleVec2i(plan.mainSize(), plan.crossSize()) : new SimpleVec2i(plan.crossSize(), plan.mainSize());
    }

    private record SizePlan(int[] lengths, int mainSize, int crossSize) {}

    private SizePlan plan(Layout layout, Theme theme) {
        Axis cross = mainAxis == Axis.HORIZONTAL ? Axis.VERTICAL : Axis.HORIZONTAL;
        int n = children.size();
        int[] lengths = new int[n];
        int totalMain = 0, crossSize = 0;
        for (int i = 0; i < n; i++) {
            SimpleVec2i size = UIBuilder.measure(layout, theme, children.get(i));
            lengths[i] = along(size, mainAxis);
            totalMain += lengths[i];
            crossSize = Math.max(crossSize, along(size, cross));
        }
        int gaps = spacing * Math.max(0, n - 1);
        int mainSize = sizeBehaviour == FlexSizeBehaviour.MAX ? layout.getMaximumLength(mainAxis) : totalMain + gaps;
        if (sizeBehaviour == FlexSizeBehaviour.MAX) crossSize = layout.getMaximumLength(cross);
        return new SizePlan(lengths, mainSize, crossSize);
    }

    @Override
    public SimpleVec2i measure(Layout layout, Theme theme) {
        SizePlan p = plan(layout, theme);
        return mainAxis == Axis.HORIZONTAL ? new SimpleVec2i(p.mainSize(), p.crossSize()) : new SimpleVec2i(p.crossSize(), p.mainSize());
    }

    private static int along(SimpleVec2i v, Axis axis) {
        return axis == Axis.HORIZONTAL ? v.x() : v.y();
    }
}
