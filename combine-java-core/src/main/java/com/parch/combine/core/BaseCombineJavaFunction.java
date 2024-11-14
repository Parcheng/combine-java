package com.parch.combine.core;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.CombineJavaLoader;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.service.ICombineJavaService;
import com.parch.combine.core.component.vo.FlowResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * CombineJava - Service基类
 */
public abstract class BaseCombineJavaFunction {

    private ICombineJavaService service;

    public BaseCombineJavaFunction(String configPath) {
        service = CombineJavaLoader.init(configPath);
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
            params = JsonUtil.string2Obj(paramJson, HashMap.class);
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
    public List<RegisterResult> register(String jsonData) {
        List<RegisterResult> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(jsonData)) {
            return result;
        }

        // 读取配置文件
        service.registerFlow(jsonData, vo -> {
            // 保存每个接口的初始化结果
            RegisterResult initDTO = new RegisterResult();
            initDTO.setKey(vo.getFlowKey());
            initDTO.setSuccess(vo.isSuccess());
            initDTO.setErrorList(vo.getErrorList());
            initDTO.setComponentIds(vo.getComponentIds());
            initDTO.setStaticComponentIds(vo.getStaticComponentIds());
            result.add(initDTO);
        });

        return result;
    }

    /**
     * 资源关闭
     */
    public void resourceClose() {
        service.resourceClose();
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

    /**
     * 注册结果
     */
    public static class RegisterResult {

        private boolean success;

        private String key;

        private List<String> errorList;

        private List<String> componentIds;

        private List<String> staticComponentIds;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getErrorList() {
            return errorList;
        }

        public void setErrorList(List<String> errorList) {
            this.errorList = errorList;
        }

        public List<String> getComponentIds() {
            return componentIds;
        }

        public void setComponentIds(List<String> componentIds) {
            this.componentIds = componentIds;
        }

        public List<String> getStaticComponentIds() {
            return staticComponentIds;
        }

        public void setStaticComponentIds(List<String> staticComponentIds) {
            this.staticComponentIds = staticComponentIds;
        }
    }
}
