package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "banner", name = "横幅元素", templateClass = BannerElementTemplateConfig.class)
public class BannerElementConfig extends ElementConfig<BannerElementTemplateConfig> {

    @Field(key = "type", name = "内容类型（img | text）", type = FieldTypeEnum.TEXT, defaultValue = "text")
    private String bannerType;

    @Field(key = "hasClose", name = "是否允许关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean hasClose;

    @Field(key = "content", name = "内容", type = FieldTypeEnum.TEXT)
    private String content;

    public BannerElementConfig() {
        super(SystemElementPathTool.buildJsPath("banner"), SystemElementPathTool.buildCssPath("banner"),
                SystemElementPathTool.buildTemplatePath("banner"), BannerElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {
        if (CheckEmptyUtil.isEmpty(bannerType)) {
            bannerType = "text";
        }
        bannerType = bannerType.toLowerCase();
    }

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHasClose() {
        return hasClose;
    }

    public void setHasClose(Boolean hasClose) {
        this.hasClose = hasClose;
    }
}
