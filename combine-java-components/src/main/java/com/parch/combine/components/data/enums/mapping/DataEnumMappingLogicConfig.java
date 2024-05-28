package com.parch.combine.components.data.enums.mapping;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldGroup;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEnumMappingLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源", type = FieldTypeEnum.TEXT, hasExpression = true, isRequired = true)
    private String source;

    @Field(key = "resultId", name = "输出指定组件ID的执行结果", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认输出 source 的数据")
    private String resultId;

    @Field(key = "items", name = "映射配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldGroup(index = 0, name = "枚举KEY", type = FieldTypeEnum.TEXT, hasExpression = true)
    @FieldGroup(index = 1, name = "源的字段名", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 2, name = "目标字段名（默认写到源字段中）", type = FieldTypeEnum.TEXT, isRequired = false)
    private List<MappingItem> items = new ArrayList<>();

    @Override
    public void init() {}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<MappingItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            MappingItem item = new MappingItem();
            item.setEnumKey(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setSourceField(ConfigGroupTool.getConfigByIndex(itemStr,1));
            item.setTargetField(ConfigGroupTool.getConfigByIndex(itemStr,2));
            return item;
        });
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public static class MappingItem {

        private String enumKey;

        private String sourceField;

        private String targetField;

        public String getEnumKey() {
            return enumKey;
        }

        public void setEnumKey(String enumKey) {
            this.enumKey = enumKey;
        }

        public String getSourceField() {
            return sourceField;
        }

        public void setSourceField(String sourceField) {
            this.sourceField = sourceField;
        }

        public String getTargetField() {
            return targetField;
        }

        public void setTargetField(String targetField) {
            this.targetField = targetField;
        }
    }
}
