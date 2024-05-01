package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultCombineWebService extends AbstractCombineWebService {

    public DefaultCombineWebService() {
        super("default_combine_web_config.json");
    }
}
