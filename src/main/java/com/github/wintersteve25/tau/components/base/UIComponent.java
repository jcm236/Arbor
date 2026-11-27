package com.github.wintersteve25.tau.components.base;

import com.github.wintersteve25.tau.layout.Layout;
import com.github.wintersteve25.tau.theme.Theme;

/**
 * The base of all UI Components
 */
public interface UIComponent {
    UIComponent build(Layout layout, Theme theme);
}
