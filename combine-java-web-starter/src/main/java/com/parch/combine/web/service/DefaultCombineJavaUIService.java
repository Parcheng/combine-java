package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultCombineJavaUIService extends AbstractCombineJavaUIService {

    public DefaultCombineJavaUIService() {
        super("default_combine_ui_config.json");
    }
}
