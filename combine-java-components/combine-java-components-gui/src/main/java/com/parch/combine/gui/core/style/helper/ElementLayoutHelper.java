package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.config.ElementLayoutConfig;
import com.parch.combine.gui.core.style.enums.LayoutAlignTypeEnum;
import com.parch.combine.gui.core.style.enums.LayoutAxisEnum;
import com.parch.combine.gui.core.style.enums.LayoutTypeEnum;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import java.awt.FlowLayout;

public class ElementLayoutHelper {

    private ElementLayoutHelper(){}

    public static void set(JComponent component, ElementLayoutConfig config) {
        if (config == null) {
            return;
        }

        LayoutTypeEnum type = LayoutTypeEnum.get(config.getType());
        switch (type) {
            case FLOW:
                LayoutAlignTypeEnum alignTypeEnum = LayoutAlignTypeEnum.get(config.getAlign());
                component.setLayout(new FlowLayout(alignTypeEnum.getValue(), config.getHgap(), config.getVgap()));
                break;
            case BOX:
                LayoutAxisEnum axis = LayoutAxisEnum.get(config.getAxis());
                component.setLayout(new BoxLayout(component, axis.getValue()));
                break;
        }
    }
}
