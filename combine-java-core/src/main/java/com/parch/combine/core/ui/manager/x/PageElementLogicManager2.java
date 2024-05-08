//package com.parch.combine.core.ui.manager;
//
//import com.parch.combine.core.common.util.CheckEmptyUtil;
//import com.parch.combine.core.component.base.AbsComponent;
//import com.parch.combine.core.component.base.ComponentFlagEnum;
//import com.parch.combine.core.component.base.LogicConfig;
//import com.parch.combine.core.component.context.ComponentContextHandler;
//import com.parch.combine.core.component.context.GlobalContextHandler;
//import com.parch.combine.core.component.handler.ComponentClassHandler;
//import com.parch.combine.core.component.tools.PrintHelper;
//import com.parch.combine.core.component.vo.DataResult;
//import com.parch.combine.core.component.vo.FlowInitVO;
//import com.parch.combine.core.ui.base.element.ElementLogicConfig;
//
//import java.util.*;
//
///**
// * 流程逻辑处理器
// */
//public class PageElementLogicManager2 {
//
//    private static final Map<String, ElementLogicConfig> ELEMENT_LOGIC_CONFIGS = new HashMap<>();
//
//    /**
//     * 初始化函数
//     *
//     * @param logicConfigs 逻辑配置集合
//     * @return 已初始化的组件ID集合
//     */
//    public FlowInitVO load(List<Map<String, Object>> logicConfigs) {
//        List<String> logicIds = new ArrayList<>();
//        List<String> errorMsgList = new ArrayList<>();
//        List<String> registerLogicIds = new ArrayList<>();
//
//        // 根据逻辑配置构建组件集合
//        for (Map<String, Object> logicConfig : logicConfigs) {
//            register(logicConfig, logicIds, errorMsgList, registerLogicIds);
//        }
//
//        // 组件检测是否成功
//        boolean success = errorMsgList.size() == 0;
//        if (!success) {
//            // 未全部检测通过，将已注册的组件清除
//            for (String componentId : registerComponentIds) {
//                ELEMENT_LOGIC_CONFIGS.remove(componentId);
//            }
//        }
//
//        // 构建结果VO
//        FlowInitVO initVO = new FlowInitVO();
//        initVO.setSuccess(success);
//        initVO.setComponentIds(componentIds);
//        initVO.setStaticComponentIds(staticComponentIds);
//        initVO.setErrorList(errorMsgList);
//        return initVO;
//    }
//
//    /**
//     * 注册组件
//     *
//     * @param logicConfig 逻辑配置
//     * @param logicIds 流程包含的组件ID集合
//     * @param errorMsgList 错误信息集合
//     * @param registerLogicIds 当前流程已注册的组件ID集合（通过ID引用的不会在这个集合中）
//     */
//    protected void register(Map<String, Object> logicConfig, List<String> logicIds,
//                                     List<String> errorMsgList, List<String> registerLogicIds) {
//        // 获取组件ID（重复ID不重复解析）
//        Object componentIdObj = logicConfig.get(ID_FIELD);
//        String componentId;
//        if (componentIdObj == null) {
//            componentId = UUID.randomUUID().toString();
//            logicConfig.put(ID_FIELD, componentId);
//        } else {
//            componentId = componentIdObj.toString();
//        }
//
//        // 判断组件是否已经构建过
//        AbsComponent<?,?> component = COMPONENT_MAP.get(componentId);
//        if (component != null) {
//            addComponentId(componentIds, staticComponentIds, component);
//            return;
//        }
//
//        // 获取组件配置
//        Object typeObj = logicConfig.get(COMPONENT_TYPE_FIELD);
//        if (typeObj == null) {
//            return;
//        }
//
//        // 构建组件
//        String componentType = typeObj.toString();
//        component = ComponentClassHandler.build(componentId, componentType, scopeKey, logicConfig);
//        if (component == null) {
//            errorMsgList.add("组件【" + componentId + "】构建失败");
//            return;
//        }
//
//        // 检查组件配置
//        List<String> checkErrors = ComponentClassHandler.init(component);
//        if (checkErrors != null) {
//            errorMsgList.addAll(checkErrors);
//        }
//
//        // 注册到组件池，并记录
//        COMPONENT_MAP.put(component.getId(), component);
//        registerComponentIds.add(component.getId());
//
//        // 添加流程组件ID集合
//        addComponentId(componentIds, staticComponentIds, component);
//    }
//
//    /**
//     * 添加组件ID
//     *
//     * @param componentIds 组件ID集合
//     * @param component 组件
//     */
//    protected void addComponentId(List<String> componentIds, List<String> staticComponentIds, AbsComponent<?,?> component) {
//        // 静态逻辑块只构建，不加入到流程中
//        if (component.getLogicConfig().getFlags().contains(ComponentFlagEnum.STATIC)) {
//            staticComponentIds.add(component.getId());
//            return;
//        }
//
//        componentIds.add(component.getId());
//    }
//
//    /**
//     * 执行组件
//     *
//     * @param componentIds 组件集合
//     * @return 结果
//     */
//    public DataResult executeComponents(List<String> componentIds) {
//        // 按顺序执行组件逻辑
//        DataResult dataResult = null;
//
//        for (String componentKey : componentIds) {
//            dataResult = executeComponent(getComponent(componentKey));
//            if (!dataResult.getSuccess() || dataResult.isStop()) {
//                break;
//            }
//        }
//
//        // 按顺序执行-已执行组件的结束函数
//        for (AbsComponent<?,?> component : ComponentContextHandler.getExecutedComponents()) {
//            component.end();
//        }
//
//        return dataResult;
//    }
//
//    /**
//     * 执行组件
//     *
//     * @param component 组件对象
//     * @return 结果
//     */
//    public DataResult executeComponent(AbsComponent<?,?> component) {
//        // 运行组件逻辑
//        DataResult result = component.run();
//        LogicConfig logicConfig = component.getLogicConfig();
//
//        // 如果配置中定义了报错提示语，则使用配置的报错提示语
//        if (CheckEmptyUtil.isNotEmpty(logicConfig.getShowMsg())) {
//            result.setShowMsg(logicConfig.getShowMsg());
//        }
//
//        // 打印日志
//        boolean isPrint = logicConfig.getPrintResult() == null ? GlobalContextHandler.get().getPrintComponentResult() : logicConfig.getPrintResult();
//        if (isPrint) {
//            PrintHelper.printComponentResult(component, result);
//        }
//
//        return result;
//    }
//
//    /**
//     * 获取组件
//     *
//     * @param componentId 组件ID
//     * @return 组件
//     */
//    public AbsComponent<?,?> getComponent(String componentId) {
//        return COMPONENT_MAP.get(componentId);
//    }
//
//    /**
//     * 自定义函数
//     */
//    public interface Function {
//        /**
//         * 前置函数
//         */
//        void before();
//
//        /**
//         * 后置函数
//         */
//        void after();
//    }
//}
