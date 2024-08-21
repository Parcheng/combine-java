package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultCombineJavaService extends AbstractCombineWebService {

    public DefaultCombineJavaService() {
        super("default_combine_config.json");
    }

}
