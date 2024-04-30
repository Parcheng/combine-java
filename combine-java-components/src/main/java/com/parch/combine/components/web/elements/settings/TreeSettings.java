package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class TreeSettings extends BaseSettings{

    @ComponentField(key = "checkedField", name = "是否被选择", type = FieldTypeEnum.TEXT, defaultValue = "false")
    private String checkedField;

    @ComponentField(key = "textField", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String textField;

    @ComponentField(key = "childrenField", name = "子树", type = FieldTypeEnum.TEXT)
    private String childrenField;

    @ComponentField(key = "checkFirst", name = "是否默认选择第一项", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean checkFirst;

    @ComponentField(key = "triggers", name = "树项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldRef(key = WebSettingCanstant.TRIGGER_KEY)
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
