package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.builder.config.HtmlHeaderMetaConfig;
import com.parch.combine.core.ui.tools.HtmlBuileHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlHeaderMetaBuilder {

    private HtmlHeaderMetaConfig templateConfig;

    private List<HtmlHeaderMetaConfig> configs;

    public HtmlHeaderMetaBuilder(HtmlHeaderMetaConfig templateConfig, List<HtmlHeaderMetaConfig> configs) {
        this.templateConfig = templateConfig;
        this.configs = configs;
    }

    public List<String> build() {
        List<String> mates = new ArrayList<>();
        if (templateConfig != null) {
            buildItem(mates, templateConfig);
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
        metaProperties.put("name", config.getName());
        metaProperties.put("content", config.getContent());
        mates.add(HtmlBuileHelper.build("meta", null, metaProperties, true));
    }

    public List<String> check() {
        List<String> msg = new ArrayList<>();
        if (templateConfig != null) {
            checkItem(msg, templateConfig, "[MATE]-模板-");
        }

        if (CheckEmptyUtil.isNotEmpty(configs)) {
            for (int i = 0; i < configs.size(); i++) {
                HtmlHeaderMetaConfig config = configs.get(i);
                checkItem(msg, config, "[MATE]-第<" + (i + 1) + ">条-");
            }
        }

        return msg;
    }

    private void checkItem(List<String> msg, HtmlHeaderMetaConfig config, String baseMsg) {
        if (CheckEmptyUtil.isEmpty(config.getName())) {
            msg.add(baseMsg + "name属性不能为空");
        }
        if (CheckEmptyUtil.isEmpty(config.getContent())) {
            msg.add(baseMsg + "content属性不能为空");
        }
    }
}
