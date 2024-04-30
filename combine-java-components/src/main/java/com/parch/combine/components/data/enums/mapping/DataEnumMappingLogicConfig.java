package com.parch.combine.components.data.enums.mapping;

import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldGroup;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEnumMappingLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据来源", type = FieldTypeEnum.TEXT, isRequired = true)
    private String source;

    @ComponentField(key = "source", name = "输出指定组件ID的执行结果", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("默认输出 source 的数据")
    private String resultId;

    @ComponentField(key = "items", name = "映射配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @ComponentFieldGroup(index = 0, name = "枚举KEY", type = FieldTypeEnum.TEXT)
    @ComponentFieldGroup(index = 1, name = "源的字段名", type = FieldTypeEnum.TEXT)
    @ComponentFieldGroup(index = 2, name = "目标字段名（默认写到源字段中）", type = FieldTypeEnum.TEXT, isRequired = false)
    private List<MappingItem> items = new ArrayList<>();

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
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            MappingItem item = new MappingItem();
            item.setEnumKey(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setSourceField(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            item.setTargetField(ConfigGroupHelper.getConfigByIndex(itemStr,2));
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
