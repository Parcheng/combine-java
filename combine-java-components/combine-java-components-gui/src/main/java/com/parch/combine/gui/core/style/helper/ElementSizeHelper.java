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

        Dimension dimension = null;
        if (sizeConfig.getHeight() != null) {
            dimension = new Dimension();
            dimension.width = sizeConfig.getWidth() == null ? Integer.MAX_VALUE : sizeConfig.getWidth();
            dimension.height = sizeConfig.getHeight();

            component.setSize(dimension);
            component.setPreferredSize(dimension);
        }

        if (sizeConfig.getMaxHeight() == null) {
            if (dimension != null) {
                component.setMaximumSize(dimension);
            }
        } else {
            Dimension maxDimension = new Dimension();
            maxDimension.width = sizeConfig.getMaxWidth() == null ? Integer.MAX_VALUE : sizeConfig.getMaxWidth();
            maxDimension.height = sizeConfig.getMaxHeight();
            component.setMaximumSize(maxDimension);
        }

        if (sizeConfig.getMinHeight() == null) {
            if (dimension != null) {
                component.setMinimumSize(dimension);
            }
        } else {
            Dimension minDimension = new Dimension();
            minDimension.width = sizeConfig.getMinWidth() == null ? Integer.MAX_VALUE : sizeConfig.getMinWidth();
            minDimension.height = sizeConfig.getMinHeight();
            component.setMinimumSize(minDimension);
        }
    }
}
