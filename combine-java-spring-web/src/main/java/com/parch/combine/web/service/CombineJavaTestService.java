package com.parch.combine.web.service;

import org.springframework.stereotype.Service;

@Service
public class CombineJavaTestService extends AbstractCombineWebHandler {

    public CombineJavaTestService() {
        super("combine_test_business.json");
    }

}
