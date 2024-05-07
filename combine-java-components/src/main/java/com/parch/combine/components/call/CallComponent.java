package com.parch.combine.components.call;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;

/**
 * 调用组件
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class CallComponent<T extends InitConfig, R extends CallLogicConfig> extends AbsComponent<T, R> {


    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public CallComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public final List<String> init() {
        List<String> result = new ArrayList<>();
        CallLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getUrl())) {
            result.add("调用地址为空");
        }

        // 调用实现类的检测逻辑
        List<String> implResult = initCallConfig();
        if (implResult != null) {
            result.addAll(implResult);
        }

        return result;
    }

    /**
     * 检测调用配置
     *
     * @return 错误信息
     */
    protected abstract List<String> initCallConfig();

    @Override
    @SuppressWarnings("unchecked")
    public final DataResult execute() {
        // 数据过滤
        CallLogicConfig logicConfig = getLogicConfig();
        Map<String, Object> params = logicConfig.getParams() == null ? new HashMap<>(0) : (Map<String, Object>) DataVariableHelper.parseAndCopy(logicConfig.getParams());
        Map<String, String> headers = logicConfig.getHeaders() == null ? new HashMap<>(0) : (Map<String, String>) DataVariableHelper.parseAndCopy(logicConfig.getHeaders());

        // 解析参数，将参数中${...}替换为实际值
        DataVariableHelper.parse(params);

        return execute(logicConfig.getUrl(), params, headers);
    }

    /**
     * 执行
     *
     * @param url URL
     * @param params 参数
     * @return 结果
     */
    public abstract DataResult execute(String url, Map<String, Object> params, Map<String, String> headers);
}
