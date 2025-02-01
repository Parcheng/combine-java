package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.html.base.page.config.HtmlHeaderMetaConfig;
import com.parch.combine.html.common.tool.HtmlBuildTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlHeaderMetaBuilder {

    private HtmlHeaderMetaConfig[] templateConfigs;

    private HtmlHeaderMetaConfig[] configs;

    public HtmlHeaderMetaBuilder(HtmlHeaderMetaConfig[] templateConfigs, HtmlHeaderMetaConfig[] configs) {
        this.templateConfigs = templateConfigs;
        this.configs = configs;
    }

    public List<String> build() {
        List<String> mates = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(templateConfigs)) {
            for (HtmlHeaderMetaConfig templateConfig : templateConfigs) {
                buildItem(mates, templateConfig);
            }
        }

        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (HtmlHeaderMetaConfig config : configs) {
                buildItem(mates, config);
            }
        }

        return mates;
    }

    public void buildItem(List<String> mates, HtmlHeaderMetaConfig config) {
        Map<String, String> metaProperties = new HashMap<>(2);
        metaProperties.put("name", config.name());
        metaProperties.put("content", config.content());
        mates.add(HtmlBuildTool.build("meta", null, metaProperties, true));
    }
}
