package com.parch.combine.gui.base.build.frame;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.gui.core.GUIElementTemplateHelper;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.settings.ElementGridSettings;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

public class GUIFrameBuilder {

    private String domain;
    private String title;
    private String icon;
    private Integer width;
    private Integer height;
    private Integer distanceTop;
    private Integer distanceLeft;
    private Boolean close;
    private Boolean resizable;
    private Boolean visible;

    private String[] topElements;
    private String[] bottomElement;
    private String[] leftElement;
    private String[] rightElement;
    private String[] centerElements;

    protected GUIFrameTemplate sysTemplate;
    protected GUIFrameTemplate template;

    public GUIFrameBuilder(GUIFrameTemplate template) {
        this.template = template == null ? new GUIFrameTemplate(): template;
        this.sysTemplate = GUIElementTemplateHelper.getFrameTemplate(GUIFrameTemplate.class);
    }

    public void build() {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocation(distanceLeft, distanceTop);
        if (CheckEmptyUtil.isNotEmpty(icon)) {
            frame.setIconImage(new ImageIcon(ResourceFileUtil.getURL(icon)).getImage());
        }

        GUIElementManager manager = GUIElementManagerHandler.getAndRegisterManager(domain);
        buildLayoutPanel(manager, frame, topElements, BorderLayout.NORTH, sysTemplate.getTop(), template.getTop());
        buildLayoutPanel(manager, frame,  bottomElement, BorderLayout.SOUTH, sysTemplate.getBottom(), template.getBottom());
        buildLayoutPanel(manager, frame, leftElement, BorderLayout.WEST, sysTemplate.getLeft(), template.getLeft());
        buildLayoutPanel(manager, frame, rightElement, BorderLayout.EAST, sysTemplate.getRight(), template.getRight());
        buildLayoutPanel(manager, frame, centerElements, BorderLayout.CENTER, sysTemplate.getCenter(), template.getCenter());

        if (resizable != null) {
            frame.setResizable(resizable);
        }
        if (close == null || close) {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        if (visible == null || visible) {
            frame.setVisible(true);
        }
    }

    private static void buildLayoutPanel(GUIElementManager manager, JFrame frame, String[] elements, String layout, ElementConfig... configs) {
        if (elements != null) {
            JPanel layoutPanel = new JPanel();
            GUIElementTemplateHelper.loadTemplates(layoutPanel, configs[0]);

            int rowCount = 1;
            for (String s : elements) {
                IGUIElement element = manager.get(s);
                if (element == null) {
                    continue;
                }

                JComponent elementComponent = element.build(frame);
                if (elementComponent == null) {
                    continue;
                }

                ElementHelper.addSubComponent(layoutPanel, elementComponent,
                        element.getTemplate().getExternalGrid(), new ElementGridSettings(1, rowCount++));
            }

            JScrollPane scrollPanel = new JScrollPane(layoutPanel);
            scrollPanel.setOpaque(false);
            frame.add(scrollPanel, layout);
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

    public void setDomain(String domain) {
        this.domain = domain;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setDistanceTop(Integer distanceTop) {
        this.distanceTop = distanceTop;
    }

    public void setDistanceLeft(Integer distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public void setResizable(Boolean resizable) {
        this.resizable = resizable;
    }
}
