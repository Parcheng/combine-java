package com.parch.combine.gui.core.style.settings;

public class ElementGridSettings {

    private Integer positionX;

    private Integer positionY;

    public ElementGridSettings(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
