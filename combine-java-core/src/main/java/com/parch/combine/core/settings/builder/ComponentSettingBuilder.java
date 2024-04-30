package com.parch.combine.core.settings.builder;

import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.settings.config.*;
import com.parch.combine.core.settings.handler.CommonSettingHandler;
import com.parch.combine.core.settings.handler.ComponentSettingHelper;
import com.parch.combine.core.settings.handler.SettingBuildErrorHandler;

import java.util.*;

/**
 * 组件设置构建类
 */
public class ComponentSettingBuilder {

    private ComponentSetting settings;

    private ComponentPropertiesSettingBuilder initConfigBuilder = new ComponentPropertiesSettingBuilder();
    private ComponentPropertiesSettingBuilder logicConfigBuilder = new ComponentPropertiesSettingBuilder();
    private ComponentCommonObjectBuilder commonBuilder = null;

    /**
     * 构造函数
     */
    public ComponentSettingBuilder(String key, String name, boolean hasInit, Class<? extends AbsComponent<?, ?>> componentClass) {
        settings = ComponentSettingHelper.build(key, name, componentClass);
        CommonSettingHandler.setProperty(this, hasInit);
    }

    /**
     * 添加描述信息（一行）
     */
    public void addDesc(String descRow) {
        settings.getDesc().add(descRow);
    }

    /**
     * 添加注意实现（一行）
     */
    public void addImportant(String importantRow) {
        settings.getImportant().add(importantRow);
    }

    /**
     * 添加配置的属性
     */
    public void addProperty(PropertyTypeEnum propertyType, String key, FieldTypeEnum fieldType, String name, boolean isRequired, boolean isArray) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, key)).addProperty(key, fieldType, name, isRequired,isArray);
    }

    /**
     * 添加配置的属性
     */
    public void addProperty(PropertyTypeEnum propertyType, String key, FieldTypeEnum fieldType, String name, boolean isRequired, boolean isArray, String defaultValue) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, key)).addProperty(key, fieldType, name, isRequired, isArray, defaultValue);
    }

    /**
     * 添加动态配置属性值
     */
    public String addDynamicProperty(PropertyTypeEnum propertyType, String propertyKey, String key, String value, String name) {
        return Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).addDynamicProperty(propertyKey, key, value, name);
    }

    /**
     * 添加配置的属性引用
     */
    public void addPropertyRef(PropertyTypeEnum propertyType, String key, String refKey, String refName) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, key)).addPropertyRef(key, refKey, refName);
    }

    /**
     * 添加属性描述
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyDesc(PropertyTypeEnum propertyType, String propertyKey, String desc) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).addPropertyDesc(propertyKey, desc);
    }

    /**
     * 设置属性的下拉选择
     *
     * @param propertyKey 属性KEY
     * @param options 下拉选择集合
     */
    public void setPropertyOption(PropertyTypeEnum propertyType, String propertyKey, List<IOptionSetting> options) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).setPropertyOption(propertyKey, options);
    }

    /**
     * 设置属性的下拉选择
     *
     * @param propertyKey 属性KEY
     * @param key 下拉选项KEY
     * @param name 下拉选项名称
     */
    public void addPropertyOption(PropertyTypeEnum propertyType, String propertyKey, String key, String name, String desc) {
       Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).addPropertyOption(propertyKey, key, name, desc);
    }

    /**
     * 添加属性的组
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyGroup(PropertyTypeEnum propertyType, String propertyKey, String name, FieldTypeEnum fieldType, boolean isRequired, List<IOptionSetting> options) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).addPropertyGroup(propertyKey, name, fieldType, isRequired, options);
    }

    /**
     * 添加属性的组
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyEg(PropertyTypeEnum propertyType, String propertyKey, String value, String desc) {
        Objects.requireNonNull(this.getPropertyMap(propertyType, propertyKey)).addPropertyEg(propertyKey, value, desc);
    }

    /**
     * 设置组件公共对象
     */
    public void addCommonObject(String key, String name, String desc) {
        if (commonBuilder == null) {
            commonBuilder = new ComponentCommonObjectBuilder();
        }
        commonBuilder.add(key, name, desc);
    }

    /**
     * 设置组件结果
     */
    public void setResult(String info, boolean isDownload) {
        settings.setResult(ComponentSettingHelper.buildResult(info, isDownload));
    }

    /**
     * 设置组件结果描述信息
     */
    public void addResultDesc(String desc) {
        settings.getResult().getDesc().add(desc);
    }

    /**
     * 获取设置信息
     *
     * @return 设置信息
     */
    public ComponentSetting get() {
        settings.setInitConfig(initConfigBuilder.get());
        settings.setLogicConfig(logicConfigBuilder.get());
        if (commonBuilder != null) {
            settings.setCommonObjects(commonBuilder.get());
        }
        return settings;
    }

    /**
     * 获取属性MAP
     *
     * @param type 类型
     * @return 属性MAP
     */
    private ComponentPropertiesSettingBuilder getPropertyMap(PropertyTypeEnum type, String key) {
        switch (type) {
            case INIT:
                return initConfigBuilder;
            case LOGIC:
                return logicConfigBuilder;
            case COMMON:
                String[] keyPath = key.split(SymbolConstant.TROPE_DOT);
                return commonBuilder.getPropertiesBuilder(keyPath[0]);
            default:
                SettingBuildErrorHandler.print("属性类型为空", null);
                return null;
        }
    }
}
