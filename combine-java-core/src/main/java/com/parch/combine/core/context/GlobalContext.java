package com.parch.combine.core.context;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局流程解析上下文对象
 *
 * @author parch
 * @date 2023/4/26
 */
public class GlobalContext {

    /**
     * 初始化要导入的配置文件集合
     */
    private List<String> initConfigs = new ArrayList<>();

    /**
     * 初始化要执行的流程Key集合
     */
    private List<String> initFlows = new ArrayList<>();

    /**
     * 开放注册配置
     */
    private Boolean openRegisterConfig = true;

    /**
     * 流程请求ID的字段KEY
     */
    private String requestIdKey = "$requestId";

    /**
     * 打印组件结果
     */
    private Boolean printComponentResult = true;

    /**
     * 加载API文档
     */
    private Boolean loadApiInfo = true;

    /**
     * 内部流程标识
     */
    private FlagConfigs flagConfigs = new FlagConfigs();

    public static class FlagConfigs {

        /**
         * 内部流程
         */
        private String innerFlow = "$";

        /**
         * 组件结果
         */
        private String componentResult = "$r";

        /**
         * 组件结果-显示错误信息
         */
        private String componentResultShowMsg = "$showMsg";

        /**
         * 组件结果-错误信息
         */
        private String componentResultErrorMsg = "$errorMsg";

        /**
         * 组件结果-成功
         */
        private String componentResultSuccess = "$success";

        /**
         * 组件结果-下载
         */
        private String componentResultDownload = "$download";

        /**
         * 流程常量
         */
        private String flowConstant = "$c";

        /**
         * 流程中变量
         */
        private String flowVariable = "$v";

        /**
         * 流程请求头
         */
        private String flowHeader = "$h";

        /**
         * 数组长度
         */
        private String size = "$size";


        public String getInnerFlow() {
            return innerFlow;
        }

        public void setInnerFlow(String innerFlow) {
            this.innerFlow = innerFlow;
        }

        public String getComponentResult() {
            return componentResult;
        }

        public void setComponentResult(String componentResult) {
            this.componentResult = componentResult;
        }

        public String getComponentResultShowMsg() {
            return componentResultShowMsg;
        }

        public void setComponentResultShowMsg(String componentResultShowMsg) {
            this.componentResultShowMsg = componentResultShowMsg;
        }

        public String getComponentResultErrorMsg() {
            return componentResultErrorMsg;
        }

        public void setComponentResultErrorMsg(String componentResultErrorMsg) {
            this.componentResultErrorMsg = componentResultErrorMsg;
        }

        public String getComponentResultSuccess() {
            return componentResultSuccess;
        }

        public void setComponentResultSuccess(String componentResultSuccess) {
            this.componentResultSuccess = componentResultSuccess;
        }

        public String getComponentResultDownload() {
            return componentResultDownload;
        }

        public void setComponentResultDownload(String componentResultDownload) {
            this.componentResultDownload = componentResultDownload;
        }

        public String getFlowConstant() {
            return flowConstant;
        }

        public void setFlowConstant(String flowConstant) {
            this.flowConstant = flowConstant;
        }

        public String getFlowVariable() {
            return flowVariable;
        }

        public void setFlowVariable(String flowVariable) {
            this.flowVariable = flowVariable;
        }

        public String getFlowHeader() {
            return flowHeader;
        }

        public void setFlowHeader(String flowHeader) {
            this.flowHeader = flowHeader;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

    public List<String> getInitConfigs() {
        return initConfigs;
    }

    public void setInitConfigs(List<String> initConfigs) {
        this.initConfigs = initConfigs;
    }

    public List<String> getInitFlows() {
        return initFlows;
    }

    public void setInitFlows(List<String> initFlows) {
        this.initFlows = initFlows;
    }

    public Boolean getOpenRegisterConfig() {
        return openRegisterConfig;
    }

    public void setOpenRegisterConfig(Boolean openRegisterConfig) {
        this.openRegisterConfig = openRegisterConfig;
    }

    public String getRequestIdKey() {
        return requestIdKey;
    }

    public void setRequestIdKey(String requestIdKey) {
        this.requestIdKey = requestIdKey;
    }


    public Boolean getPrintComponentResult() {
        return printComponentResult;
    }

    public void setPrintComponentResult(Boolean printComponentResult) {
        this.printComponentResult = printComponentResult;
    }


    public FlagConfigs getFlagConfigs() {
        return flagConfigs;
    }

    public void setFlagConfigs(FlagConfigs flagConfigs) {
        this.flagConfigs = flagConfigs;
    }

    public Boolean getLoadApiInfo() {
        return loadApiInfo;
    }

    public void setLoadApiInfo(Boolean loadApiInfo) {
        this.loadApiInfo = loadApiInfo;
    }
}