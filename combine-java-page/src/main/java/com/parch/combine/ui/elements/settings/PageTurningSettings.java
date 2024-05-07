package com.parch.combine.ui.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class PageTurningSettings extends BaseSettings{

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String currPage;

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String maxPage;

    @Field(key = "triggers", name = "翻页触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.TRIGGER_KEY)
    private List<TriggerEntity> triggers;

    public String getCurrPage() {
        return currPage;
    }

    public void setCurrPage(String currPage) {
        this.currPage = currPage;
    }

    public String getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    public List<TriggerEntity> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerEntity> triggers) {
        this.triggers = triggers;
    }
}
