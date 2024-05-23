package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.SubConfig;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "nav_bar", name = "音频元素", templateClass = AudioElementTemplateConfig.class)
public class NavBarElementConfig extends ElementConfig<NavBarElementTemplateConfig> {

    @Field(key = "brandText", name = "商标文本", type = FieldTypeEnum.TEXT)
    private String brandText;

    @Field(key = "brandImg", name = "商标LOGO图片地址", type = FieldTypeEnum.TEXT)
    private String brandImg;

    @Field(key = "defaultChecked", name = "默认选择项索引（从0开始）", type = FieldTypeEnum.NUMBER)
    private Integer defaultChecked;

    @Field(key = "nav", name = "导航项配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(type = NavSettings.class)
    private NavSettings nav;

    @Field(key = "defaultNavs", name = "导航默认项配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<NavData> defaultNavs;

    @Field(key = "buttons", name = "导航右栏操作配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = OptItemSettings.class)
    private List<OptItemSettings> opts;

    public NavBarElementConfig() {
        super(SystemElementPathTool.buildJsPath("nav_bar"), SystemElementPathTool.buildCssPath("nav_bar"),
                SystemElementPathTool.buildTemplatePath("nav_bar"), NavBarElementTemplateConfig.class);
    }

    @SubConfig
    public static class OptItemSettings {

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.OBJECT, isArray = true)
        private String text;

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
        @Trigger
        private Object triggers;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Object getTriggers() {
            return triggers;
        }

        public void setTriggers(Object triggers) {
            this.triggers = triggers;
        }
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    @SubConfig
    public static class NavData {

        @Field(key = "text", name = "默认项文本", type = FieldTypeEnum.TEXT)
        private String text;

        @Field(key = "index", name = "默认项位置索引（从0开始）", type = FieldTypeEnum.NUMBER)
        private Integer index;

        @Field(key = "children", name = "默认项子项（子项配置项与“settings.defaultNavs”相同）", type = FieldTypeEnum.OBJECT, isArray = true)
        private List<NavData> children;

        @Field(key = "triggers", name = "默认项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
        @Trigger
        private Object triggers;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public List<NavData> getChildren() {
            return children;
        }

        public void setChildren(List<NavData> children) {
            this.children = children;
        }

        public Object getTriggers() {
            return triggers;
        }

        public void setTriggers(Object triggers) {
            this.triggers = triggers;
        }
    }

    @SubConfig
    public static class NavSettings {

        @Field(key = "text", name = "导航项文本", type = FieldTypeEnum.OBJECT, isRequired = true)
        private String text;

        @Field(key = "children", name = "导航项子项", type = FieldTypeEnum.OBJECT)
        private String children;

        @Field(key = "triggers", name = "导航项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
        @Trigger
        private Object triggers;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getChildren() {
            return children;
        }

        public void setChildren(String children) {
            this.children = children;
        }

        public Object getTriggers() {
            return triggers;
        }

        public void setTriggers(Object triggers) {
            this.triggers = triggers;
        }
    }

    public String getBrandText() {
        return brandText;
    }

    public void setBrandText(String brandText) {
        this.brandText = brandText;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public NavSettings getNav() {
        return nav;
    }

    public void setNav(NavSettings nav) {
        this.nav = nav;
    }

    public List<OptItemSettings> getOpts() {
        return opts;
    }

    public void setOpts(List<OptItemSettings> opts) {
        this.opts = opts;
    }

    public List<NavData> getDefaultNavs() {
        return defaultNavs;
    }

    public void setDefaultNavs(List<NavData> defaultNavs) {
        this.defaultNavs = defaultNavs;
    }

    public Integer getDefaultChecked() {
        return defaultChecked;
    }

    public void setDefaultChecked(Integer defaultChecked) {
        this.defaultChecked = defaultChecked;
    }
}
