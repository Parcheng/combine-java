package com.parch.combine.component.tool.crontab;

import com.parch.combine.core.base.InitConfig;

/**
 * 初始化配置类
 */
public class SystemCrontabInitConfig extends InitConfig {

    private Integer poolSize;

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }
}
