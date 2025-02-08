package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.component.settings.config.IOptionSetting;

public enum ExchangeTypeEnum implements IOptionSetting {

    DIRECT("direct"),
    FANOUT("fanout"),
    TOPIC("topic"),
    HEADERS("headers");

    private final String type;

    ExchangeTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String getKey() {
        return type;
    }

    @Override
    public String getName() {
        return type;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}

