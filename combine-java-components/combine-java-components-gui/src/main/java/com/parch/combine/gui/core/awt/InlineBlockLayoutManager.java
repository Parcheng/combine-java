package com.parch.combine.gui.core.awt;

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
            Dimension referredSize = component.getPreferredSize();
            width += referredSize.width;
            height = Math.max(height, referredSize.height);
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

//    public void layoutContainer(Container target) {
//        synchronized (target.getTreeLock()) {
//            Insets insets = target.getInsets();
//            int maxwidth = target.width - (insets.left + insets.right + hgap*2);
//            int nmembers = target.getComponentCount();
//            int x = 0, y = insets.top + vgap;
//            int rowh = 0, start = 0;
//
//            boolean ltr = target.getComponentOrientation().isLeftToRight();
//
//            boolean useBaseline = getAlignOnBaseline();
//            int[] ascent = null;
//            int[] descent = null;
//
//            if (useBaseline) {
//                ascent = new int[nmembers];
//                descent = new int[nmembers];
//            }
//
//            for (int i = 0 ; i < nmembers ; i++) {
//                Component m = target.getComponent(i);
//                if (m.isVisible()) {
//                    Dimension d = m.getPreferredSize();
//                    m.setSize(d.width, d.height);
//
//                    if (useBaseline) {
//                        int baseline = m.getBaseline(d.width, d.height);
//                        if (baseline >= 0) {
//                            ascent[i] = baseline;
//                            descent[i] = d.height - baseline;
//                        }
//                        else {
//                            ascent[i] = -1;
//                        }
//                    }
//                    if ((x == 0) || ((x + d.width) <= maxwidth)) {
//                        if (x > 0) {
//                            x += hgap;
//                        }
//                        x += d.width;
//                        rowh = Math.max(rowh, d.height);
//                    } else {
//                        rowh = moveComponents(target, insets.left + hgap, y,
//                                maxwidth - x, rowh, start, i, ltr,
//                                useBaseline, ascent, descent);
//                        x = d.width;
//                        y += vgap + rowh;
//                        rowh = d.height;
//                        start = i;
//                    }
//                }
//            }
//            moveComponents(target, insets.left + hgap, y, maxwidth - x, rowh,
//                    start, nmembers, ltr, useBaseline, ascent, descent);
//        }
//    }
}
