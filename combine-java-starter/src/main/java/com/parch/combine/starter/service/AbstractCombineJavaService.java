package com.parch.combine.starter.service;

import com.parch.combine.core.component.CombineJavaStarter;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.service.ICombineJavaService;
import com.parch.combine.core.component.vo.FlowResult;
import com.parch.combine.starter.dto.JsonConfigInitDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * CombineJava - Service基类
 */
public abstract class AbstractCombineJavaService {

    private ICombineJavaService service;

    public AbstractCombineJavaService(String configPath) {
        service = CombineJavaStarter.init(configPath);
    }

    /**
     * 调用流程
     *
     * @param params   参数
     * @param domain   域
     * @param function 函数
     * @return 结果
     */
    public FlowResult call(Map<String, Object> params, String domain, String function) {
        return execute(params, null, null, domain, function, null);
    }

    /**
     * 调用流程
     *
     * @param params   参数
     * @param headers  请求头
     * @param domain   域
     * @param function 函数
     * @return 结果
     */
    public FlowResult call(Map<String, Object> params, Map<String, String> headers, String domain, String function) {
        return execute(params, headers, null, domain, function, null);
    }

    /**
     * 调用流程
     *
     * @param params   参数
     * @param headers  请求头
     * @param domain   域
     * @param function 函数
     * @param downloadFunc  文件下载函数
     * @return 结果
     */
    public FlowResult call(Map<String, Object> params, Map<String, String> headers, String domain, String function, Function<FileInfo, Boolean> downloadFunc) {
        return execute(params, headers, null, domain, function, downloadFunc);
    }

    /**
     * 调用流程（支持上传文件）
     *
     * @param paramJson 参数JSON
     * @param headers  请求头
     * @param domain   域
     * @param function 函数
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public FlowResult uploadAndCall(String paramJson, Map<String, String> headers, FileInfo fileInfo, String domain, String function) {
        return uploadAndCall(paramJson, headers, fileInfo, domain, function, null);
    }

    /**
     * 调用流程（支持上传文件）
     *
     * @param paramJson 参数JSON
     * @param headers  请求头
     * @param domain   域
     * @param function 函数
     * @param downloadFunc  文件下载函数
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public FlowResult uploadAndCall(String paramJson, Map<String, String> headers, FileInfo fileInfo, String domain, String function, Function<FileInfo, Boolean> downloadFunc) {
        // 入参
        Map<String, Object> params;
        if (CheckEmptyUtil.isEmpty(paramJson)) {
            params = new HashMap<>();
        } else {
            params = JsonUtil.deserialize(paramJson, HashMap.class);
        }

        // 执行业务逻辑
        return execute(params, headers, fileInfo, domain, function, downloadFunc);
    }

    /**
     * 注册配置
     *
     * @param jsonData 配置数据JSON
     * @return 结果
     * @throws IOException 异常
     */
    public List<JsonConfigInitDTO> register(String jsonData) {
        List<JsonConfigInitDTO> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(jsonData)) {
            return result;
        }

        // 读取配置文件
        service.registerFlow(jsonData, vo -> {
            // 保存每个接口的初始化结果
            JsonConfigInitDTO initDTO = new JsonConfigInitDTO();
            initDTO.setKey(vo.getFlowKey());
            initDTO.setSuccess(vo.isSuccess());
            initDTO.setErrorList(vo.getErrorList());
            initDTO.setComponentIds(vo.getComponentIds());
            initDTO.setStaticComponentIds(vo.getStaticComponentIds());
            result.add(initDTO);
        });

        return result;
    }

    private FlowResult execute(Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo, String domain, String function, Function<FileInfo, Boolean> downloadFunc) {
        List<String> componentIds = service.getComponentIds(domain, function);
        if (componentIds == null) {
            return FlowResult.fail("接口未注册", "接口不存在");
        }

        FlowResult result = service.execute(domain, function, params, headers, fileInfo);
        if (downloadFunc != null && result.isDownload() && result.getData() instanceof FileInfo) {
            if (!downloadFunc.apply((FileInfo) result.getData())) {
                result.setSuccess(false);
                result.setErrMsg("文件下载异常");
                result.setShowMsg("文件下载异常");
            }
        }

        return result;
    }
}
