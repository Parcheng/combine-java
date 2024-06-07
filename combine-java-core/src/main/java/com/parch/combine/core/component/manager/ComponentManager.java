package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.ComponentFlagEnum;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.handler.ComponentClassHandler;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.vo.CombineInitVO;

import java.util.*;
import java.util.function.Consumer;

/**
 * 流程逻辑处理器
 */
public class ComponentManager {

    private String scopeKey;

    private final Map<String, AbsComponent<?,?>> COMPONENT_MAP = new HashMap<>();

    public ComponentManager(String scopeKey) {
        this.scopeKey = scopeKey;
    }

    /**
     * 初始化函数
     *
     * @param logicConfigs 逻辑配置集合
     * @return 已初始化的组件ID集合
     */
    public CombineInitVO init(List<Map<String, Object>> logicConfigs) {
        List<String> componentIds = new ArrayList<>();
        List<String> staticComponentIds = new ArrayList<>();
        List<String> errorMsgList = new ArrayList<>();
        List<String> registerComponentIds = new ArrayList<>();

        // 根据逻辑配置构建组件集合
        for (Map<String, Object> logicConfig : logicConfigs) {
            registerComponent(logicConfig, componentIds, staticComponentIds, errorMsgList, registerComponentIds);
        }

        // 组件检测是否成功
        boolean success = errorMsgList.size() == 0;
        if (!success) {
            // 未全部检测通过，将已注册的组件清除
            for (String componentId : registerComponentIds) {
                COMPONENT_MAP.remove(componentId);
            }
        }

        // 构建结果VO
        CombineInitVO initVO = new CombineInitVO();
        initVO.setSuccess(success);
        initVO.setComponentIds(componentIds);
        initVO.setStaticComponentIds(staticComponentIds);
        initVO.setErrorList(errorMsgList);
        return initVO;
    }

    /**
     * 保存流程配置集合
     *
     * @param configs 配置集合
     * @return 是否成功
     */
    protected boolean initBlock(List<Map<String, Object>> configs, Consumer<CombineInitVO> func) {
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            CombineInitVO initResult = init(configs);
            initResult.setFlowKey("-");

            // 调用自定义处理函数
            if (func != null) {
                func.accept(initResult);
            }
        }

        return true;
    }

    /**
     * 注册组件
     *
     * @param logicConfig 逻辑配置
     * @param componentIds 流程包含的组件ID集合
     * @param staticComponentIds 流程包含的静态组件ID集合
     * @param errorMsgList 错误信息集合
     * @param registerComponentIds 当前流程已注册的组件ID集合（通过ID引用的不会在这个集合中）
     */
    protected void registerComponent(Map<String, Object> logicConfig, List<String> componentIds,
                                     List<String> staticComponentIds, List<String> errorMsgList, List<String> registerComponentIds) {
        // 获取组件ID（重复ID不重复解析）
        Object componentIdObj = logicConfig.get(FieldKeyCanstant.ID);
        String componentId;
        if (componentIdObj == null) {
            componentId = UUID.randomUUID().toString();
            logicConfig.put(FieldKeyCanstant.ID, componentId);
        } else {
            componentId = componentIdObj.toString();
        }

        // 判断组件是否已经构建过
        AbsComponent<?,?> component = COMPONENT_MAP.get(componentId);
        if (component != null) {
            addComponentId(componentIds, staticComponentIds, component);
            return;
        }

        // 获取组件配置
        Object typeObj = logicConfig.get(FieldKeyCanstant.TYPE);
        if (typeObj == null) {
            return;
        }

        // 构建组件
        String componentType = typeObj.toString();
        ThreeTuples<Boolean, AbsComponent<?,?>, List<String>> buildResult = ComponentClassHandler.build(componentId, componentType, scopeKey, logicConfig);
        if (buildResult.getFirst()) {
            component = buildResult.getSecond();

            // 注册到组件池，并记录
            COMPONENT_MAP.put(component.getId(), component);
            registerComponentIds.add(component.getId());

            // 添加流程组件ID集合
            addComponentId(componentIds, staticComponentIds, component);
        } else {
            errorMsgList.addAll(buildResult.getThird());
        }
    }

    /**
     * 添加组件ID
     *
     * @param componentIds 组件ID集合
     * @param component 组件
     */
    protected void addComponentId(List<String> componentIds, List<String> staticComponentIds, AbsComponent<?,?> component) {
        // 静态逻辑块只构建，不加入到流程中
        String[] flags = component.getLogicConfig().flags();
        if (CheckEmptyUtil.isNotEmpty(flags)) {
            for (String flag : flags) {
                if (ComponentFlagEnum.get(flag) == ComponentFlagEnum.STATIC) {
                    staticComponentIds.add(component.getId());
                    return;
                }
            }
        }

        componentIds.add(component.getId());
    }

    /**
     * 执行组件
     *
     * @param componentIds 组件集合
     * @return 结果
     */
    public DataResult executeComponents(List<String> componentIds) {
        // 按顺序执行组件逻辑
        DataResult dataResult = null;

        for (String componentKey : componentIds) {
            dataResult = executeComponent(getComponent(componentKey));
            if (!dataResult.getSuccess() || dataResult.isStop()) {
                break;
            }
        }

        // 按顺序执行-已执行组件的结束函数
        for (AbsComponent<?,?> component : ComponentContextHandler.getExecutedComponents()) {
            component.end();
        }

        return dataResult;
    }

    /**
     * 执行组件
     *
     * @param component 组件对象
     * @return 结果
     */
    public DataResult executeComponent(AbsComponent<?,?> component) {
        // 运行组件逻辑
        return component.run();
    }

    /**
     * 获取组件
     *
     * @param componentId 组件ID
     * @return 组件
     */
    public AbsComponent<?,?> getComponent(String componentId) {
        return COMPONENT_MAP.get(componentId);
    }

    /**
     * 自定义函数
     */
    public interface Function {
        /**
         * 前置函数
         */
        void before();

        /**
         * 后置函数
         */
        void after();
    }
}
