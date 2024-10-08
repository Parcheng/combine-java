package com.parch.combine.web.service;

import com.parch.combine.ui.BaseCombineJavaUIFunction;
import org.springframework.stereotype.Service;

@Service
public class CombineJavaUIApiService extends BaseCombineJavaUIFunction {

    public CombineJavaUIApiService() {
        super("combine_ui_api_business.json");
    }
}
