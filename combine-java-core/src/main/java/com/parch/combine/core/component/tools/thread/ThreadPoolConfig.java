package com.parch.combine.core.component.tools.thread;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public class ThreadPoolConfig {

    @Field(key = "key", name = "线程池KEY", type = FieldTypeEnum.TEXT, defaultValue = "global")
    @FieldDesc("用于指定使用的线程池，默认使用全局线程池")
    private String key;

    @Field(key = "corePoolSize", name = "线程池核心线程数", type = FieldTypeEnum.NUMBER, defaultValue = "5")
    private Integer corePoolSize;

    @Field(key = "poolSize", name = "线程池最大线程数", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    private Integer maxPoolSize;

    @Field(key = "keepAliveTime", name = "非核心线程有效时间（毫秒）", type = FieldTypeEnum.NUMBER, defaultValue = "5000")
    private Long keepAliveTime;

    @Field(key = "queueCapacity", name = "缓存队列容量", type = FieldTypeEnum.NUMBER, defaultValue = "20")
    private Integer queueCapacity;

    public Integer getCorePoolSize() {
        return corePoolSize == null ? 5 : corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize == null ? 20 : maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime == null ? 5000L : keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getQueueCapacity() {
        return queueCapacity == null ? 20 : queueCapacity;
    }

    public void setQueueCapacity(Integer queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public String getKey() {
        return CheckEmptyUtil.isEmpty(this.key) ? "global" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
