package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.enums.LayoutTypeEnum;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

public class ElementLayoutHelper {

    private ElementLayoutHelper(){}

    public static void init(JComponent component, ElementConfig config) {
        if (!(component instanceof JPanel)) {
            return;
        }

        LayoutTypeEnum layout = LayoutTypeEnum.get(config == null ? null : config.getLayout());
        switch (layout) {
            case FLOW:
                component.setLayout(new FlowLayout(FlowLayout.LEFT));
                break;
            case GRID:
            default:
                component.setLayout(new GridBagLayout());
                break;
        }
    }
}
