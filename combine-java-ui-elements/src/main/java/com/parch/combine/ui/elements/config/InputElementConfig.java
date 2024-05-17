package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "input", name = "输入框元素", templateClass = InputElementTemplateConfig.class)
public class InputElementConfig extends ElementConfig<InputElementTemplateConfig> {

    @Field(key = "key", name = "输入框字段KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    private String key;

    @Field(key = "inputType", name = "输入框类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = ControlItemType.class)
    private ControlItemType inputType;

    @Field(key = "value", name = "输入框默认值", type = FieldTypeEnum.TEXT)
    private String value;

    @Field(key = "afterText", name = "前置插件文本", type = FieldTypeEnum.TEXT)
    private String afterText;

    @Field(key = "beforeText", name = "后置插件文本", type = FieldTypeEnum.TEXT)
    private String beforeText;

    public InputElementConfig() {
        super(SystemElementPathTool.buildJsPath("input"), SystemElementPathTool.buildTemplatePath("input"), InputElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ControlItemType getInputType() {
        return inputType;
    }

    public void setInputType(ControlItemType inputType) {
        this.inputType = inputType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public enum ControlItemType implements IOptionSetting {
        TEXT("文本", true),
        FILE("文件", true),
        PASSWORD("密码", true),
        NUMBER("数字", true);

        private String name;
        private boolean isValid;

        ControlItemType(String name, boolean isValid) {
            this.name = name;
            this.isValid = isValid;
        }

        public static ControlItemType get(String name) {
            if (CheckEmptyUtil.isEmpty(name)) {
                return TEXT;
            }
            for (ControlItemType value : ControlItemType.values()) {
                if (value.toString().equals(name.toUpperCase())) {
                    return value;
                }
            }
            return TEXT;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public boolean isValid() {
            return this.isValid;
        }
    }

    public String getAfterText() {
        return afterText;
    }

    public void setAfterText(String afterText) {
        this.afterText = afterText;
    }

    public String getBeforeText() {
        return beforeText;
    }

    public void setBeforeText(String beforeText) {
        this.beforeText = beforeText;
    }
}
