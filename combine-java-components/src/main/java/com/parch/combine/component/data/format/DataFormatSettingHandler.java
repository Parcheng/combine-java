package com.parch.combine.component.data.format;

import com.parch.combine.component.data.filter.DataFilterRuleEnum;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class DataFormatSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("format", "数据格式化组件", false, DataFormatComponent.class);
        builder.addDesc("数据格式化，支持多种格式化函数");

        builder.addProperty(PropertyTypeEnum.LOGIC, "replace", FieldTypeEnum.BOOLEAN, "是否替换源数据（默认不替换）",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "格式化配置集合",  false, true);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "要格式化的字段名", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "函数", FieldTypeEnum.SELECT,true, Arrays.asList(DataFilterRuleEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "参数", FieldTypeEnum.TEXT,  false, null);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "函数详细介绍：");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【JSON】: JSON数据转换");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：TO_JSON | JSON_TO_LIST | JSON_TO_OBJECT");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【CLEAR_DUPLICATE】: 清理重复数据（只支持集合类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（选填）：用于判断重复的字段，默认转为字符串比较（多个字段用逗号分割）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【SORT】: 排序（只支持集合类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（选填）：用于排序的字段，默认转为字符串排序（多个字段用逗号分割）,也可以使用‘-’符号表示不指定排序字段");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（选填）：正序/倒序，默认正序，可选值（asc正序，desc倒序）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【TEXT_CONVERT】: 文本转换函数（文本和模型转换）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：MODEL_TO_TEXT, MODELS_TO_TEXTS, MODELS_TO_TEXT, TEXT_TO_MODEL, TEXT_TO_MODELS, TEXTS_TO_MODELS");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（必填）：模型字段名（多个用‘,’分割）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数3（必填）：列分隔符");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数4（选填）：行分隔符（默认为所有空行符）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【GROUP】: 集合分组函数（只支持对象集合类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：用于分组的字段（多个字段用逗号分割）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【TO_MAP】: 集合转MAP函数（只支持对象集合类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：用于作为Map Key的字段（多个字段用逗号分割）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（选填）：用于作为Map Value的字段（默认为整个对象）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【TO_TREE】: 集合转树结构函数（只支持对象集合类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：用于数据的唯一标识字段名");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（必填）：用于指向父数据的唯一标识字段名");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数3（必填）：子数据的存储字段名");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【RANG】: 获取区间数据函数（只支持集合和字符串类型）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：起始下标");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（选填）：结束下标");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【CUSTOM】: 自定义函数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：Java类路径（必须实现 ICustomFormat 接口）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（支持多个，选填）：自定义函数额外参数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【MD5】: MD5 加密");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t无参数");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.extInfo JSON TO_JSON", "将组件 data001 的 extInfo 字段，转换成 JSON 字符串");


        builder.setResult("所有格式化后的新值集合", false);
        return builder.get();
    }
}
