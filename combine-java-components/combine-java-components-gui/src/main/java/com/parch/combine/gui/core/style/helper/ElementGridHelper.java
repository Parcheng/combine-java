package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.config.ElementGridConfig;

import javax.swing.*;
import java.awt.*;

public class ElementGridHelper {

    private ElementGridHelper(){}

    public static void setSubComponent(JComponent component, JComponent subComponent, ElementGridConfig config) {
        if (component instanceof JPanel) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;

            if (config != null) {
                if (config.getWeightX() != null) {
                    gbc.weightx = config.getWeightX();
                }
                if (config.getWeightY() != null) {
                    gbc.weighty = config.getWeightY();
                }
                if (config.getPositionX() != null) {
                    gbc.gridx = config.getPositionX();
                }
                if (config.getPositionY() != null) {
                    gbc.gridy = config.getPositionY();
                }
                if (config.getOccupyX() != null) {
                    gbc.gridwidth = config.getOccupyX();
                }
                if (config.getOccupyY() != null) {
                    gbc.gridheight = config.getOccupyY();
                }
            }

            component.add(subComponent, gbc);
            return;
        }

        component.add(subComponent);
    }
}
