package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.handler.CombineManagerHandler;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.vo.FlowConfigVO;
import com.parch.combine.core.component.vo.FlowInitVO;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * 核心处理器
 */
public class CombineManager {

    private String scopeKey;

    private ComponentManager component;

    private ConstantManager constant;

    private FlowAspectManager flowAspect;

    private FlowManager flow;

    private InitConfigManager initConfig;

    public CombineManager() {
        scopeKey = UUID.randomUUID().toString();
        constant = new ConstantManager();
        initConfig = new InitConfigManager();
        component = new ComponentManager();
        flowAspect = new FlowAspectManager(component);
        flow = new FlowManager(component);
        CombineManagerHandler.register(scopeKey, this);
    }

    public void init(FlowConfigVO config, Consumer<FlowInitVO> func) {
        // 保存常量到常量池
        constant.save(config.getConstant());

        // 加载初始化配置
        initConfig.load(config.getInit());

        // 初始化逻辑块
        component.initBlock(scopeKey, config.getBlocks(), func);

        // 初始化前置和后置逻辑
        flowAspect.initBefore(scopeKey, config.getBefore(), func);
        flowAspect.initAfter(scopeKey, config.getAfter(), func);

        // 初始化每个接口的逻辑
        flow.init(scopeKey, config.getFlows(), func);
    }

    /**
     * 执行业务逻辑集合
     *
     * @param params 参数
     * @param componentIds 业务逻辑中-需要执行的组件ID集合
     * @param func 自定义函数
     * @return 结果集
     */
    public DataResult execute(String key, Map<String, Object> params, Map<String, String> headers, List<String> componentIds, ComponentManager.Function func) {
        return execute(key, params, headers, null, componentIds, func);
    }

    /**
     * 执行业务逻辑集合
     *
     * @param params 参数
     * @param componentIds 业务逻辑中-需要执行的组件ID集合
     * @param func 自定义函数
     * @return 结果集
     */
    public DataResult execute(String key, Map<String, Object> params, Map<String, String> headers, FileInfo file, List<String> componentIds, ComponentManager.Function func) {
        // 初始化流程上下文
        ComponentContextHandler.init(scopeKey, key, params, headers, file);
        // 打印请求头和参数信息
        PrintHelper.printComponentHeader();
        PrintHelper.printComponentParam();

        // 前置函数
        if (func != null) {
            func.before();
        }

        // 执行前置逻辑
        DataResult result = flowAspect.executeBefore(key);

        // 执行流程逻辑
        if (result.getSuccess() && !result.isStop() && CheckEmptyUtil.isNotEmpty(componentIds)) {
            result = component.executeComponents(componentIds);
        }

        // 执行后置逻辑
        flowAspect.executeAfter(key);

        // 后置函数
        if (func != null) {
            func.after();
        }

        // 清除缓存
        ComponentContextHandler.clear();

        return result;
    }

    public String getScopeKey() {
        return scopeKey;
    }

    public ComponentManager getComponent() {
        return component;
    }

    public ConstantManager getConstant() {
        return constant;
    }

    public FlowAspectManager getFlowAspect() {
        return flowAspect;
    }

    public FlowManager getFlow() {
        return flow;
    }

    public InitConfigManager getInitConfig() {
        return initConfig;
    }
}
