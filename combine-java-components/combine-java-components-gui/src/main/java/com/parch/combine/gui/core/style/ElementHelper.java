package com.parch.combine.gui.core.style;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.AlignmentXEnum;
import com.parch.combine.gui.core.style.enums.AlignmentYEnum;
import com.parch.combine.gui.core.style.enums.LayoutTypeEnum;
import com.parch.combine.gui.core.style.helper.ElementBorderHelper;
import com.parch.combine.gui.core.style.helper.ElementFontHelper;
import com.parch.combine.gui.core.style.helper.ElementGridHelper;
import com.parch.combine.gui.core.style.helper.ElementLayoutHelper;
import com.parch.combine.gui.core.style.helper.ElementSizeHelper;
import com.parch.combine.gui.core.style.settings.ElementGridSettings;

import javax.swing.JComponent;

public class ElementHelper {

    public static void set(JComponent component, ElementConfig elementConfig) {
        ElementLayoutHelper.init(component, elementConfig);

        if (elementConfig == null) {
            return;
        }

        if (CheckEmptyUtil.isNotEmpty(elementConfig.getFgColor())) {
            component.setForeground(ConstantHelper.color(elementConfig.getFgColor()));
        }
        if (CheckEmptyUtil.isNotEmpty(elementConfig.getBgColor())) {
            component.setBackground(ConstantHelper.color(elementConfig.getBgColor()));
        }
        if (elementConfig.getOpaque() != null) {
            component.setOpaque(elementConfig.getOpaque());
        }

        if (elementConfig.getSize() != null) {
            ElementSizeHelper.set(component, elementConfig.getSize());
        }

        if (elementConfig.getBorder() != null) {
            ElementBorderHelper.set(component, elementConfig.getBorder());
        }

        if (elementConfig.getFont() != null) {
            ElementFontHelper.set(component, elementConfig.getFont());
        }
    }

    public static void addSubComponent(JComponent component, JComponent subComponent, ElementGridConfig gridConfig, ElementGridSettings settings) {
        if (component == null || subComponent == null) {
            return;
        }

        ElementGridHelper.setSubComponent(component, subComponent, gridConfig, settings);
    }
}
