package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Slider;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;

public class TestSlider implements UIComponent {

    private double value;

    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Sized(
                        Size.staticSize(200, 20),
                        new Slider.Builder()
                                .withValue(value)
                                .withOnValueChanged((value) -> this.value = value)
                )
        );
    }
}
