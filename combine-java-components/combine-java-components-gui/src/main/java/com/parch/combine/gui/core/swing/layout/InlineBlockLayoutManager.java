package com.parch.combine.gui.core.swing.layout;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class InlineBlockLayoutManager implements LayoutManager {
    private List<Component> components = new ArrayList<>();

    @Override
    public void addLayoutComponent(String name, Component comp) {
        components.add(comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        components.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return minimumLayoutSize(parent);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        int width = 0;
        int height = 0;
        for (Component component : components) {
            Dimension prefSize = component.getPreferredSize();
            width += prefSize.width;
            height = Math.max(height, prefSize.height);
        }
        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container parent) {
        int x = 0;
        int y = 0;
        for (Component component : components) {
            Dimension prefSize = component.getPreferredSize();
            component.setBounds(x, y, prefSize.width, prefSize.height);
            x += prefSize.width;
        }
    }
}
