package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.builder.config.HtmlHeaderLinkConfig;
import com.parch.combine.core.ui.builder.config.HtmlHeaderMetaConfig;
import com.parch.combine.core.ui.tools.HtmlBuileHelper;
import com.parch.combine.core.ui.tools.UrlPathHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlHeaderLinkBuilder {

    private HtmlHeaderLinkConfig templateConfig;

    private List<HtmlHeaderLinkConfig> configs;

    public HtmlHeaderLinkBuilder(HtmlHeaderLinkConfig templateConfig, List<HtmlHeaderLinkConfig> configs) {
        this.templateConfig = templateConfig;
        this.configs = configs;
    }

    public List<String> build() {
        List<String> linkList = new ArrayList<>();
        if (templateConfig != null) {
            buildItem(linkList, templateConfig);
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
        linkList.add(HtmlBuileHelper.build("link", null, linkProperties, true));
    }

    public List<String> check() {
        List<String> msg = new ArrayList<>();
        if (templateConfig != null) {
            checkItem(msg, templateConfig, "[LINK]-模板-");
        }

        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (int i = 0; i < configs.size(); i++) {
                HtmlHeaderLinkConfig config = configs.get(i);
                checkItem(msg, config, "[LINK]-第<" + (i + 1) + ">条-");
            }
        }

        return msg;
    }

    private void checkItem(List<String> msg, HtmlHeaderLinkConfig config, String baseMsg) {
        if (CheckEmptyUtil.isEmpty(config.getRel())) {
            msg.add(baseMsg + "rel属性不能为空");
        }
        if (CheckEmptyUtil.isEmpty(config.getHref())) {
            msg.add(baseMsg + "href属性不能为空");
        }
    }
}
