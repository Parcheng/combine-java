package com.parch.combine.core.component.tools;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContext;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.vo.FlowResult;

import java.util.*;

/**
 * 下级组件帮助类
 */
public class SubComponentTool {

    /**
     * 执行组件
     *
     * @param componentId 组件ID
     *
     * @return 结果
     */
    public static ComponentDataResult execute(CombineManager combineManager, String componentId) {
        if (componentId == null) {
            return null;
        }

        AbstractComponent<?,?> component = combineManager.getComponent().getComponent(componentId);

        AbstractComponent<?,?> currComponent = ComponentContextHandler.getCurrComponent();
        ComponentDataResult result = combineManager.getComponent().executeComponent(component);
        ComponentContextHandler.setCurrComponent(currComponent);
        return result;
    }

    /**
     * 执行组件
     *
     * @param componentIds 组件ID集合
     *
     * @return 结果
     */
    public static ComponentDataResult execute(CombineManager combineManager, String[] componentIds) {
        for (String componentId : componentIds) {
            ComponentDataResult result = execute(combineManager, componentId);
            if (result != null) {
                if (!result.getSuccess()) {
                    return ComponentDataResult.fail(result.getErrMsg(), result.getShowMsg());
                } else if (result.isStop()) {
                    return ComponentDataResult.build(result);
                }
            }
        }

        return ComponentDataResult.success(componentIds);
    }

    /**
     * 执行组件
     *
     * @param key 流程KEY
     * @param data 数据
     * @param componentIds 组件ID集合
     * @return 结果
     */
    public static FlowResult execute(CombineManager combineManager, String key, Map<String, Object> data, String[] componentIds) {
        return combineManager.execute(key, data, Collections.emptyMap(), Arrays.asList(componentIds), null);
    }
}
