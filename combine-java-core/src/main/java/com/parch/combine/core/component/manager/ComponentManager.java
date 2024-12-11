package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.canstant.FieldKeyConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.ComponentFlagEnum;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.handler.ComponentClassHandler;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.vo.CombineInitVO;

import java.util.*;
import java.util.function.Consumer;

/**
 * 流程逻辑处理器
 */
public class ComponentManager {

    private String scopeKey;

    private final Map<String, AbstractComponent<?,?>> COMPONENT_MAP = new HashMap<>();

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
        List<String> waitRegisterComponentIds = new ArrayList<>();

        // 根据逻辑配置构建组件集合
        for (Map<String, Object> logicConfig : logicConfigs) {
            this.registerComponent(logicConfig, componentIds, staticComponentIds, waitRegisterComponentIds, errorMsgList, registerComponentIds);
        }

        // 检查所有待注册注解是否已经全部注册
        for (String componentId : waitRegisterComponentIds) {
            if (!registerComponentIds.contains(componentId)) {
                errorMsgList.add("【" + componentId + "】的组件未定义");
            }
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
     * @param waitRegisterComponentIds 流程包含的待注册ID集合
     * @param errorMsgList 错误信息集合
     * @param registerComponentIds 当前流程已注册的组件ID集合（通过ID引用的不会在这个集合中）
     */
    protected void registerComponent(Map<String, Object> logicConfig, List<String> componentIds, List<String> staticComponentIds,
                                     List<String> waitRegisterComponentIds, List<String> errorMsgList, List<String> registerComponentIds) {
        // 初始化组件ID
        Object componentIdObj = logicConfig.get(FieldKeyConstant.ID);
        String componentId;
        if (componentIdObj == null) {
            componentId = UUID.randomUUID().toString();
            logicConfig.put(FieldKeyConstant.ID, componentId);
        } else {
            componentId = componentIdObj.toString();
        }

        // 判断组件是否已经构建过
        AbstractComponent<?,?> component = COMPONENT_MAP.get(componentId);
        if (component != null) {
            this.addComponentId(componentIds, staticComponentIds, component);
            return;
        }

        Object typeObj = logicConfig.get(FieldKeyConstant.TYPE);
        if (typeObj == null) {
            errorMsgList.add("【" + JsonUtil.obj2String(logicConfig) + "】组件配置错误, 组件类型未指定");
            return;
        }

        // 判断是否是引用组件
        Boolean ref = DataParseUtil.parseBoolean(logicConfig.get(FieldKeyConstant.REF_COMPONENT));
        if (componentIdObj != null && ref != null && ref) {
            waitRegisterComponentIds.add(componentId);
            return;
        }

        // 构建组件
        String componentType = typeObj.toString();
        ThreeTuples<Boolean, AbstractComponent<?,?>, List<String>> buildResult = ComponentClassHandler.build(componentId, componentType, scopeKey, logicConfig);
        if (buildResult.getFirst()) {
            component = buildResult.getSecond();

            // 注册到组件池，并记录
            COMPONENT_MAP.put(component.getId(), component);
            registerComponentIds.add(component.getId());

            // 添加流程组件ID集合
            this.addComponentId(componentIds, staticComponentIds, component);
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
    protected void addComponentId(List<String> componentIds, List<String> staticComponentIds, AbstractComponent<?,?> component) {
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
    public ComponentDataResult executeComponents(List<String> componentIds) {
        // 按顺序执行组件逻辑
        ComponentDataResult dataResult = null;

        for (String componentKey : componentIds) {
            dataResult = executeComponent(getComponent(componentKey));
            if (!dataResult.getSuccess() || dataResult.isStop()) {
                break;
            }
        }

        // 按顺序执行-已执行组件的结束函数
        for (AbstractComponent<?,?> component : ComponentContextHandler.getExecutedComponents()) {
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
    public ComponentDataResult executeComponent(AbstractComponent<?,?> component) {
        // 运行组件逻辑
        return component.run();
    }

    /**
     * 获取组件
     *
     * @param componentId 组件ID
     * @return 组件
     */
    public AbstractComponent<?,?> getComponent(String componentId) {
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
