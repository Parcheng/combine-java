package com.parch.combine.gitlab.base.project.member;

import com.parch.combine.core.component.settings.config.IOptionSetting;

public enum ProjectMemberAccessLevelEnum implements IOptionSetting {

    INVALID("无效", true),
    MINIMAL_ACCESS("最小访问（MINIMAL_ACCESS）", true),
    GUEST("游客（GUEST）", true),
    REPORTER("访客（REPORTER）", true),
    DEVELOPER("开发人员（DEVELOPER）", true),
    MAINTAINER("维护人员（MAINTAINER）", true),
    OWNER("负责人（OWNER）", true),
    ADMIN("管理员（ADMIN）", true),
    NONE("未知", false);

    private final String name;
    private final boolean isValid;

    ProjectMemberAccessLevelEnum(String name, boolean isValid) {
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
