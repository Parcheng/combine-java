package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

/**
 * 页面元素设置
 */
public class TreeSettings extends BaseSettings{

    private String checkedField;

    private String textField;

    private String childrenField;

    private Boolean checkFirst;

    private TriggerEntity trigger;

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

    public TriggerEntity getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerEntity trigger) {
        this.trigger = trigger;
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
