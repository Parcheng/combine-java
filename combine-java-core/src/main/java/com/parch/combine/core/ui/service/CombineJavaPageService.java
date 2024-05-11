package com.parch.combine.core.ui.service;

import com.parch.combine.core.ui.base.HtmlConfig;
import com.parch.combine.core.ui.builder.HtmlBuilder;
import com.parch.combine.core.ui.vo.CombineInitVO;
import com.parch.combine.core.ui.vo.GlobalConfigVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CombineJavaPageService implements ICombineJavaPageService {

    private GlobalConfigVO globalConfig;

    private Map<String, String> pageMap = new HashMap<>();

    public CombineJavaPageService() {}

    public GlobalConfigVO getGlobalConfig() {
        return globalConfig;
    }

    @Override
    public void register(String key, HtmlConfig config, Consumer<CombineInitVO> func) {
        HtmlBuilder htmlBuilder = new HtmlBuilder(config);
        List<String> msg = htmlBuilder.check();

        pageMap.put(key, htmlBuilder.build());
    }

    @Override
    public String getPage(String key) {
        return pageMap.get(key);
    }
}
