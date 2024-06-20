package com.parch.combine.gui.core.style;

import java.awt.FlowLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ConstantHelper {

    private static Map<Integer, FlowLayout> LAYOUT_MAP = new HashMap<>();
    private static Map<String, Color> COLOR_MAP = new HashMap<>();

    private ConstantHelper() {}

    public static FlowLayout layout(int align) {
        FlowLayout layout = LAYOUT_MAP.get(align);
        if (layout != null) {
            return layout;
        }

        layout = new FlowLayout(align);
        LAYOUT_MAP.put(align, layout);
        return layout;
    }

    public static Color color(String code) {
        if (code == null) {
            return null;
        }

        Color color = COLOR_MAP.get(code);
        if (color != null) {
            return color;
        }

        color = Color.decode(code);
        COLOR_MAP.put(code, color);
        return color;
    }
}
