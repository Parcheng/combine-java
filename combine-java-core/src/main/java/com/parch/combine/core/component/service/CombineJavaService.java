package com.parch.combine.core.component.service;

import com.parch.combine.core.common.exception.CommonErrorEnum;
import com.parch.combine.core.common.exception.SysException;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.context.ScopeContext;
import com.parch.combine.core.component.context.ScopeContextHandler;
import com.parch.combine.core.component.vo.CombineConfigVO;
import com.parch.combine.core.component.vo.CombineInitVO;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.manager.ComponentManager;
import com.parch.combine.core.component.vo.FlowResult;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 流程配置处理器
 */
public class CombineJavaService implements ICombineJavaService {

    private boolean openRegister = true;
    private boolean initSuccess = true;

    private final CombineManager combineManager = new CombineManager();

    /**
     * 初始化流程配置集合
     *
     * @param path 配置文件路径
     * @param func 自定义函数
     */
    public void registerFlowAsPath(String path, Consumer<CombineInitVO> func) {
        String configJson = ResourceFileUtil.read(path, CombineJavaService.class.getClassLoader());
        registerFlow(configJson, func);
    }

    @Override
    public void registerFlow(String configJson, Consumer<CombineInitVO> func) {
        if (!this.openRegister) {
            throw new SysException(CommonErrorEnum.FLOW_UN_OPEN_REGISTER);
        }

        if (CheckEmptyUtil.isEmpty(configJson)) {
            return;
        }

        CombineConfigVO config = JsonUtil.string2Obj(configJson, CombineConfigVO.class);
        this.registerFlow(config, func);
    }

    @Override
    public void registerFlow(CombineConfigVO config, Consumer<CombineInitVO> func) {
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
    public FlowResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers) {
        return execute(domain, function, params, headers, null);
    }

    @Override
    public FlowResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo) {
        ScopeContext.FlagConfigs flagConfigs = ScopeContextHandler.get(getScopeKey()).getFlagConfigs();
        if (CheckEmptyUtil.isNotEmpty(flagConfigs.getInnerFlow()) && domain.startsWith(flagConfigs.getInnerFlow())) {
            throw new SysException(CommonErrorEnum.FLOW_IS_PROTECTED);
        }
        return executeAny(domain, function, params, headers, fileInfo);
    }

    @Override
    public FlowResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers) {
        return executeAny(domain, function, params, headers, null);
    }

    @Override
    public FlowResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo) {
        return executeAny(domain, function, params, headers, fileInfo, null);
    }

    @Override
    public FlowResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo file, ComponentManager.Function func) {
        List<String> componentIds = this.getComponentIds(domain, function);
        if (CheckEmptyUtil.isEmpty(componentIds)) {
            throw new SysException(CommonErrorEnum.FLOW_COMPONENT_IS_NULL);
        }
        String key = FlowKeyUtil.getKey(domain, function);
        return executeAny(key, params, headers, file, componentIds, null);
    }

    @Override
    public FlowResult executeAny(String key, Map<String, Object> params, Map<String, String> headers, FileInfo file, List<String> componentIds, ComponentManager.Function func) {
        return combineManager.execute(key, params, headers, file, componentIds, func);
    }

    @Override
    public CombineManager getManager() {
        return this.combineManager;
    }

    @Override
    public String getScopeKey() {
        return combineManager.getScopeKey();
    }

    @Override
    public void resourceClose() {
        combineManager.getResourceClose().closeAll();
    }

    @Override
    public boolean isInitSuccess() {
        return initSuccess;
    }

    /**
     * 设置是否开放注册
     *
     * @param openRegister 是否开放注册
     */
    protected void setOpenRegister(boolean openRegister) {
        this.openRegister = openRegister;
    }

    /**
     * 设置是否初始化成功
     *
     * @param initSuccess 是否初始化成功
     */
    protected void setInitSuccess(boolean initSuccess) {
        this.initSuccess = initSuccess;
    }
}
