package com.parch.combine.gui.core.style.helper;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class ElementLayoutHelper {

    private ElementLayoutHelper(){}

    public static void init(JComponent component) {
        if (component instanceof JPanel) {
            component.setLayout(new GridBagLayout());
        }
    }
}
