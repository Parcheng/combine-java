package com.parch.combine.gui.core.element;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.GUIElementTemplateHelper;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementHelper;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public abstract class AbsGUIElement<T, C> implements IGUIElement {

    protected String type;
    protected T sysTemplate;
    protected T template;
    protected C config;
    protected JFrame frame;

    public AbsGUIElement(String type, T template, C config, Class<T> templateClass) {
        try {
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
}
