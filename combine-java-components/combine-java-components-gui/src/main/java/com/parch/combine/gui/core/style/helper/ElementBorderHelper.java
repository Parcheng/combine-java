package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.ConstantHelper;
import com.parch.combine.gui.core.style.config.ElementBorderConfig;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ElementBorderHelper {

    private ElementBorderHelper(){}

    public static void set(JComponent component, ElementBorderConfig config) {
        if (config == null) {
            return;
        }

        Border border = new LineBorder(ConstantHelper.color(config.getColor()), config.getSize());
        EmptyBorder empty = new EmptyBorder(config.getTop(), config.getLeft(), config.getBottom(), config.getRight());
        component.setBorder(new CompoundBorder(border, empty));
    }
}
