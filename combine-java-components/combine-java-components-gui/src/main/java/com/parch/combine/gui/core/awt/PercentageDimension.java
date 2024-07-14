package com.parch.combine.gui.core.awt;

import java.awt.Dimension;

public class PercentageDimension extends Dimension {

    public int percentageWidth;

    public int percentageHeight;

    public int getPercentageWidth() {
        return percentageWidth;
    }

    public void setPercentageWidth(int percentageWidth) {
        this.percentageWidth = percentageWidth;
    }

    public int getPercentageHeight() {
        return percentageHeight;
    }

    public void setPercentageHeight(int percentageHeight) {
        this.percentageHeight = percentageHeight;
    }
}
