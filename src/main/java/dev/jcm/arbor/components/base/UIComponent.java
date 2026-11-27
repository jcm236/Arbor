package dev.jcm.arbor.components.base;

import dev.jcm.arbor.layout.Layout;
import dev.jcm.arbor.theme.Theme;

/**
 * The base of all UI Components
 */
public interface UIComponent {
    UIComponent build(Layout layout, Theme theme);
}
