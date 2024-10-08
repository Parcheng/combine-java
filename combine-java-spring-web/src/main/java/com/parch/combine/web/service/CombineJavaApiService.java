package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class CombineJavaApiService extends AbstractCombineWebHandler {

    public CombineJavaApiService() {
        super("combine_api_business.json");
    }

}
