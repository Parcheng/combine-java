package com.parch.combine.components.data.enums.register;

import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldGroup;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEnumRegisterLogicConfig extends LogicConfig {

    @Field(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @Field(key = "items", name = "枚举项配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldGroup(index = 0, name = "枚举项编码", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "枚举项名称", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 2, name = "枚举项描述", type = FieldTypeEnum.TEXT, isRequired = false)
    private List<EnumCacheHandler.EnumItem> items = new ArrayList<>();


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<EnumCacheHandler.EnumItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            EnumCacheHandler.EnumItem item = new EnumCacheHandler.EnumItem();
            item.setCode(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setName(ConfigGroupTool.getConfigByIndex(itemStr,1));
            item.setDesc(ConfigGroupTool.getConfigByIndex(itemStr,2));
            return item;
        });
    }
}
