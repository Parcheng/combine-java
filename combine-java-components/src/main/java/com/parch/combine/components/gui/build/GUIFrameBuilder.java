package com.parch.combine.components.gui.build;

import com.parch.combine.components.gui.core.GUIElementManagerHandler;
import com.parch.combine.components.gui.core.IGUIElement;
import com.parch.combine.components.gui.core.manager.GUIElementManager;

import javax.swing.*;
import java.awt.*;

public class GUIFrameBuilder {

    private String id;
    private String title;
    private String[] elements;
    private Boolean close;
    private Boolean visible;

    public void build() {
        JFrame frame = new JFrame(title);
        frame.setSize(400, 400);

        if (elements != null) {
            GUIElementManager manager = GUIElementManagerHandler.getAndRegisterManager(id);

            int i = 0;
            for (String elementId : elements) {
                IGUIElement element = manager.get(elementId);
                if (element == null) {
                    continue;
                }

                JComponent elementComponent = element.build();
                if (elementComponent == null) {
                    continue;
                }

                if (i == 0) {
                    frame.add(elementComponent, BorderLayout.NORTH);
                } else {
                    frame.add(elementComponent, BorderLayout.CENTER);
                }

                i++;
            }
        }

        if (close == null || close) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (visible == null || visible) {
            frame.setVisible(true);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setElements(String[] elements) {
        this.elements = elements;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setId(String id) {
        this.id = id;
    }
}
