package com.parch.combine.gui.core.element;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.GUIElementTemplateHelper;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.event.GUIEventHandler;
import com.parch.combine.gui.core.style.ElementConfig;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class AbstractGUIElement<T, C extends GUIElementConfig<V>, V> implements IGUIElement {

    protected String scopeKey;
    protected String domain;
    protected String id;
    protected Map<String, Object> data;

    protected String type;
    protected T sysTemplate;
    protected T template;
    protected C config;
    protected V value;

    protected JFrame frame;
    protected Boolean visible;
    protected Container container;

    protected Map<String, IGUIElementCallFunction> callFunctionMap;

    protected AbstractGUIElement(String scopeKey, String domain, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        try {
            this.id = id;
            this.scopeKey = scopeKey;
            this.domain = domain;
            this.data = data;
            this.type = type;
            this.config = config;
            this.value = config.value;
            this.visible = config.visible;
            this.callFunctionMap = this.initCallFunction();

            this.sysTemplate = GUIElementTemplateHelper.getControlTemplate(type, templateClass);
            this.template = template;

            if (this.sysTemplate == null) {
                this.sysTemplate = templateClass.getDeclaredConstructor().newInstance();
            }
            if (this.template == null) {
                this.template = templateClass.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            PrintUtil.printError("【GUIElement】【" + type + "】模板加载失败！");
        }
    }

    protected void loadTemplates(JComponent component, ElementConfig ... configs) {
        GUIElementTemplateHelper.loadTemplates(component, configs);
    }

    protected void registerEvents(JComponent component, EventConfig[] events) {
        GUIEventHandler.bindings(component, events, this);
    }

    @Override
    public final Object call(String key, Object... params) {
        if (callFunctionMap == null || key == null) {
            return null;
        }

        IGUIElementCallFunction function = callFunctionMap.get(key);
        if (function == null) {
            return null;
        }

        return function.execute(params);
    }

    public abstract Map<String, IGUIElementCallFunction> initCallFunction();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public String getScopeKey() {
        return scopeKey;
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public void setVisible(Boolean visible) {
        this.visible = visible != null && visible;
        if (this.container != null) {
            this.container.setVisible(this.visible);
        }
    }

    @Override
    public boolean isVisible() {
        return visible != null && visible;
    }
}
