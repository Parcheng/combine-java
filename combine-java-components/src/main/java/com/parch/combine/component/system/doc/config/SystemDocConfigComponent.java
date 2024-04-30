package com.parch.combine.component.system.doc.config;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.GlobalContext;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.settings.builder.ComponentPropertiesSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.vo.DataResult;
import java.util.*;

/**
 * 组件设置信息组件
 */
public class SystemDocConfigComponent extends AbsComponent<SystemDocConfigInitConfig, SystemDocConfigLogicConfig> {

    private List<HashMap> result;

    /**
     * 构造器
     */
    public SystemDocConfigComponent() {
        super(SystemDocConfigInitConfig.class, SystemDocConfigLogicConfig.class);
    }


    @Override
    public List<String> init(){
        ComponentPropertiesSettingBuilder builder = new ComponentPropertiesSettingBuilder();
        builder.addProperty("initConfigs", FieldTypeEnum.TEXT, "初始化要导入的配置文件集合",  false, true);
        builder.addProperty("initFlows", FieldTypeEnum.TEXT, "初始化要执行的流程KEY集合",  false, true);
        builder.addProperty("openRegisterConfig", FieldTypeEnum.BOOLEAN, "是否开放流程注册",  false, false, "true");
        builder.addProperty("requestIdKey", FieldTypeEnum.TEXT, "流程请求ID的字段KEY",  false, false, "$requestId");
        builder.addProperty("printComponentResult", FieldTypeEnum.BOOLEAN, "日志是否打印组件执行结果",  false, false, "true");
        builder.addProperty("loadApiInfo", FieldTypeEnum.BOOLEAN, "是否加载API信息",  false, false, "true");
        builder.addProperty("flagConfigs", FieldTypeEnum.OBJECT, "标识配置集合",  false, false);
        builder.addProperty("flagConfigs.innerFlow", FieldTypeEnum.TEXT, "内部流程标识",  false, false, "$");
        builder.addProperty("flagConfigs.componentResult", FieldTypeEnum.TEXT, "组件结果标识",  false, false, "$r");
        builder.addProperty("flagConfigs.componentResultShowMsg", FieldTypeEnum.TEXT, "组件结果-显示错误信息标识",  false, false, "$showMsg");
        builder.addProperty("flagConfigs.componentResultErrorMsg", FieldTypeEnum.TEXT, "组件结果-错误信息标识",  false, false, "$errorMsg");
        builder.addProperty("flagConfigs.componentResultSuccess", FieldTypeEnum.TEXT, "组件结果-成功标识",  false, false, "$success");
        builder.addProperty("flagConfigs.componentResultDownload", FieldTypeEnum.TEXT, "组件结果-是否下载标识",  false, false, "$download");
        builder.addProperty("flagConfigs.flowConstant", FieldTypeEnum.TEXT, "流程中常量标识",  false, false, "$c");
        builder.addProperty("flagConfigs.flowVariable", FieldTypeEnum.TEXT, "流程中内部变量标识",  false, false, "$v");
        builder.addProperty("flagConfigs.flowHeader", FieldTypeEnum.TEXT, "流程请求头标识",  false, false, "$h");
        builder.addProperty("flagConfigs.size", FieldTypeEnum.TEXT, "数据长度标识",  false, false, "$size");

        String json = JsonUtil.serialize(builder.get());
        result = JsonUtil.parseArray(json, HashMap.class);
        return new ArrayList<>();
    }

    @Override
    public DataResult execute() {
        GlobalContext context = GlobalContextHandler.get();
        if (context == null) {
            return DataResult.fail(SystemDocConfigErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
