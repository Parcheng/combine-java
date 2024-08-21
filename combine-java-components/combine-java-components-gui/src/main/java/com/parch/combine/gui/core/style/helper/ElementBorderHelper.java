package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.ConstantHelper;
import com.parch.combine.gui.core.style.config.ElementBorderConfig;

import javax.swing.*;
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

        // 主边框
        if (config.getSize() != null && config.getSize() > 0) {
            border = buildBorder(config.getType(), config.getColor(), config.getSize());
        }

        // 上下左右副边框
        if (config.getLeftConfig() != null && config.getLeftConfig().getSize() != null && config.getLeftConfig().getSize() > 0) {
            Border leftBorder = buildBorder(config.getLeftConfig().getType(), config.getLeftConfig().getColor(), config.getLeftConfig().getSize());
            border = border == null ? leftBorder : new CompoundBorder(border, leftBorder);
        }
        if (config.getRightConfig() != null && config.getRightConfig().getSize() != null && config.getRightConfig().getSize() > 0) {
            Border rightConfig = buildBorder(config.getRightConfig().getType(), config.getRightConfig().getColor(), config.getRightConfig().getSize());
            border = border == null ? rightConfig : new CompoundBorder(border, rightConfig);
        }
        if (config.getTopConfig() != null && config.getTopConfig().getSize() != null && config.getTopConfig().getSize() > 0) {
            Border topConfig = buildBorder(config.getTopConfig().getType(), config.getTopConfig().getColor(), config.getTopConfig().getSize());
            border = border == null ? topConfig : new CompoundBorder(border, topConfig);
        }
        if (config.getBottomConfig() != null && config.getBottomConfig().getSize() != null && config.getBottomConfig().getSize() > 0) {
            Border bottomConfig = buildBorder(config.getBottomConfig().getType(), config.getBottomConfig().getColor(), config.getBottomConfig().getSize());
            border = border == null ? bottomConfig : new CompoundBorder(border, bottomConfig);
        }

        // 边框内部填充
        if (config.getTop() != null || config.getLeft() != null || config.getBottom() != null || config.getRight() != null) {
            EmptyBorder empty = new EmptyBorder(config.getTop() == null ? 0 : config.getTop(), config.getLeft() == null ? 0 : config.getLeft(),
                    config.getBottom() == null ? 0 : config.getBottom(), config.getRight() == null ? 0 : config.getRight());
            border = border == null ? empty : new CompoundBorder(border, empty);
        }

        // 设置边框
        if (border != null) {
            component.setBorder(border);
        }
    }

    private static Border buildBorder(String type, String color, Integer size) {
        return new LineBorder(color == null ? Color.WHITE : ConstantHelper.color(color), size);
    }
}
