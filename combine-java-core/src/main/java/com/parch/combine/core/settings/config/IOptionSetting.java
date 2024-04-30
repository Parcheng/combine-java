package com.parch.combine.core.settings.config;

/**
 * 下拉数据设置
 */
public interface IOptionSetting {

    String getKey();

    String getName();

    String getDesc();

    boolean isValid();
}
