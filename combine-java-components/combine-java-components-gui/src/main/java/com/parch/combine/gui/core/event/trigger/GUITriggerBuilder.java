package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.core.component.context.CombineManagerHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

public class GUITriggerBuilder {

    public static ITriggerProcessor build(EventConfig config, IGUIElement element) {
        String triggerType = config.triggerType();
        GUITriggerTypeEnum trigger = GUITriggerTypeEnum.get(triggerType);
        switch (trigger) {
            case COMPONENT:
                CombineManager manager = CombineManagerHandler.get(element.getScopeKey());
                if (manager == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 获取组件管理器失败");
                    return null;
                }

                ComponentTriggerProcessor.Config componentConfig = config.componentTrigger();
                if (componentConfig == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 配置未定义");
                    return null;
                }

                String[] componentIds = componentConfig.components();
                if (CheckEmptyUtil.isEmpty(componentIds)) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 未定义要执行的组件");
                    return null;
                }
                for (String componentId : componentIds) {
                    if (manager.getComponent().getComponent(componentId) == null) {
                        PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 的 " + componentId + " 组件不存在");
                        return null;
                    }
                }

                return new ComponentTriggerProcessor(manager, element.getFrame(), componentConfig);
            case DIALOG_BOX:
                GUIElementManager guiElementManager = GUIElementManagerHandler.getManager(element.getDomain());
                if (guiElementManager == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 获取GUI元素管理器失败");
                    return null;
                }

                DialogBoxTriggerProcessor.Config dialogBoxConfig = config.dialogBoxTrigger();
                if (dialogBoxConfig == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 配置未定义");
                    return null;
                }

                return new DialogBoxTriggerProcessor(element.getFrame(), dialogBoxConfig);
            case INTERNAL:
                InternalTriggerProcessor.Config internalTrigger = config.internalTrigger();
                if (internalTrigger == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 配置未定义");
                    return null;
                }
                if (internalTrigger.getFunc() == null) {
                    PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 的函数未定义");
                    return null;
                }

                return new InternalTriggerProcessor(element.getFrame(), internalTrigger);
            case NONE:
            default:
                PrintLogUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 触发类型不合法");
                return null;
        }
    }
}