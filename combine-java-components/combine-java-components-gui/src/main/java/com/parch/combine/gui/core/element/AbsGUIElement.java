package com.parch.combine.gui.core.element;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.GUIElementTemplateHelper;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.event.GUIEventHandler;
import com.parch.combine.gui.core.style.ElementConfig;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class AbsGUIElement<T, C> implements IGUIElement {

    protected String scopeKey;
    protected String id;
    protected Map<String, Object> data;

    protected String type;
    protected T sysTemplate;
    protected T template;
    protected C config;
    protected JFrame frame;

    public AbsGUIElement(String scopeKey, String id, Map<String, Object> data, String type, T template, C config, Class<T> templateClass) {
        try {
            this.id = id;
            this.scopeKey = scopeKey;
            this.data = data;
            this.type = type;
            this.sysTemplate = GUIElementTemplateHelper.getControlTemplate(type, templateClass);
            this.template = template;
            this.config = config;

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

    public JComponent build(JFrame frame){
        this.frame = frame;
        return build();
    }

    protected abstract JComponent build();

    protected void loadTemplates(JComponent component, ElementConfig ... configs) {
        GUIElementTemplateHelper.loadTemplates(component, configs);
    }

    protected void registerEvents(JComponent component, EventConfig[] events) {
        if (events == null || events.length == 0) {
            return;
        }

        for (EventConfig event : events) {
            GUIEventHandler.binding(component, event, this);
        }
    }

    public String getScopeKey() {
        return scopeKey;
    }

    public String getId() {
        return id;
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public Map<String, Object> getData() {
        return data;
    }
}
