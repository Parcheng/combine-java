package com.parch.combine.components.gui;

import javax.swing.*;
import java.awt.*;

public class GUIFromControlComponent {

    public void buildItem(JFrame f) {
        // 单选
        JRadioButton male = new JRadioButton("男", false);
        JRadioButton female = new JRadioButton("女", true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        JPanel selectPanel = new JPanel();
        selectPanel.add(male);
        selectPanel.add(female);

        JCheckBox box1 = new JCheckBox("A", true);
        JCheckBox box2 = new JCheckBox("B", false);
        JPanel boxPanel = new JPanel();
        boxPanel.add(box1);
        boxPanel.add(box2);


        JLabel label = new JLabel();
        label.setText("标题");
        label.setPreferredSize(new Dimension(30, 30));

        // 输入框
        JTextField input = new JTextField("文本框",20);
        input.setPreferredSize(new Dimension(100, 50));
        // JPasswordField
        // JTextArea

        JButton button = new JButton();
        button.setText("按钮111");
        button.setPreferredSize(new Dimension(100, 50));

        //列表
        JList<String> colorList = new JList<>(new String[]{"红色","绿色","蓝色"});

        // 下拉框
        JComboBox<String> colorSelect = new JComboBox<>();
        colorSelect.addItem("红色");
        colorSelect.addItem("绿色");
        colorSelect.addItem("蓝色");

        JPanel panel = new JPanel();
        // Box topLeft = Box.createHorizontalBox(); // Box.createVerticalBox();
        panel.setSize(100, 30);
        panel.add(colorList);
        panel.add(selectPanel);
        panel.add(colorSelect);
        panel.add(label);
        panel.add(input);
        panel.add(button);
        panel.add(boxPanel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; // 使组件跨越此行的所有列
        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平填充
        gbc.weightx = 1.0; // 水平权重设置为1.0，使其宽度尽可能填满

        panel2.add(panel, gbc);
        panel2.add(button, gbc);
        panel2.add(label, gbc);
        panel2.add(colorSelect, gbc);

        // JDialog	对话框

        // 颜色 边框 公共类
        // 是否支持后台运行
        f.add(panel2);
    }
}
