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

        component.setFont(new Font(config.getName(), Font.PLAIN, config.getSize()));
    }
}
