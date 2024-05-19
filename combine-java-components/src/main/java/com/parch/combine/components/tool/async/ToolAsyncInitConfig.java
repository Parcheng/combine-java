package com.parch.combine.components.tool.async;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.Invalid;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.InitConfig;

public class ToolAsyncInitConfig extends InitConfig {

    @Field(key = "corePoolSize", name = "线程池核心线程数", type = FieldTypeEnum.NUMBER, defaultValue = "5")
    private Integer corePoolSize;

    @Field(key = "poolSize", name = "线程池最大线程数", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    private Integer maxPoolSize;

    @Field(key = "keepAliveTime", name = "非核心线程有效时间（毫秒）", type = FieldTypeEnum.NUMBER, defaultValue = "5000")
    private Long keepAliveTime;

    @Field(key = "queueCapacity", name = "缓存队列容量", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    private Integer queueCapacity;

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
