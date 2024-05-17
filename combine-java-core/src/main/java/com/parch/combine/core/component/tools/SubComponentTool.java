package com.parch.combine.core.component.tools;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.vo.CombineInitVO;
import com.parch.combine.core.component.manager.CombineManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 下级组件帮助类
 */
public class SubComponentTool {

    /**
     * 初始化组件
     *
     * @param components 组件集合
     * @return 异常信息
     */
    @SuppressWarnings("unchecked")
    public static List<String> init(CombineManager combineManager, List<Object> components) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(components)) {
            return errorMsg;
        }

        for (int i = 0; i < components.size(); i++) {
            Object componentObj = components.get(i);
            if (componentObj == null) {
                continue;
            }

            if (componentObj instanceof Map) {
                // 初始化组件
                CombineInitVO initVO = combineManager.getComponent().init(Collections.singletonList((Map<String, Object>) componentObj));
                // 判断是否初始化成功
                if (initVO.isSuccess()) {
                    // 获取组件ID，并将组件配置替换为组件ID
                    String componentId = CheckEmptyUtil.isNotEmpty(initVO.getComponentIds()) ? initVO.getComponentIds().get(0) : initVO.getStaticComponentIds().get(0);
                    components.set(i, componentId);
                } else {
                    // 初始化不成功添加错误信息
                    errorMsg.addAll(initVO.getErrorList());
                }
            } else {
                AbsComponent<?,?> component = combineManager.getComponent().getComponent(componentObj.toString());
                if (component == null) {
                    errorMsg.add("ID【" + componentObj.toString() + "】的组件不存在");
                }
            }
        }

        return errorMsg;
    }

    /**
     * 执行组件
     *
     * @param componentId 组件ID
     *
     * @return 结果
     */
    public static DataResult execute(CombineManager combineManager, Object componentId) {
        if (componentId == null) {
            return null;
        }

        AbsComponent<?,?> component = combineManager.getComponent().getComponent(componentId.toString());
        return combineManager.getComponent().executeComponent(component);
    }

    /**
     * 执行组件
     *
     * @param componentIds 组件ID集合
     *
     * @return 结果
     */
    public static DataResult execute(CombineManager combineManager, List<Object> componentIds) {
        for (Object componentIdObj : componentIds) {
            DataResult result = execute(combineManager, componentIdObj);
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
    public static DataResult execute(CombineManager combineManager, String key, Map<String, Object> data, List<Object> componentIds) {
        return combineManager.execute(key, data, new HashMap<>(0), componentIds.stream().map(Object::toString).collect(Collectors.toList()), null);
    }
}
