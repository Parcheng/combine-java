package com.parch.combine.core.component.service;

import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.vo.FlowInitVO;
import com.parch.combine.core.component.manager.ComponentManager;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface ICombineWebService {

    /**
     * 注册流程配置集合
     *
     * @param configJson 配置JSON
     * @param func       自定义函数
     */
    void registerFlow(String configJson, Consumer<FlowInitVO> func);

    /**
     * 保存流程配置集合
     *
     * @param domain   域
     * @param function 功能
     * @return 是否成功
     */
    boolean save(String domain, String function, List<String> configs);

    /**
     * 获取流程配置集合
     *
     * @param domain   域
     * @param function 功能
     * @return 流程配置集合
     */
    List<String> getComponentIds(String domain, String function);

    /**
     * 执行业务逻辑集合
     *
     * @param domain   域
     * @param function 功能
     * @param params   参数
     * @param headers  请求头
     * @return 结果集
     */
    DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers);

    /**
     * 执行业务逻辑集合
     *
     * @param domain   域
     * @param function 功能
     * @param params   参数
     * @param headers  请求头
     * @param fileInfo 文件信息
     * @return 结果集
     */
    DataResult execute(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo);

    /**
     * 执行任何业务逻辑集合（包括不允许外部触发的）
     *
     * @param domain   域
     * @param function 功能
     * @param params   参数
     * @param headers  请求头
     * @return 结果集
     */
    DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers);

    /**
     * 执行任何业务逻辑集合（包括不允许外部触发的）
     *
     * @param domain   域
     * @param function 功能
     * @param params   参数
     * @param headers  请求头
     * @param fileInfo 文件信息
     * @return 结果集
     */
    DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo);

    /**
     * 执行任何业务逻辑集合（包括不允许外部触发的）
     *
     * @param domain   域
     * @param function 功能
     * @param params  参数
     * @param headers 请求头
     * @return 结果集
     */
    DataResult executeAny(String domain, String function, Map<String, Object> params, Map<String, String> headers, FileInfo file, ComponentManager.Function func);

    /**
     * 执行任何业务逻辑集合（包括不允许外部触发的）
     *
     * @param key   域/功能
     * @param params  参数
     * @param headers 请求头
     * @return 结果集
     */
    DataResult executeAny(String key, Map<String, Object> params, Map<String, String> headers, FileInfo file, List<String> componentIds, ComponentManager.Function func);
}
