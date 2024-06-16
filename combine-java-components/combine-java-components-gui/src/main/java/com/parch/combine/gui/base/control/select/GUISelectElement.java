package com.parch.combine.gui.base.control.select;

import com.parch.combine.gui.base.control.GUIControlOptionConfig;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUISelectElement extends AbsGUIElement<GUISelectElementTemplate, GUISelectElement.Config> {

    private JComboBox<String> comboBox = null;

    public GUISelectElement(GUISelectElementTemplate template, Config config) {
        super("select", template, config, GUISelectElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.comboBox = new JComboBox<>();
        super.loadTemplates(this.comboBox, this.sysTemplate.getSelect(), this.template.getSelect());

        int checkIndex = -1;
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];
            this.comboBox.addItem(option.getText() == null ? option.getValue() : option.getText());
            if (checkIndex == -1 && this.config.value != null && this.config.value.equals(option.getValue())) {
                checkIndex = i;
            }
        }

        if (checkIndex != -1) {
            this.comboBox.setSelectedIndex(checkIndex);
        }

        panel.add(this.comboBox);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        String dataStr = data.toString();
        for (int i = 0; i < this.config.options.length; i++) {
            GUIControlOptionConfig option = this.config.options[i];
            if (dataStr != null && dataStr.equals(option.getValue())) {
                if (this.comboBox != null) {
                    this.comboBox.setSelectedIndex(i);
                }
                this.config.value = option.getValue();
                return true;
            }
        }

        return false;
    }

    @Override
    public Object getData() {
        if (this.comboBox == null) {
            return this.config.value;
        }

        int index =this.comboBox.getSelectedIndex();
        return index > 0 ? this.config.options[index].getValue() : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUISelectElement(this.template, this.config);
    }

    public static class Config {
        public String value;
        public GUIControlOptionConfig[] options;
    }
}
