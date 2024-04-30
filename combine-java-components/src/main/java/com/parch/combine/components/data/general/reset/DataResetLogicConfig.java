package com.parch.combine.components.data.general.reset;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.ConfigGroupHelper;
import com.parch.combine.core.tools.compare.CompareGroupConfig;
import com.parch.combine.core.tools.variable.DataTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataResetLogicConfig extends LogicConfig {

    @ComponentField(key = "nullValue", name = "是否允许空值", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean nullValue;

    @ComponentField(key = "items", name = "重新设值配置集合，", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @ComponentFieldObject(type = DataResetCompare.class)
    private List<DataResetCompare> items = new ArrayList<>();

    @Override
    public void init() {
        if (this.nullValue == null) {
            this.nullValue = true;
        }
    }

    public static class DataResetCompare extends CompareGroupConfig {
        @ComponentField(key = "resets", name = "配置项集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
        @ComponentFieldDesc("将 “新值” 赋值给 “要重新赋值的字段”")
        @ComponentFieldGroup(index = 0, name = "要重新赋值的字段名", type = FieldTypeEnum.TEXT)
        @ComponentFieldGroup(index = 1, name = "数据类型", type = FieldTypeEnum.SELECT)
        @ComponentFieldGroupSelect(index = 1, enumClass = DataTypeEnum.class)
        @ComponentFieldGroup(index = 2, name = "新值", type = FieldTypeEnum.TEXT)
        @ComponentFieldEg(eg = "$r.data001.name STRING zhangsan", desc = "将 zhangsan 重新赋值给组件 data001 的 name 字段")
        @ComponentFieldEg(eg = "$r.data001.age INT 1", desc = "将 1 重新赋值给组件 data001 的 age 字段")
        @ComponentFieldEg(eg = "$r.data001.desc VARIABLE $r.data002.desc", desc = "将组件 data002 的 desc 字段赋值给组件 data001 的 desc 字段")
        private List<DataResetConfig> resets = new ArrayList<>();
        public List<DataResetConfig> getResets() {
            return resets;
        }

        public void setResets(List<String> items) {
            this.resets = ConfigGroupHelper.buildItemList(items, itemStr -> {
                DataResetConfig item = new DataResetConfig();
                item.setFieldName(ConfigGroupHelper.getConfigByIndex(itemStr,0));
                item.setType(ConfigGroupHelper.getConfigByIndex(itemStr,1));
                item.setValue(ConfigGroupHelper.getConfigByIndex(itemStr,2));
                return item;
            });
        }
    }

    public static class DataResetConfig {
        /**
         * 字段名
         */
        String fieldName;

        /**
         * 字段类型
         */
        String type;

        /**
         * 值
         */
        String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Boolean getNullValue() {
        return nullValue;
    }

    public void setNullValue(Boolean nullValue) {
        this.nullValue = nullValue;
    }

    public List<DataResetCompare> getItems() {
        return items;
    }

    public void setItems(List<DataResetCompare> items) {
        this.items = items;
    }
}
