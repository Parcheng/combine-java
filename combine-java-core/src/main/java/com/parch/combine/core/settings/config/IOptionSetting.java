package com.parch.combine.core.settings.config;

/**
 * 下拉数据设置
 */
public interface IOptionSetting {

    String getKey();

    String getName();

    boolean isValid();

    default String getDesc() {
        return null;
    }

    default String[] getDetails() {
        return null;
    }
}
