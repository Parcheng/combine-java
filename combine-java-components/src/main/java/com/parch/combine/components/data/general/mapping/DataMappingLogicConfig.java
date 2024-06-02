package com.parch.combine.components.data.general.mapping;

import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldGroup;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataMappingLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "映射配置项集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldGroup(index = 0, name = "字段名", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "取值目标的字段或常量值", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "id #{id}", desc = "将入参的 id 参数的值，赋值给执行结果的 id 字段")
    @FieldEg(eg = "name #{$r.data001.name}", desc = "将 data001 组件返回结果的 name 字段的值，赋值给执行结果的 name 字段")
    @FieldEg(eg = "age 18", desc = "将 18 赋值给执行结果的 age 字段")
    private List<DataMappingItem> items = new ArrayList<>();

    @Override
    public void init() {}

    public List<DataMappingItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataMappingItem item = new DataMappingItem();
            item.setNewFieldName(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setSource(ConfigGroupTool.getConfigByIndex(itemStr,1));
            return item;
        });
    }

    public static class DataMappingItem {
        /**
         * 新字段名
         */
        String newFieldName;

        /**
         * 源数据
         */
        String source;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getNewFieldName() {
            return newFieldName;
        }

        public void setNewFieldName(String newFieldName) {
            this.newFieldName = newFieldName;
        }
    }
}
