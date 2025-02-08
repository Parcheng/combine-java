package com.parch.combine.gitlab.base.merge;

import com.parch.combine.core.component.settings.config.IOptionSetting;

public enum MergeRequestStateEnum implements IOptionSetting {

    OPENED("打开", true),
    CLOSED("关闭", true),
    LOCKED("锁定", true),
    MERGED("已合并", true),
    ALL("全部", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    MergeRequestStateEnum(String name, boolean isValid) {
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
