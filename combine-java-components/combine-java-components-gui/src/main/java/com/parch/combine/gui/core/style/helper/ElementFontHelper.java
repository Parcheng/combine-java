package com.parch.combine.gui.core.style.helper;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.style.config.ElementFontConfig;

import javax.swing.JComponent;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementFontHelper {

    private ElementFontHelper(){}

    private static Map<String, Boolean> FONT_SET = new HashMap<>(16);

    public static void set(JComponent component, ElementFontConfig config) {
        if (config == null) {
            return;
        }

        List<String> names = config.getNames();
        Integer style = config.getBold() == null ? null : (config.getBold() ? Font.BOLD : Font.PLAIN);
        Integer size = config.getSize();
        Font sourceFont = component.getFont();
        if (CheckEmptyUtil.isEmpty(names)) {
            if (sourceFont != null) {
                names = names == null ? new ArrayList<>() : names;
                names.add(sourceFont.getName());
            }
        }
        if (size == null) {
            size = sourceFont == null ? 14 : sourceFont.getSize();
        }
        if (style == null) {
            style = sourceFont == null ? Font.PLAIN : sourceFont.getStyle();
        }

        component.setFont(new Font(getFont(names), style, size));
    }

    private static String getFont(List<String> names) {
        if (CheckEmptyUtil.isEmpty(names)) {
            return null;
        }

        // 获取系统的GraphicsEnvironment对象
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();

        for (String name : names) {
            Boolean has = FONT_SET.get(name);
            if (has == null) {
                for (String systemName : fontNames) {
                    if (systemName.equals(name)) {
                        FONT_SET.put(name, true);
                        return name;
                    }
                }
                FONT_SET.put(name, false);
            } else if (has) {
                return name;
            }
        }

        return null;
    }
}
