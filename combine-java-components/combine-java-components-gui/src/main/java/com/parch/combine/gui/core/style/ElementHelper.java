package com.parch.combine.gui.core.style;

import com.parch.combine.gui.core.style.helper.ElementSizeHelper;

import javax.swing.*;

public class ElementHelper {

    public static void set(JComponent component, ElementConfig elementConfig) {
        if (elementConfig == null) {
            return;
        }

        if (elementConfig.getSize() != null) {
            ElementSizeHelper.set(component, elementConfig.getSize());
        }


//        component.setFont();
//        component.setDocument();
    }
}
