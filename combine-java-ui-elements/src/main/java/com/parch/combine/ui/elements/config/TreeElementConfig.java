package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.trigger.Trigger;
import com.parch.combine.ui.core.settings.PageSettingCanstant;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "tree", name = "树元素", templateClass = TreeElementTemplateConfig.class)
public class TreeElementConfig extends ElementConfig<TreeElementTemplateConfig> {

    @Field(key = "checkedField", name = "是否被选择", type = FieldTypeEnum.TEXT, defaultValue = "false")
    private String checkedField;

    @Field(key = "textField", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String textField;

    @Field(key = "childrenField", name = "子树", type = FieldTypeEnum.TEXT)
    private String childrenField;

    @Field(key = "checkFirst", name = "是否默认选择第一项", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean checkFirst;

    @Field(key = "triggers", name = "树项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object triggers;

    public TreeElementConfig() {
        super(SystemElementPathTool.buildJsPath("tree"), SystemElementPathTool.buildCssPath("tree"),
                SystemElementPathTool.buildTemplatePath("tree"), TreeElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

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

    public Object getTriggers() {
        return triggers;
    }

    public void setTriggers(Object triggers) {
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
