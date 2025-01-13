package com.parch.combine.gitlab.base.project;

import com.parch.combine.core.common.settings.config.IOptionSetting;

/**
 * 项目可见性
 *
 * @author parch
 * @Date 2025/1/13
 */
public enum GitlabProjectVisibilityEnum implements IOptionSetting {

    PUBLIC("公开", true),
    PRIVATE("私有", true),
    INTERNAL("内部", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    GitlabProjectVisibilityEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
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
