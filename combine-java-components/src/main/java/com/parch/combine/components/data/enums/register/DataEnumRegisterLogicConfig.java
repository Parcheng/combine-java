package com.parch.combine.components.data.enums.register;

import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataEnumRegisterLogicConfig extends LogicConfig {

    @ComponentField(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @ComponentField(key = "items", name = "枚举项配置集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @ComponentFieldGroup(index = 0, name = "枚举项编码", type = FieldTypeEnum.TEXT)
    @ComponentFieldGroup(index = 1, name = "枚举项名称", type = FieldTypeEnum.TEXT)
    @ComponentFieldGroup(index = 2, name = "枚举项描述", type = FieldTypeEnum.TEXT, isRequired = false)
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
        this.items = ConfigGroupHelper.buildItemList(items, itemStr -> {
            EnumCacheHandler.EnumItem item = new EnumCacheHandler.EnumItem();
            item.setCode(ConfigGroupHelper.getConfigByIndex(itemStr,0));
            item.setName(ConfigGroupHelper.getConfigByIndex(itemStr,1));
            item.setDesc(ConfigGroupHelper.getConfigByIndex(itemStr,2));
            return item;
        });
    }
}
