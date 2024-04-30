package com.parch.combine.core.settings.handler;

import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.settings.config.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建设置处理器
 */
public class ComponentSettingHelper {

    /**
     * 构造组件分类设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static ComponentClassifySetting buildClassify(String key, String name) {
        ComponentClassifySetting classify = new ComponentClassifySetting();
        classify.setKey(key);
        classify.setName(name);
        classify.setSettings(new ArrayList<>());
        return classify;
    }

    /**
     * 构造组件设置
     *
     * @param key KEY
     * @param name 名称
     * @return 组件设置
     */
    public static ComponentSetting build(String key, String name, Class<? extends AbsComponent<?, ?>> componentClass) {
        ComponentSetting settings = new ComponentSetting();
        settings.setKey(key);
        settings.setName(name);
        settings.setDesc(new ArrayList<>());
        settings.setImportant(new ArrayList<>());
        settings.setInitConfig(new ArrayList<>());
        settings.setLogicConfig(new ArrayList<>());
        settings.setComponentClass(componentClass);
        return settings;
    }

    /**
     * 构建组件属性
     *
     * @param key 字段KEK
     * @param type 字段类型
     * @param name 字段名称
     * @param isRequired 是否必填
     * @param isArray 是否是集合
     * @param defaultValue 默认值描述
     * @return 属性设置
     */
    public static ComponentPropertySetting buildProperty(String key, FieldTypeEnum type, String name, Boolean isRequired, Boolean isArray, String defaultValue) {
        ComponentPropertySetting property = new ComponentPropertySetting();
        property.setKey(key);
        property.setType(type);
        property.setName(name);
        property.setIsRequired(isRequired);
        property.setIsArray(isArray);
        property.setDefaultValue(defaultValue);
        property.setDesc(new ArrayList<>());
        property.setEgs(new ArrayList<>());
        property.setChildren(new ArrayList<>());
        if (type == FieldTypeEnum.GROUP) {
            property.setGroup(new ArrayList<>());
        } else if (type == FieldTypeEnum.SELECT) {
            property.setOptions(new ArrayList<>());
        }
        return property;
    }

    /**
     * 构建组件属性
     *
     * @param name 字段名称
     * @param isRequired 是否必填
     * @return 属性设置
     */
    public static ComponentPropertyGroupSetting buildPropertyGroup(String name, FieldTypeEnum type, boolean isRequired, List<IOptionSetting> options) {
        ComponentPropertyGroupSetting group = new ComponentPropertyGroupSetting();
        group.setName(name);
        group.setType(type);
        group.setIsRequired(isRequired);
        group.setOptions(options);
        return group;
    }

    /**
     * 构建组件属性示例
     *
     * @param desc 示例描述
     * @param value 示例值
     * @return 属性设置
     */
    public static ComponentPropertyEgSetting buildPropertyEg(String value, String desc) {
        ComponentPropertyEgSetting eg = new ComponentPropertyEgSetting();
        eg.setDesc(desc);
        eg.setValue(value);
        return eg;
    }


    /**
     * 构建组件结果设置
     *
     * @param info 简单信息
     * @param isDownload 是否下载
     * @return 结果对象
     */
    public static ComponentResultSetting buildResult(String info, boolean isDownload) {
        ComponentResultSetting result = new ComponentResultSetting();
        result.setInfo(info);
        result.setIsDownload(isDownload);
        result.setDesc(new ArrayList<>());
        return result;
    }
}
