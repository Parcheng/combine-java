package com.parch.combine.core.component.context;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局流程解析上下文对象
 *
 * @author parch
 * @date 2023/4/26
 */
public class GlobalContext {

    @Field(key = "initConfigs", name = "初始化要导入的配置文件集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> initConfigs = new ArrayList<>();

    @Field(key = "initFlows", name = "初始化要执行的流程KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> initFlows = new ArrayList<>();

    @Field(key = "openRegisterConfig", name = "是否开放流程注册", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean openRegisterConfig = true;

    @Field(key = "requestIdKey", name = "流程请求ID的字段KEY", type = FieldTypeEnum.TEXT, defaultValue = "$requestId")
    private String requestIdKey = "$requestId";

    @Field(key = "printComponentResult", name = "日志是否打印组件执行结果", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean printComponentResult = true;

    @Field(key = "loadApiInfo", name = "是否加载API信息", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean loadApiInfo = true;

    @Field(key = "flagConfigs", name = "标识配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(FlagConfigs.class)
    private FlagConfigs flagConfigs = new FlagConfigs();

    public static class FlagConfigs {

        @Field(key = "innerFlow", name = "内部流程标识", type = FieldTypeEnum.TEXT, defaultValue = "$")
        @FieldDesc("不允许外部调用")
        private String innerFlow = "$";

        @Field(key = "componentResult", name = "组件结果标识", type = FieldTypeEnum.TEXT, defaultValue = "$r")
        private String componentResult = "$r";

        @Field(key = "componentResultShowMsg", name = "组件结果-显示错误信息标识", type = FieldTypeEnum.TEXT, defaultValue = "$showMsg")
        private String componentResultShowMsg = "$showMsg";

        @Field(key = "componentResultErrorMsg", name = "组件结果-错误信息标识", type = FieldTypeEnum.TEXT, defaultValue = "$errorMsg")
        private String componentResultErrorMsg = "$errorMsg";

        @Field(key = "componentResultSuccess", name = "组件结果-成功标识", type = FieldTypeEnum.TEXT, defaultValue = "$success")
        private String componentResultSuccess = "$success";

        @Field(key = "componentResultDownload", name = "组件结果-是否下载标识", type = FieldTypeEnum.TEXT, defaultValue = "$download")
        private String componentResultDownload = "$download";

        @Field(key = "flowConstant", name = "流程中常量标识", type = FieldTypeEnum.TEXT, defaultValue = "$c")
        private String flowConstant = "$c";

        @Field(key = "flowVariable", name = "流程中内部变量标识", type = FieldTypeEnum.TEXT, defaultValue = "$v")
        private String flowVariable = "$v";

        @Field(key = "flowHeader", name = "流程请求头标识", type = FieldTypeEnum.TEXT, defaultValue = "$h")
        private String flowHeader = "$h";

        @Field(key = "size", name = "数据长度标识", type = FieldTypeEnum.TEXT, defaultValue = "$size")
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