package com.parch.combine.component.tool.lock;

import com.parch.combine.core.base.InitConfig;

/**
 * 初始化配置类
 */
public class ToolLockInitConfig extends InitConfig {

    private Boolean autoUnLock;

    private Boolean fair;

    public Boolean getAutoUnLock() {
        return autoUnLock;
    }

    public void setAutoUnLock(Boolean autoUnLock) {
        this.autoUnLock = autoUnLock;
    }

    public Boolean getFair() {
        return fair;
    }

    public void setFair(Boolean fair) {
        this.fair = fair;
    }
}
