package com.parch.combine.html.common.enums;

import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 配置类型枚举
 */
public enum ConfigTypeEnum {

    TRIGGER("触发配置"),
    DATA_LOAD("数据加载配置"),
    ELEMENT("页面元素配置"),
    ELEMENT_GROUP("页面元素组配置"),
    ELEMENT_TEMPLATE("页面元素模板配置");

    private String name;

    ConfigTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
