package com.parch.combine.component.web.elements.settings;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

/**
 * 页面元素设置
 */
public class InputSettings extends BaseSettings{

    private String key;

    private ControlItemType type;

    private String value;

    private String afterText;

    private String beforeText;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ControlItemType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = ControlItemType.get(type);
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
        public String getDesc() {
            return null;
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
