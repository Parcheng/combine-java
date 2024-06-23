package com.parch.combine.gui.core.event;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.event.trigger.GUITriggerBuilder;
import com.parch.combine.gui.core.event.trigger.ITriggerProcessor;

import javax.swing.JComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIEventHandler {

    public static void binding(JComponent guiComponent, EventConfig config, AbsGUIElement<?, ?> element) {
        if (element == null) {
            PrintUtil.printError("【GUI EVENT BINDING】ERROR: 绑定事件的 GUI 元素不存在");
            return;
        }

        GUIEventTypeEnum event = GUIEventTypeEnum.get(config.getEventType());
        if (event == null || event == GUIEventTypeEnum.NONE) {
            PrintUtil.printError("【GUI EVENT BINDING】ERROR: " + config.getEventType() + " 事件类型不合法");
            return;
        }

        ITriggerProcessor triggerProcessor = GUITriggerBuilder.build(config, element);
        if (triggerProcessor == null) {
            return;
        }

//        ActionEvent：当用户执行某个动作时触发，比如点击按钮或菜单项。
//        MouseEvent：当用户与鼠标交互时触发，比如点击、拖动、释放等。
//        KeyEvent：当用户与键盘交互时触发，比如按下或释放键盘按键。
//        WindowEvent：当窗口发生变化时触发，比如打开、关闭、最小化、最大化等。
//        FocusEvent：当组件获得或失去焦点时触发。
//        ComponentEvent：当组件的大小、位置或可见性发生变化时触发。
//        ItemEvent：当用户与可选择项目（如复选框、单选按钮、下拉框等）交互时触发。
        switch (event) {
            case CLICK:
                guiComponent.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        if (event.getClickCount() == 1) {
                            triggerProcessor.trigger(event);
                        }
                    }
                });
                break;
            case DBLCLICK:
                guiComponent.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            triggerProcessor.trigger(event);
                        }
                    }
                });
                break;
        }
    }
}