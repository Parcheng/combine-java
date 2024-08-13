package com.parch.combine.gui.base.build.control.file;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Map;

public class GUIFileInputElement extends AbstractGUIComponentElement<GUIFileInputElementTemplate, GUIFileInputElement.Config, String> {

    private JTextField input = null;

    public GUIFileInputElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIFileInputElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "file_input", template, config, GUIFileInputElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.input = new JTextField();
        super.registerEvents(this.input, this.config.events);
        if (this.value != null) {
            this.input.setText(this.value);
        }
        if (this.config.columns != null) {
            this.input.setColumns(this.config.columns);
        }
        super.addSubComponent(panel, this.input, this.template.getInput());

        JButton uploadButton = new JButton(this.config.chooseText);
        super.addSubComponent(panel, uploadButton, this.template.getChoose());
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.setValue(selectedFile.getAbsolutePath());
            }
        });

        return panel;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            data = CheckEmptyUtil.EMPTY;
        }

        if (this.input != null) {
            this.input.setText(data.toString());
        }
        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        return this.input == null ? this.value : this.input.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIFileInputElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public EventConfig[] events;
        public String chooseText;
        public Integer columns;
    }
}
