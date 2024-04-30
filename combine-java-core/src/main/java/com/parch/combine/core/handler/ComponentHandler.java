package com.parch.combine.core.handler;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.ComponentFlagEnum;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.tools.PrintHelper;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.vo.FlowInitVO;

import java.util.*;

/**
 * 流程逻辑处理器
 */
public class ComponentHandler {

    /**
     * 组件池
     */
    private final static Map<String, AbsComponent<?,?>> COMPONENT_MAP = new HashMap<>();

    public final static String ID_FIELD = "id";
    public final static String COMPONENT_TYPE_FIELD = "type";

    /**
     * 初始化函数
     *
     * @param logicConfigs 逻辑配置集合
     * @return 已初始化的组件ID集合
     */
    public static FlowInitVO init(List<Map<String, Object>> logicConfigs) {
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
        FlowInitVO initVO = new FlowInitVO();
        initVO.setSuccess(success);
        initVO.setComponentIds(componentIds);
        initVO.setStaticComponentIds(staticComponentIds);
        initVO.setErrorList(errorMsgList);
        return initVO;
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
    private static void registerComponent(Map<String, Object> logicConfig, List<String> componentIds, List<String> staticComponentIds, List<String> errorMsgList, List<String> registerComponentIds) {
        // 获取组件ID（重复ID不重复解析）
        Object componentIdObj = logicConfig.get(ID_FIELD);
        String componentId = componentIdObj == null ? UUID.randomUUID().toString() : componentIdObj.toString();

        // 判断组件是否已经构建过
        AbsComponent<?,?> component = COMPONENT_MAP.get(componentId);
        if (component != null) {
            addcomponentId(componentIds, staticComponentIds, component);
            return;
        }

        // 获取组件配置
        Object typeObj = logicConfig.get(COMPONENT_TYPE_FIELD);
        if (typeObj == null) {
            return;
        }

        // 构建组件
        String componentType = typeObj.toString();
        component = ComponentClassHandler.build(componentId, componentType, logicConfig);
        if (component == null) {
            errorMsgList.add("组件【" + componentId + "】构建失败");
            return;
        }

        // 检查组件配置
        List<String> checkErrors = ComponentClassHandler.init(component);
        if (checkErrors != null) {
            errorMsgList.addAll(checkErrors);
        }

        // 注册到组件池，并记录
        COMPONENT_MAP.put(component.getId(), component);
        registerComponentIds.add(component.getId());

        // 添加流程组件ID集合
        addcomponentId(componentIds, staticComponentIds, component);
    }

    /**
     * 添加组件ID
     *
     * @param componentIds 组件ID集合
     * @param component 组件
     */
    private static void addcomponentId(List<String> componentIds, List<String> staticComponentIds, AbsComponent<?,?> component) {
        // 静态逻辑块只构建，不加入到流程中
        if (component.getLogicConfig().getFlags().contains(ComponentFlagEnum.STATIC)) {
            staticComponentIds.add(component.getId());
            return;
        }

        componentIds.add(component.getId());
    }

    /**
     * 执行业务逻辑集合
     *
     * @param params 参数
     * @param componentIds 业务逻辑中-需要执行的组件ID集合
     * @param func 自定义函数
     * @return 结果集
     */
    public static DataResult execute(String key, Map<String, Object> params, Map<String, String> headers, List<String> componentIds, Function func) {
        // 初始化流程上下文
        ComponentContextHandler.init(key, params, headers);
        // 日志打印参数信息
        PrintHelper.printComponentParam();

        // 前置函数
        if (func != null) {
            func.before();
        }

        // 执行前置逻辑
        DataResult result = FlowAspectHandler.executeBefore(key);

        // 执行流程逻辑
        if (result.getSuccess() && CheckEmptyUtil.isNotEmpty(componentIds)) {
            result = executeComponents(componentIds);
        }

        // 执行后置逻辑
        FlowAspectHandler.executeAfter(key);

        // 后置函数
        if (func != null) {
            func.after();
        }

        // 清除缓存
        ComponentContextHandler.clear();

        return result;
    }

    /**
     * 执行组件
     *
     * @param componentIds 组件集合
     * @return 结果
     */
    public static DataResult executeComponents(List<String> componentIds) {
        // 按顺序执行组件逻辑
        DataResult dataResult = null;

        for (String componentKey : componentIds) {
            dataResult = executeComponent(getComponent(componentKey));
            if (!dataResult.getSuccess()) {
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
    public static DataResult executeComponent(AbsComponent<?,?> component) {
        // 运行组件逻辑
        DataResult result = component.run();

        // 如果配置中定义了报错提示语，则使用配置的报错提示语
        if (CheckEmptyUtil.isNotEmpty(component.getLogicConfig().getShowMsg())) {
            result.setShowMsg(component.getLogicConfig().getShowMsg());
        }

        // 打印日志
        if (GlobalContextHandler.get().getPrintComponentResult()) {
            PrintHelper.printComponentResult(component, result);
        }
        return result;
    }

    /**
     * 获取组件
     *
     * @param componentId 组件ID
     * @return 组件
     */
    public static AbsComponent<?,?> getComponent(String componentId) {
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
