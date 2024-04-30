package com.parch.combine.component.web.page;


import com.parch.combine.component.web.helper.DomConfig;
import com.parch.combine.core.base.LogicConfig;
import java.util.List;

/**
 * 页面模板通用配置类
 */
public class WebPageTempConfig extends LogicConfig {

    private String lang;

    /**
     * 模板路径
     */
    private String tempPath;

    private List<HtmlMetaItem> metas;

    private List<HtmlLinkItem> links;

    private List<String> scripts;

    private List<HtmlElementConfig<DomConfig>> configs;

    /**
     * mate配置
     */
    public static class HtmlMetaItem {
        /**
         * 名称
         */
        String name;

        /**
         * 内容
         */
        String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * mate配置
     */
    public static class HtmlLinkItem {

        /**
         * 定义当前文档与链接资源之间的关系
         */
        String rel;

        /**
         * 属性用于指定链接资源的URL
         */
        String href;

        /**
         * 用于指定链接资源的MIME类型
         */
        String type;

        /**
         * 允许指定样式表适用于哪些媒体类型
         */
        String media;

        /**
         * 使用 link 标签链接到多个尺寸的图标时，可以使用 sizes 属性指定图标的大小
         */
        String sizes;

        /**
         * 用于确保外部资源的完整性，可以与 crossorigin 属性一起使用。
         * 通过为资源提供一个基于内容的哈希值（如SHA-256），可以确保资源未被篡改
         */
        String integrity;

        /**
         * 当链接到跨域资源时，可以使用 crossorigin 属性指定资源的CORS（跨源资源共享）设置
         */
        String crossorigin;

        /**
         * 可以用于提前加载重要的资源，例如字体、图片或脚本
         */
        String preload;

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getSizes() {
            return sizes;
        }

        public void setSizes(String sizes) {
            this.sizes = sizes;
        }

        public String getIntegrity() {
            return integrity;
        }

        public void setIntegrity(String integrity) {
            this.integrity = integrity;
        }

        public String getCrossorigin() {
            return crossorigin;
        }

        public void setCrossorigin(String crossorigin) {
            this.crossorigin = crossorigin;
        }

        public String getPreload() {
            return preload;
        }

        public void setPreload(String preload) {
            this.preload = preload;
        }
    }

    public static class HtmlElementConfig<T extends DomConfig> {

        private String key;

        private T config;

        public HtmlElementConfig() {}

        public HtmlElementConfig(String key, T config) {
            this.key = key;
            this.config = config;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public T getConfig() {
            return config;
        }

        public void setConfig(T config) {
            this.config = config;
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

    public List<HtmlMetaItem> getMetas() {
        return metas;
    }

    public void setMetas(List<HtmlMetaItem> metas) {
        this.metas = metas;
    }

    public List<HtmlLinkItem> getLinks() {
        return links;
    }

    public void setLinks(List<HtmlLinkItem> links) {
        this.links = links;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public List<HtmlElementConfig<DomConfig>> getConfigs() {
        return configs;
    }

    public void setConfigs(List<HtmlElementConfig<DomConfig>> configs) {
        this.configs = configs;
    }
}
