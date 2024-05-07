package com.parch.combine.components.web.elements.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "页面元素加载数据触发配置", desc = "当 TYPE = LOAD_DATA 时的参数列表")
public class TriggerLoadDataEntity extends TriggerEntity {

    @Field(key = "loadIds", name = "要加载的LOAD ID集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    private List<String> loadIds;

    public List<String> getLoadIds() {
        return loadIds;
    }

    public void setLoadIds(List<String> loadIds) {
        this.loadIds = loadIds;
    }
}

