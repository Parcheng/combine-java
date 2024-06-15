package com.parch.combine.mail.base.helper;

import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;

/**
 * 邮件内容类型
 */
public enum MailContentTypeEnum implements IOptionSetting {

    FILE("文件", true),
    IMAGE("图片", true),
    TEXT("文本", true);

    private String name;
    private boolean isValid;

    MailContentTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static MailContentTypeEnum get(String sqlType) {
        if (CheckEmptyUtil.isEmpty(sqlType)) {
            return TEXT;
        }
        for (MailContentTypeEnum value : MailContentTypeEnum.values()) {
            if (value.toString().equals(sqlType.toUpperCase())) {
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
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
