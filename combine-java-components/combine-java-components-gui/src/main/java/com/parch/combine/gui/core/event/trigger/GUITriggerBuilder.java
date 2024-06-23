package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.component.handler.CombineManagerHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import java.util.HashMap;
import java.util.UUID;

public class GUITriggerBuilder {

    public static ITriggerProcessor build(EventConfig config, AbsGUIElement<?, ?> element) {
        String triggerType = config.getTriggerType();
        GUITriggerTypeEnum trigger = GUITriggerTypeEnum.get(triggerType);
        switch (trigger) {
            case COMPONENT:
                CombineManager manager = CombineManagerHandler.get(element.getScopeKey());
                if (manager == null) {
                    PrintUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 获取组件管理器失败");
                    return null;
                }

                ComponentTriggerProcessor.Config componentConfig = config.getComponentTrigger();
                if (componentConfig == null) {
                    PrintUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 配置未定义");
                    return null;
                }
                if (CheckEmptyUtil.isEmpty(componentConfig.getElementIds())) {
                    PrintUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 未定义要执行的组件");
                    return null;
                }
                if (CheckEmptyUtil.isEmpty(componentConfig.getKey())) {
                    componentConfig.setKey(UUID.randomUUID().toString());
                }
                if (componentConfig.getParams() == null) {
                    componentConfig.setParams(new HashMap<>(0));
                }

                return new ComponentTriggerProcessor(manager, element.getFrame(), componentConfig);
            case NONE:
            default:
                PrintUtil.printError("【GUI EVENT BINDING】Trigger ERROR " + triggerType + " 触发类型不合法");
                return null;
        }
    }
}