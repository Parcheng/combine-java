package com.parch.combine.core.service;

import com.parch.combine.common.exception.CommonErrorEnum;
import com.parch.combine.common.exception.SysException;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.ResourceFileUtil;
import com.parch.combine.core.base.FileInfo;
import com.parch.combine.core.context.GlobalContext;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.manager.CombineManager;
import com.parch.combine.core.manager.ComponentManager;
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

    private CombineManager combineManager = new CombineManager();

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

        combineManager.init(config, func);
    }

    @Override
    public boolean save(String domain, String function, List<String> configs) {
        if (!openRegister) {
            throw new SysException(CommonErrorEnum.FLOW_UN_OPEN_REGISTER);
        }

        return combineManager.getFlow().save(domain, function, configs);
    }

    @Override
    public List<String> getComponentIds(String domain, String function) {
        return combineManager.getFlow().list(domain, function);
    }

    @Override
    public DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers) {
        return execute(domain, function, params, headers, null);
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
        return executeAny(domain, function, params, headers, fileInfo, null);
    }

    @Override
    public DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo file, ComponentManager.Function func) {
        List<String> componentIds = this.getComponentIds(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            throw new SysException(CommonErrorEnum.FLOW_COMPONENT_IS_NULL);
        }
        String key = FlowKeyUtil.getKey(domain, function);
        return executeAny(key, params, headers, file, componentIds, null);
    }

    @Override
    public DataResult executeAny(String key, Map<String, Object> params, Map<String, String> headers, FileInfo file, List<String> componentIds, ComponentManager.Function func) {
        return combineManager.execute(key, params, headers, file, componentIds, func);
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
