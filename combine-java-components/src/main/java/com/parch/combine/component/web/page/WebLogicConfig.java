package com.parch.combine.component.web.page;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.component.web.ElementDomConfig;

import java.util.List;
import java.util.UUID;

/**
 * 逻辑配置类
 */
public class WebLogicConfig extends LogicConfig {

    private String lang;

    /**
     * 模板路径
     */
    private String tempPath;

    private List<WebPageTempConfig.HtmlMetaItem> metas;

    private List<WebPageTempConfig.HtmlLinkItem> links;

    private List<String> scripts;

    private List<String> elements;

    /**
     * 指定加载那些元素：默认全部
     */
    private List<String> loadElements;

    /**
     * 页面元素
     */
    public static class HtmlElement extends ElementDomConfig {

        public String showElement;

        public String getShowElement() {
            return showElement;
        }

        public void setShowElement(String showElement) {
            this.showElement = showElement;
        }

        @Override
        public String getId() {
            if (CheckEmptyUtil.isEmpty(super.getId())) {
                super.setId(UUID.randomUUID().toString());
            }
            return super.getId();
        }
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public List<WebPageTempConfig.HtmlMetaItem> getMetas() {
        return metas;
    }

    public void setMetas(List<WebPageTempConfig.HtmlMetaItem> metas) {
        this.metas = metas;
    }

    public List<WebPageTempConfig.HtmlLinkItem> getLinks() {
        return links;
    }

    public void setLinks(List<WebPageTempConfig.HtmlLinkItem> links) {
        this.links = links;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }

    public List<String> getLoadElements() {
        return loadElements;
    }

    public void setLoadElements(List<String> loadElements) {
        this.loadElements = loadElements;
    }
}
