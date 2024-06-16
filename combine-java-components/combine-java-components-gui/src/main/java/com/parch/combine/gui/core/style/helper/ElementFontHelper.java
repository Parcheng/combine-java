package com.parch.combine.gui.core.style.helper;

import com.parch.combine.gui.core.style.config.ElementFontConfig;

import javax.swing.JComponent;
import java.awt.Font;

public class ElementFontHelper {

    private ElementFontHelper(){}

    public static void set(JComponent component, ElementFontConfig config) {
        if (config == null) {
            return;
        }

        Font font = new Font(config.getName(), Font.PLAIN, config.getSize());
        if (config.getBold() != null && config.getBold()) {
            font.deriveFont(Font.BOLD);
        }

        component.setFont(font);
    }
}
