package com.parch.combine.core.service;

import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.vo.FlowInitVO;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface ICombineWebService {

    /**
     * 注册流程配置集合
     *
     * @param configJson 配置JSON
     * @param func 自定义函数
     */
    void registerFlow(String configJson, Consumer<FlowInitVO> func);

    /**
     * 保存流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 是否成功
     */
    boolean save(String domain, String function, List<String> configs);

    /**
     * 获取流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 流程配置集合
     */
    List<String> getComponentIds(String domain, String function);

    /**
     * 执行业务逻辑集合
     *
     * @param domain 域
     * @param function 功能
     * @param params 参数
     * @param headers 请求头
     * @return 结果集
     */
    DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers);

    /**
     * 执行任何业务逻辑集合（包括不允许外部触发的）
     *
     * @param domain 域
     * @param function 功能
     * @param params 参数
     * @param headers 请求头
     * @return 结果集
     */
    DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers);
}
