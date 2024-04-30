package com.parch.combine.component.access.rocketmq;

import com.parch.combine.core.base.InitConfig;


public class RocketMQInitConfig extends InitConfig {

    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
