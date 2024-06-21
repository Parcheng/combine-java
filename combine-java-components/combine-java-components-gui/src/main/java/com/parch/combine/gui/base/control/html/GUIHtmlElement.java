package com.parch.combine.gui.base.control.html;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JEditorPane;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GUIHtmlElement extends AbsGUIElement<GUIHtmlElementTemplate, GUIHtmlElement.Config> {

    private JEditorPane page = null;

    public GUIHtmlElement(GUIHtmlElementTemplate template, Config config) {
        super("html", template, config, GUIHtmlElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.page = new JEditorPane();
        setUrl();
        panel.add(this.page);

        super.loadTemplates(panel, this.sysTemplate.getPage(), this.template.getPage());
        return panel;
    }

    private void setUrl() {
        try {
            if (config.path.startsWith("http")) {
                this.page.setPage(config.path);
            } else {
                this.page.setPage(ResourceFileUtil.getURL(config.path));
            }
        }catch (Exception e) {
            this.page.setContentType("text/html");
            this.page.setText("<html><body" + config.errorText + "</body></html>");
            PrintUtil.printError("【GUI-构建PAGE】【" + config.path+ "】失败：" + e.getMessage());
        }
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        this.config.path = data.toString();
        if (this.page != null) {
            setUrl();
        }

        return true;
    }

    @Override
    public Object getData() {
        return this.page == null ? config.path : this.page.getPage().getPath();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIHtmlElement(this.template, this.config);
    }

    public static class Config {
        public String path;
        public String errorText;
    }
}
