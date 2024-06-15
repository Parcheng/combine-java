package com.parch.combine.starter.test;

import com.parch.combine.core.component.CombineJavaStarter;
import com.parch.combine.core.component.service.ICombineJavaService;
import com.parch.combine.core.component.vo.DataResult;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
//        顶层容器：JFrame、JApplet、JDialog、JWindow共4个
//        中间容器：JPanel、JScrollPane、JSplitPane、JToolBar
//        3.特殊容器：在用户界面上 具有特殊作用的中间容器，如JIntemalFrame,JRootPane,JLayeredPane和JDestopPane等。
//        4.基本组件：实现人机交互的组件，如JButton,JComboBox,JList,JMenu,JSlider等。
//        5.不可编辑信息的显示组件：向用户显示能够编辑的格式化信息的组件，如JTable,JTextArea和JTextField等。
//        6.特殊对话框组件：可以直接产生特殊对话框的组件，如JColorChooser和JFileChooser等。


//        3	JColorChooser
//        JColorChooser 提供一个控制面板，设计允许用户操作和选择颜色。
//        11	ImageIcon
//        ImageIcon 控件是一个图标界面的实现，它从图像描绘图标
//        12	JScrollbar
//        Scrollbar 控件代表一个滚动条组件，为了让用户从值的范围中选择。
//        13	JOptionPane
//        JOptionPane 提供了一组提示用户输入值的标准对话框，或者通知他们其他东西。
//        14	JFileChooser
//        JFileChooser 控件代表一个对话框窗口，用户可以从该对话框窗口选择一个文件。
//        15	JProgressBar
//        随着任务完成的进展，进度条显示任务完成的百分比。
//        16	JSlider
//        JSlider 让用户在有界区间内通过滑动旋钮图形化地选择一个值。
//        17	JSpinner
//        JSpinner 是一个单行输入字段，它让用户从一个有序序列中选择一个数字或者一个对象值。

//        JWindow p = new JWindow();
//        p.setSize(400, 400);
//        p.setVisible(true);

//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.REMAINDER; // 使组件跨越此行的所有列
//        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平填充
//        gbc.weightx = 1.0; // 水平权重设置为1.0，使其宽度尽可能填满
//
//        panel2.add(panel, gbc);
//        panel2.add(button, gbc);
//        panel2.add(label, gbc);
//        panel2.add(colorSelect, gbc);

        ICombineJavaService service = CombineJavaStarter.init("test_combine_config.json");
        DataResult result = service.execute("gui", "test", new HashMap<>(), new HashMap<>());
        System.out.println(result.getData());
    }
}
