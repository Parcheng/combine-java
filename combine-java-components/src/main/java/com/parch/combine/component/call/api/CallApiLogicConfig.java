package com.parch.combine.component.call.api;

import com.parch.combine.component.call.CallLogicConfig;

import java.util.Map;

/**
 * 逻辑配置类
 */
public class CallApiLogicConfig extends CallLogicConfig {

    /**
     * 请求方式
     */
    private String mode;

    /**
     * 重试次数
     */
    private Integer retry;

    /**
     * 超时时间
     */
    private Integer timeout;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
