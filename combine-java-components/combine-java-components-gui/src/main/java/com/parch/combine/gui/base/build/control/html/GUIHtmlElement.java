package com.parch.combine.gui.base.build.control.html;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JEditorPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Map;

public class GUIHtmlElement extends AbstractGUIComponentElement<GUIHtmlElementTemplate, GUIHtmlElement.Config, String> {

    private JEditorPane page = null;

    public GUIHtmlElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIHtmlElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "html", template, config, GUIHtmlElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.page = new JEditorPane();
        setUrl();
        super.registerEvents(this.page, this.config.events);

        panel.add(this.page);

        super.loadTemplates(panel, this.sysTemplate.getPage(), this.template.getPage());
        return panel;
    }

    private void setUrl() {
        try {
            if (this.value.startsWith("http")) {
                this.page.setPage(this.value);
            } else {
                this.page.setPage(ResourceFileUtil.getURL(this.value));
            }
        }catch (Exception e) {
            this.page.setContentType("text/html");
            this.page.setText("<html><body" + config.errorText + "</body></html>");
            PrintUtil.printError("【GUI-构建PAGE】【" + this.value + "】失败：" + e.getMessage());
        }
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = data.toString();
        if (this.page != null) {
            setUrl();
        }

        return true;
    }

    @Override
    public Object getValue() {
        return this.page == null ? this.value : this.page.getPage().getPath();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIHtmlElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public String errorText;
        public EventConfig[] events;
    }
}
