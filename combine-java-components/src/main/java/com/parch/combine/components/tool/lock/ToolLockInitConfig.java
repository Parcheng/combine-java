package com.parch.combine.components.tool.lock;

import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class ToolLockInitConfig extends InitConfig {

    @ComponentField(key = "autoUnLock", name = "流程执行完成是否自动释放锁", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @ComponentFieldEg(eg = "false", desc = "流程全部组件主席执行完毕后不自动解锁")
    private Boolean autoUnLock;

    @ComponentField(key = "fair", name = "是否使用公平锁（非公平锁，性能会好一点）", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean fair;

    @Override
    public void init() {
        if (getAutoUnLock() == null) {
            setAutoUnLock(true);
        }
        if (getFair() == null) {
            setFair(false);
        }
    }

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
