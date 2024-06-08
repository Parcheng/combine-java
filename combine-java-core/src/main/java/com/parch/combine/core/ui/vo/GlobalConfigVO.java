package com.parch.combine.core.ui.vo;

import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;

import java.util.ArrayList;
import java.util.List;

public class GlobalConfigVO implements IInit {

    @Field(key = "baseUrl", name = "根URL", type = FieldTypeEnum.TEXT, isRequired = true)
    private String baseUrl;

    @Field(key = "systemUrl", name = "系统根URL", type = FieldTypeEnum.TEXT, isRequired = true)
    private String systemUrl;

    @Field(key = "configs", name = "初始化要导入的配置文件集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> configs = new ArrayList<>();

    @Field(key = "initFlows", name = "初始化要执行的流程KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> initPages = new ArrayList<>();

    @Field(key = "flagConfigs", name = "标识配置集合", type = FieldTypeEnum.CONFIG)
    @FieldObject(FlagConfigs.class)
    private FlagConfigs flagConfigs;

    public static GlobalConfigVO build(String path) {
        GlobalConfigVO globalConfig = JsonUtil.deserialize(ResourceFileUtil.read(path), GlobalConfigVO.class);
        if (globalConfig == null) {
            globalConfig = new GlobalConfigVO();
        }

        globalConfig.init();
        return globalConfig;
    }

    @Override
    public void init() {
        if (flagConfigs == null) {
            flagConfigs = new FlagConfigs();
        }
        flagConfigs.init();
    }


    public static class FlagConfigs implements IInit {

        @Field(key = "element", name = "元素标识", type = FieldTypeEnum.TEXT, defaultValue = "$e")
        @FieldDesc("在变量中使用 #{$e.my_from} 时，表示获取 ID 为 my_from 的元素实例的值")
        private String element = "$e";

        @Field(key = "constant", name = "常量标识", type = FieldTypeEnum.TEXT, defaultValue = "$c")
        @FieldDesc("在变量中使用 #{$c.name} 时，表示从常量中取 name 属性的值")
        private String constant = "$c";

        @Field(key = "dataLoad", name = "数据加载标识", type = FieldTypeEnum.TEXT, defaultValue = "$ld")
        @FieldDesc("在变量中使用 #{$ld.user_list} 时，表示获取 ID 为 user_list 的数据加载的缓存结果")
        private String dataLoad = "$ld";

        @Field(key = "localStorage", name = "浏览器localStorage标识", type = FieldTypeEnum.TEXT, defaultValue = "$ls")
        @FieldDesc("在变量中使用 #{$ls.token} 时，表示从浏览器 localStorage 中取 token 属性的值")
        private String localStorage = "$ls";

        @Override
        public void init() {
            if (CheckEmptyUtil.isEmpty(this.element)) {
                this.element = "$e";
            }
            if (CheckEmptyUtil.isEmpty(this.constant)) {
                this.constant = "$c";
            }
            if (CheckEmptyUtil.isEmpty(this.dataLoad)) {
                this.dataLoad = "$ld";
            }
            if (CheckEmptyUtil.isEmpty(this.localStorage)) {
                this.localStorage = "$ls";
            }
        }

        public String getElement() {
            return element;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public String getConstant() {
            return constant;
        }

        public void setConstant(String constant) {
            this.constant = constant;
        }

        public String getDataLoad() {
            return dataLoad;
        }

        public void setDataLoad(String dataLoad) {
            this.dataLoad = dataLoad;
        }

        public String getLocalStorage() {
            return localStorage;
        }

        public void setLocalStorage(String localStorage) {
            this.localStorage = localStorage;
        }
    }

    public List<String> getConfigs() {
        return configs;
    }

    public void setConfigs(List<String> configs) {
        this.configs = configs;
    }

    public List<String> getInitPages() {
        return initPages;
    }

    public void setInitPages(List<String> initPages) {
        this.initPages = initPages;
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

    public FlagConfigs getFlagConfigs() {
        return flagConfigs;
    }

    public void setFlagConfigs(FlagConfigs flagConfigs) {
        this.flagConfigs = flagConfigs;
    }
}