package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultCombineJavaHandler extends AbstractCombineWebHandler {

    public DefaultCombineJavaHandler() {
        super("default_combine_config.json");
    }

}
