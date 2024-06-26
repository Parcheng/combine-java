package com.parch.combine.core.component.tools;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.manager.CombineManager;

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
    public static DataResult execute(CombineManager combineManager, String componentId) {
        if (componentId == null) {
            return null;
        }

        AbstractComponent<?,?> component = combineManager.getComponent().getComponent(componentId);
        return combineManager.getComponent().executeComponent(component);
    }

    /**
     * 执行组件
     *
     * @param componentIds 组件ID集合
     *
     * @return 结果
     */
    public static DataResult execute(CombineManager combineManager, String[] componentIds) {
        for (String componentId : componentIds) {
            DataResult result = execute(combineManager, componentId);
            if (result != null) {
                if (!result.getSuccess()) {
                    return DataResult.fail(result.getErrMsg(), result.getShowMsg());
                } else if (result.isStop()) {
                    return DataResult.build(result);
                }
            }
        }

        return DataResult.success(componentIds);
    }

    /**
     * 执行组件
     *
     * @param key 流程KEY
     * @param data 数据
     * @param componentIds 组件ID集合
     * @return 结果
     */
    public static DataResult execute(CombineManager combineManager, String key, Map<String, Object> data, String[] componentIds) {
        return combineManager.execute(key, data, new HashMap<>(0), Arrays.asList(componentIds), null);
    }
}
