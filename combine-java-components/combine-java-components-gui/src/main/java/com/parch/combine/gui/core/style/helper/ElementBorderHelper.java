package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.ConstantHelper;
import com.parch.combine.gui.core.style.config.ElementBorderConfig;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ElementBorderHelper {

    private ElementBorderHelper(){}

    public static void set(JComponent component, ElementBorderConfig config) {
        if (config == null) {
            return;
        }

        Border border = null;
        if (config.getSize() != null && config.getSize() > 0) {
            border = new LineBorder(config.getColor() == null ? Color.WHITE : ConstantHelper.color(config.getColor()), config.getSize());
        }
        EmptyBorder empty = null;
        if (config.getTop() != null || config.getLeft() != null || config.getBottom() != null || config.getRight() != null) {
            empty = new EmptyBorder(config.getTop() == null ? 0 : config.getTop(), config.getLeft() == null ? 0 : config.getLeft(),
                    config.getBottom() == null ? 0 : config.getBottom(), config.getRight() == null ? 0 : config.getRight());
        }

        if (border == null) {
            component.setBorder(empty);
        } else if (empty == null) {
            component.setBorder(border);
        } else {
            component.setBorder(new CompoundBorder(border, empty));
        }
    }
}
