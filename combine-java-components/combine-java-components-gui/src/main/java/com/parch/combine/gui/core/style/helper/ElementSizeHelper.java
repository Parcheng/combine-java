package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.config.ElementSizeConfig;

import javax.swing.JComponent;
import java.awt.Dimension;

public class ElementSizeHelper {

    private ElementSizeHelper(){}

    public static void set(JComponent component, ElementSizeConfig sizeConfig) {
        if (sizeConfig == null) {
            return;
        }

        Dimension dimension = new Dimension();
        dimension.width = sizeConfig.getWidth();
        dimension.height = sizeConfig.getHeight();

        component.setSize(dimension);
        component.setPreferredSize(dimension);
    }
}
