package com.parch.combine.page.page;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class WebPageInitConfig extends InitConfig {

    public final static String SYSTEM_URL_FLAG = "${systemUrl}";
    public final static String BASE_URL_FLAG = "${baseUrl}";

    @ComponentField(key = "baseUrl", name = "根URL", type = FieldTypeEnum.TEXT)
    @ComponentFieldEg(eg = "http://127.0.0.1:8080/combine", desc = "前端引用地址根URL为 127.0.0.1:8080/combine")
    private String baseUrl;

    @ComponentField(key = "systemUrl", name = "系统资源根URL", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("加载系统资源时使用的跟URL（如：系统内置的前端JS和CSS）")
    private String systemUrl;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getBaseUrl())) {
            setBaseUrl("");
        }
        if (CheckEmptyUtil.isEmpty(getSystemUrl())) {
            setSystemUrl("");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSystemUrl() {
        return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
        this.systemUrl = systemUrl;
    }
}
