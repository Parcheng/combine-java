package com.parch.combine.core.common.settings.config;

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
