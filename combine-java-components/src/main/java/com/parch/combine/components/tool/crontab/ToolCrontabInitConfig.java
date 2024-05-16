package com.parch.combine.components.tool.crontab;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class ToolCrontabInitConfig extends InitConfig {

    @Field(key = "poolSize", name = "定时任务线程池大小", type = FieldTypeEnum.NUMBER, defaultValue = "10")
    private Integer poolSize;

    @Override
    public void init() {
        if (getPoolSize() == null) {
            setPoolSize(10);
        }
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }
}
