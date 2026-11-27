package dev.jcm.arbortest.tests;

import dev.jcm.arbor.components.base.UIComponent;
import dev.jcm.arbor.components.interactable.Button;
import dev.jcm.arbor.components.layout.Center;
import dev.jcm.arbor.components.render.Transform;
import dev.jcm.arbor.components.utils.Sized;
import dev.jcm.arbor.components.utils.Text;
import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;
import dev.jcm.arbor.utils.Size;
import dev.jcm.arbor.utils.Transformation;
import org.joml.Vector3f;

public class TestTransform implements UIComponent {
    @Override
    public UIComponent build(Layout layout, Theme theme) {
        return new Center(
                new Transform(
                        new Sized(Size.staticSize(20, 20),
                                new Button.Builder()
                                        .withOnPress((btn) -> System.out.println("Ho"))
                                        .build(new Text.Builder("Halo"))),
                        Transformation.translate(new Vector3f(0, 30, 1))
                ));
    }
}
