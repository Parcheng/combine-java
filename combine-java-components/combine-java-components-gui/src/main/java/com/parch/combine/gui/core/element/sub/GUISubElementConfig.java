package com.parch.combine.gui.core.element.sub;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GUISubElementConfig {
    public String key;
    public String dataField;
    public Object defaultValue;
    public boolean hasNullAssignment;
    public IGUIElement element;
    public EventConfig[] events;
    public JComponent buildResult;

    public void loadConfig(GUIElementManager guiElementManager, SubElementLogicConfig elementConfig) {
        this.dataField = elementConfig.dataField();
        this.defaultValue = elementConfig.defaultValue();
        this.key = elementConfig.key() == null ? this.dataField : elementConfig.key();
        this.events = elementConfig.events();
        this.element = guiElementManager.get(elementConfig.elementId());
        this.hasNullAssignment = elementConfig.hasNullAssignment();
    }

    public static GUISubElementConfig[] convert(GUIElementManager guiElementManager, SubElementLogicConfig[] elementConfigs) {
        return convert(guiElementManager, elementConfigs, null, GUISubElementConfig.class);
    }

    @SuppressWarnings("unchecked")
    public static <T extends GUISubElementConfig, R extends SubElementLogicConfig> T[] convert(GUIElementManager guiElementManager, R[] elementConfigs, BiConsumer<T, R> func, Class<T> configClass) {
        if (elementConfigs == null) {
            return null;
        }

        T[] guiElements = (T[]) Array.newInstance(configClass, elementConfigs.length);
        for (int i = 0; i < elementConfigs.length; i++) {
            R elementConfig = elementConfigs[i];
            try {
                guiElements[i] = configClass.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                PrintUtil.printError("GUISubElementConfig INIT Error: " + configClass.getName() + " New Instance Fail! " + e.getMessage());
                return null;
            }

            guiElements[i].loadConfig(guiElementManager, elementConfig);
            if (func != null) {
                func.accept(guiElements[i], elementConfig);
            }

            if (guiElements[i].element == null) {
                return null;
            }
        }

        return guiElements;
    }

    public static GUISubElementConfig copy(GUISubElementConfig source) {
        GUISubElementConfig newConfig = new GUISubElementConfig();
        newConfig.dataField = source.dataField;
        newConfig.key = source.key;
        newConfig.element = source.element.copy();
        newConfig.events = source.events;
        newConfig.defaultValue = source.defaultValue;
        return newConfig;
    }
}
