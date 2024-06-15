package com.parch.combine.gui.base.build;

import com.parch.combine.gui.base.core.GUIElementManagerHandler;
import com.parch.combine.gui.base.core.IGUIElement;
import com.parch.combine.gui.base.core.manager.GUIElementManager;
import com.parch.combine.gui.base.core.style.ElementStyleConstant;

import javax.swing.*;
import java.awt.*;

public class GUIFrameBuilder {

    private String id;
    private String title;

    private Boolean close;
    private Boolean visible;

    private String[] topElements;
    private String[] bottomElement;
    private String[] leftElement;
    private String[] rightElement;
    private String[] centerElements;

    public void build() {
        JFrame frame = new JFrame(title);
        frame.setSize(400, 400);

        GUIElementManager manager = GUIElementManagerHandler.getAndRegisterManager(id);
        buildLayoutPanel(manager, frame, topElements, BorderLayout.NORTH);
        buildLayoutPanel(manager, frame, bottomElement, BorderLayout.SOUTH);
        buildLayoutPanel(manager, frame, leftElement, BorderLayout.WEST);
        buildLayoutPanel(manager, frame, rightElement, BorderLayout.EAST);
        buildLayoutPanel(manager, frame, centerElements, BorderLayout.CENTER);

        if (close == null || close) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (visible == null || visible) {
            frame.setVisible(true);
        }
    }

    private static void buildLayoutPanel(GUIElementManager manager, JFrame frame, String[] elements, String layout) {
        if (elements != null) {
            JPanel layoutPanel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);

            for (String elementId : elements) {
                IGUIElement element = manager.get(elementId);
                if (element == null) {
                    continue;
                }

                JComponent elementComponent = element.build();
                if (elementComponent == null) {
                    continue;
                }


                layoutPanel.add(elementComponent);
            }

            frame.add(layoutPanel, layout);
        }
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setTopElements(String[] topElements) {
        this.topElements = topElements;
    }

    public void setBottomElement(String[] bottomElement) {
        this.bottomElement = bottomElement;
    }

    public void setLeftElement(String[] leftElement) {
        this.leftElement = leftElement;
    }

    public void setRightElement(String[] rightElement) {
        this.rightElement = rightElement;
    }

    public void setCenterElements(String[] centerElements) {
        this.centerElements = centerElements;
    }
}
