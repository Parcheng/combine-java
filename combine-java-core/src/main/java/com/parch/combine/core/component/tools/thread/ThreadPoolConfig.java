package com.parch.combine.core.component.tools.thread;

import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;

public class ThreadPoolConfig implements IInit {

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

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(this.key)) {
            this.key = "global";
        }
        if (this.getCorePoolSize() == null) {
            this.setCorePoolSize(5);
        }
        if (this.getMaxPoolSize() == null) {
            this.setMaxPoolSize(20);
        }
        if (this.getKeepAliveTime() == null) {
            this.setKeepAliveTime(5000L);
        }
        if (this.getQueueCapacity() == null) {
            this.setQueueCapacity(20);
        }
        ThreadPoolTool.register(this);
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
