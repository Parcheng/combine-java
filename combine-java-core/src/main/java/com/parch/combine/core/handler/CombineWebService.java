package com.parch.combine.core.handler;

import com.parch.combine.common.exception.CommonErrorEnum;
import com.parch.combine.common.exception.SysException;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.ResourceFileUtil;
import com.parch.combine.core.base.FileInfo;
import com.parch.combine.core.context.GlobalContext;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.service.ICombineWebService;
import com.parch.combine.core.vo.ComponentInitVO;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.vo.FlowConfigVO;
import com.parch.combine.core.vo.FlowInitVO;

import java.util.*;
import java.util.function.Consumer;

/**
 * 流程配置处理器
 */
public class CombineWebService implements ICombineWebService {

    private boolean openRegister = true;

    /**
     * 初始化
     */
    public List<ComponentInitVO> init() {
        return ComponentClassHandler.init();
    }

    /**
     * 初始化流程配置集合
     *
     * @param path 配置文件路径
     * @param func 自定义函数
     */
    public void registerFlowAsPath(String path, Consumer<FlowInitVO> func) {
        String configJson = ResourceFileUtil.read(path);
        registerFlow(configJson, func);
    }

    @Override
    public void registerFlow(String configJson, Consumer<FlowInitVO> func) {
        if (!openRegister) {
            throw new SysException(CommonErrorEnum.FLOW_UN_OPEN_REGISTER);
        }

        if (CheckEmptyUtil.isEmpty(configJson)) {
            return;
        }

        FlowConfigVO config = JsonUtil.deserialize(configJson, FlowConfigVO.class);
        if (config == null) {
            return;
        }

        // 保存常量到常量池
        ConstantHandler.save(config.getConstant());

        // 加载初始化配置
        InitConfigHandler.load(config.getInit());

        // 初始化逻辑块
        BlockHandler.init(config.getBlocks(), func);

        // 初始化前置逻辑
        FlowAspectHandler.initBefore(config.getBefore(), func);

        // 初始化后置逻辑
        FlowAspectHandler.initAfter(config.getAfter(), func);

        // 初始化每个接口的逻辑
        FlowHandler.init(config.getFlows(), func);
    }

    @Override
    public boolean save(String domain, String function, List<String> configs) {
        if (!openRegister) {
            throw new SysException(CommonErrorEnum.FLOW_UN_OPEN_REGISTER);
        }

        return FlowHandler.save(domain, function, configs);
    }

    @Override
    public List<String> getComponentIds(String domain, String function) {
        return FlowHandler.list(domain, function);
    }

    @Override
    public DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers) {
        return execute(domain, function, params, headers, (FileInfo) null);
    }

    @Override
    public DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo) {
        GlobalContext.FlagConfigs flagConfigs = GlobalContextHandler.get().getFlagConfigs();
        if (CheckEmptyUtil.isNotEmpty(flagConfigs.getInnerFlow()) && domain.startsWith(flagConfigs.getInnerFlow())) {
            throw new SysException(CommonErrorEnum.FLOW_IS_PROTECTED);
        }
        return executeAny(domain, function, params, headers, fileInfo);
    }

    @Override
    public DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers) {
        return executeAny(domain, function, params, headers, null);
    }

    @Override
    public DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo) {
        List<String> componentIds = this.getComponentIds(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            throw new SysException(CommonErrorEnum.FLOW_COMPONENT_IS_NULL);
        }
        return execute(domain, function, params, headers, fileInfo, componentIds);
    }

    /**
     * 执行业务逻辑集合
     *
     * @param params 参数
     * @param componentIds 业务逻辑中-需要执行的组件ID集合
     * @return 结果集
     */
    private DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo, List<String> componentIds) {
        String key = FlowKeyUtil.getKey(domain, function);
        return ComponentHandler.execute(key, params, headers, fileInfo, componentIds, null);
    }

    /**
     * 设置是否开放注册
     *
     * @param openRegister 是否开放注册
     */
    public void setOpenRegister(boolean openRegister) {
        this.openRegister = openRegister;
    }
}
