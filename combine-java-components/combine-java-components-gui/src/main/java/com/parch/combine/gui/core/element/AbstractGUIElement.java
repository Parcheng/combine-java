package com.parch.combine.gui.core.element;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.GUIElementTemplateHelper;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.event.GUIEventHandler;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementHelper;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class AbstractGUIElement<T extends BaseGUIElementTemplate, C extends GUIElementConfig<V>, V> implements IGUIElement {

    protected String scopeKey;
    protected String domain;
    protected String id;
    protected Map<String, Object> data;

    protected String type;
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
            this.loadTemplate(template, templateClass);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            PrintUtil.printError("【GUIElement】【" + type + "】模板加载失败！");
        }
    }

    private void loadTemplate(T template, Class<T> templateClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (template == null) {
            template = templateClass.getDeclaredConstructor().newInstance();
        }

        T sysTemplate =  GUIElementTemplateHelper.getControlTemplate(type, templateClass);
        if (sysTemplate != null) {
            template.merge(sysTemplate);
        }

        this.template = template;
    }

    protected void loadTemplates(JComponent component, ElementConfig config) {
        GUIElementTemplateHelper.loadTemplates(component, config);
    }

    protected void loadFancyTemplates(JComponent component, String key, Map<String, ElementConfig> config) {
        if (key == null || config == null) {
            return;
        }
        GUIElementTemplateHelper.loadTemplates(component, config.get(key));
    }

    protected void addSubComponent(JComponent component, JComponent subComponent, ElementConfig config) {
        if (component == null || subComponent == null) {
            return;
        }

        loadTemplates(subComponent, config);
        ElementHelper.addSubComponent(component, subComponent, config == null ? null : config.getGrid(), null);
    }


    protected void registerEvents(JComponent component, EventConfig[] events) {
        GUIEventHandler.bindings(component, events, this);
    }

    @Override
    public final Object call(String key, Object... params) {
        if (this.callFunctionMap == null || key == null) {
            return null;
        }

        IGUIElementCallFunction function = this.callFunctionMap.get(key);
        if (function == null) {
            return null;
        }

        return function.execute(params);
    }

    public abstract Map<String, IGUIElementCallFunction> initCallFunction();

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Container getContainer() {
        return this.container;
    }

    @Override
    public String getScopeKey() {
        return this.scopeKey;
    }

    @Override
    public JFrame getFrame() {
        return this.frame;
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

    @Override
    public Map<String, Object> getData() {
        return this.data;
    }

    public T getTemplate() {
        return this.template;
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
        return this.visible != null && this.visible;
    }
}
