package com.parch.combine.gui.base.control.menu;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.*;

public class GUIMenuElement extends AbsGUIElement<GUIMenuElementTemplate, GUIMenuElement.Config> {

    private JSlider slider = null;

    public GUIMenuElement(GUIMenuElementTemplate template, Config config) {
        super("button", template, config, GUIMenuElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        this.slider.setMajorTickSpacing(20);
        this.slider.setMinorTickSpacing(5);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);

//        JMenuBar menuBar = new JMenuBar();
//        JMenu fileMenu = new JMenu("File");
//        JMenuItem newItem = new JMenuItem("New");
//        JMenuItem openItem = new JMenuItem("Open");
//        JMenuItem saveItem = new JMenuItem("Save");
//
//        fileMenu.add(newItem);
//        fileMenu.add(openItem);
//        fileMenu.add(saveItem);
//        menuBar.add(fileMenu);

        panel.add(this.slider);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

//        if (this.button != null) {
//            this.button.setText(data.toString());
//        }
        this.config.text = data.toString();
        return true;
    }

    @Override
    public Object getData() {
        return null;
        // this.button == null ? config.text : this.button.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIMenuElement(this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
