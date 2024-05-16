package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.base.HtmlHeaderLinkConfig;
import com.parch.combine.core.ui.tools.HtmlBuildTool;
import com.parch.combine.core.ui.tools.UrlPathHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlHeaderLinkBuilder {

    private List<HtmlHeaderLinkConfig> templateConfigs;

    private List<HtmlHeaderLinkConfig> configs;

    public HtmlHeaderLinkBuilder(List<HtmlHeaderLinkConfig> templateConfigs, List<HtmlHeaderLinkConfig> configs) {
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
        linkProperties.put("rel", config.getRel());
        linkProperties.put("href", UrlPathHelper.replaceUrlFlag(config.getHref()));
        linkProperties.put("crossorigin", config.getCrossorigin());
        linkProperties.put("integrity", config.getIntegrity());
        linkProperties.put("media", config.getMedia());
        linkProperties.put("preload", config.getPreload());
        linkProperties.put("sizes", config.getSizes());
        linkList.add(HtmlBuildTool.build("link", null, linkProperties, true));
    }
}
