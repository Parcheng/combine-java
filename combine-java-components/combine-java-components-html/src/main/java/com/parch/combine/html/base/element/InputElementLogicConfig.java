package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldSelect;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface InputElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {
        @Field(key = "key", name = "输入框字段KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        String key();

        @Field(key = "inputType", name = "输入框类型", type = FieldTypeEnum.SELECT, defaultValue = "TEXT")
        @FieldSelect(enumClass = ControlItemType.class)
        String inputType();

        @Field(key = "value", name = "输入框默认值", type = FieldTypeEnum.TEXT)
        String value();

        @Field(key = "afterText", name = "前置插件文本", type = FieldTypeEnum.TEXT)
        String afterText();

        @Field(key = "beforeText", name = "后置插件文本", type = FieldTypeEnum.TEXT)
        String beforeText();
    }

    enum ControlItemType implements IOptionSetting {
        TEXT("文本", true),
        FILE("文件", true),
        PASSWORD("密码", true),
        NUMBER("数字", true),
        DATE("日期", true),
        TIME("日期时间", true)
        ;

        String name;
        boolean isValid;

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
}
