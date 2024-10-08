package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.html.base.page.config.HtmlHeaderLinkConfig;
import com.parch.combine.html.common.tool.HtmlBuildTool;
import com.parch.combine.html.common.tool.UrlPathHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlHeaderLinkBuilder {

    private HtmlHeaderLinkConfig[] templateConfigs;

    private HtmlHeaderLinkConfig[] configs;

    public HtmlHeaderLinkBuilder(HtmlHeaderLinkConfig[] templateConfigs, HtmlHeaderLinkConfig[] configs) {
        this.templateConfigs = templateConfigs;
        this.configs = configs;
    }

    public List<String> build() {
        List<String> linkList = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(templateConfigs)) {
            for (HtmlHeaderLinkConfig templateConfig : templateConfigs) {
                buildItem(linkList, templateConfig);
            }
        }

        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (HtmlHeaderLinkConfig config : configs) {
                buildItem(linkList, config);
            }
        }

        return linkList;
    }

    public void buildItem(List<String> linkList, HtmlHeaderLinkConfig config) {
        Map<String, String> linkProperties = new HashMap<>();
        linkProperties.put("rel", config.rel());
        linkProperties.put("href", UrlPathHelper.replaceUrlFlag(config.href()));
        linkProperties.put("crossorigin", config.crossorigin());
        linkProperties.put("integrity", config.integrity());
        linkProperties.put("media", config.media());
        linkProperties.put("preload", config.preload());
        linkProperties.put("sizes", config.sizes());
        linkList.add(HtmlBuildTool.build("link", null, linkProperties, true));
    }
}
