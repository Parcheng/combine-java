package com.parch.combine.core.settings.builder;

import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.settings.config.*;
import com.parch.combine.core.settings.handler.ComponentSettingHelper;
import com.parch.combine.core.settings.handler.SettingBuildErrorHandler;

import java.util.*;

/**
 * 全局配置构建类
 */
public class ComponentPropertiesSettingBuilder {

    private int startIndex = 0;
    private List<ComponentPropertySetting> configList = new ArrayList<>(16);
    private Map<String, ComponentPropertySetting> configMap = new HashMap<>(16);

    public ComponentPropertiesSettingBuilder() {}

    public ComponentPropertiesSettingBuilder(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 添加动态配置属性值
     */
    public String addDynamicProperty(String parentKey, String key, String value, String name) {
        String dynamicKey = parentKey + "." + key + "." + key + " == " + value;
        addProperty(dynamicKey, FieldTypeEnum.TEXT, name, null,null, null);
        return dynamicKey;
    }

    /**
     * 添加初始化配置的属性
     */
    public void addProperty(String key, FieldTypeEnum fieldType, String name, boolean isRequired, boolean isArray) {
        addProperty(key, fieldType, name, isRequired,isArray, "无");
    }

    /**
     * 添加配置的属性
     */
    public void addProperty(String key, FieldTypeEnum fieldType, String name, Boolean isRequired, Boolean isArray, String defaultValue) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        key = amendKey(key);
        String[] keyPath = key.split(SymbolConstant.TROPE_DOT);
        if (keyPath.length == 0) {
            SettingBuildErrorHandler.print("KEY 不合法: " + key, null);
            return;
        }

        String currKey = keyPath[keyPath.length -1];
        ComponentPropertySetting propertySetting = ComponentSettingHelper.buildProperty(currKey, fieldType, name, isRequired,isArray, defaultValue);
        if (keyPath.length > 1) {
            String parentKey = key.substring(0, key.length() - (currKey.length() + 1));
            ComponentPropertySetting parentPropertySetting = configMap.get(parentKey);
            if (parentPropertySetting == null) {
                SettingBuildErrorHandler.print("父属性不存在 - key: " + key, null);
                return;
            }
            parentPropertySetting.getChildren().add(propertySetting);
        } else {
            configList.add(propertySetting);
        }

        configMap.put(key, propertySetting);
    }


    /**
     * 添加属性描述
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyRef(String propertyKey, String refKey, String refName) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        propertySetting.setRefKey(refKey);
        propertySetting.setName(propertySetting.getName() + "（详见“" + refName + "”配置）");
    }

    /**
     * 添加属性描述
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyDesc(String propertyKey, String desc) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        propertySetting.getDesc().add(desc);
    }

    /**
     * 设置属性的下拉选择
     *
     * @param propertyKey 属性KEY
     * @param options 下拉选择集合
     */
    public void setPropertyOption(String propertyKey, List<IOptionSetting> options) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        if (CheckEmptyUtil.isNotEmpty(options)) {
            List<ComponentPropertyOptionSetting> optionSettings = new ArrayList<>();
            for (IOptionSetting option : options) {
                if (option.isValid()) {
                    ComponentPropertyOptionSetting optionSetting = new ComponentPropertyOptionSetting();
                    optionSetting.setKey(option.getKey());
                    optionSetting.setName(option.getName());
                    optionSetting.setDesc(option.getDesc());
                    optionSettings.add(optionSetting);
                }
            }
            propertySetting.setOptions(optionSettings);
        }
    }

    /**
     * 设置属性的下拉选择
     *
     * @param propertyKey 属性KEY
     * @param key 下拉选项KEY
     * @param name 下拉选项名称
     */
    public void addPropertyOption(String propertyKey, String key, String name, String desc) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        ComponentPropertyOptionSetting optionSetting = new ComponentPropertyOptionSetting();
        optionSetting.setKey(key);
        optionSetting.setName(name);
        optionSetting.setDesc(desc);
        propertySetting.getOptions().add(optionSetting);
    }

    /**
     * 添加属性的组
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyGroup(String propertyKey, String name, FieldTypeEnum fieldType, boolean isRequired, List<IOptionSetting> options) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        propertySetting.getGroup().add(ComponentSettingHelper.buildPropertyGroup(name, fieldType, isRequired, options));
    }

    /**
     * 添加属性的组
     *
     * @param propertyKey 属性KEY
     */
    public void addPropertyEg(String propertyKey, String value, String desc) {
        if (!GlobalContextHandler.get().getLoadApiInfo()) {
            return;
        }

        propertyKey = amendKey(propertyKey);
        ComponentPropertySetting propertySetting = configMap.get(propertyKey);
        propertySetting.getEgs().add(ComponentSettingHelper.buildPropertyEg(value, desc));
    }

    private String amendKey(String key) {
        if (startIndex <= 0) {
            return key;
        }

        String[] keyPath = key.split(SymbolConstant.TROPE_DOT);
        if (startIndex >= keyPath.length) {
            SettingBuildErrorHandler.print("属性KEY修正后为空 - key: " + key, null);
            return null;
        }

        StringBuilder newKey = new StringBuilder();
        for (int i = startIndex; i < keyPath.length; i++) {
            if (i != startIndex) {
                newKey.append(".");
            }
            newKey.append(keyPath[i]);
        }

        return newKey.toString();
    }

    /**
     * 获取设置信息
     *
     * @return 设置信息
     */
    public List<ComponentPropertySetting> get() {
        return configList;
    }
}
