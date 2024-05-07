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
public class TreeSettings extends BaseSettings{

    @Field(key = "checkedField", name = "是否被选择", type = FieldTypeEnum.TEXT, defaultValue = "false")
    private String checkedField;

    @Field(key = "textField", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String textField;

    @Field(key = "childrenField", name = "子树", type = FieldTypeEnum.TEXT)
    private String childrenField;

    @Field(key = "checkFirst", name = "是否默认选择第一项", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean checkFirst;

    @Field(key = "triggers", name = "树项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.TRIGGER_KEY)
    private List<TriggerEntity> triggers;

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getChildrenField() {
        return childrenField;
    }

    public void setChildrenField(String childrenField) {
        this.childrenField = childrenField;
    }

    public List<TriggerEntity> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggerEntity> triggers) {
        this.triggers = triggers;
    }

    public String getCheckedField() {
        return checkedField;
    }

    public void setCheckedField(String checkedField) {
        this.checkedField = checkedField;
    }

    public Boolean getCheckFirst() {
        return checkFirst;
    }

    public void setCheckFirst(Boolean checkFirst) {
        this.checkFirst = checkFirst;
    }
}
