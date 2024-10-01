package com.parch.combine.web.service;

import com.parch.combine.ui.BaseCombineJavaUIFunction;
import org.springframework.stereotype.Service;

@Service
public class DefaultCombineJavaUIHandler extends BaseCombineJavaUIFunction {

    public DefaultCombineJavaUIHandler() {
        super("default_combine_ui_config.json");
    }
}
