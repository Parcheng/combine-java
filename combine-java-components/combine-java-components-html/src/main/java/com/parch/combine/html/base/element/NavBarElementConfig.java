package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.NavBarElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.SubConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.base.trigger.Trigger;
import com.parch.combine.ui.core.base.trigger.TriggerCallFlowConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallFuncConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallUrlConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCustomConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadDataConfig;
import com.parch.combine.ui.core.base.trigger.TriggerSkipConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "nav_bar", name = "音频元素", templateClass = NavBarElementTemplateLogicConfig.class)
public class NavBarElementConfig extends ElementConfig<NavBarElementTemplateLogicConfig> {

    @Field(key = "brandText", name = "商标文本", type = FieldTypeEnum.TEXT)
    private String brandText;

    @Field(key = "brandImg", name = "商标LOGO图片地址", type = FieldTypeEnum.TEXT)
    private String brandImg;

    @Field(key = "defaultChecked", name = "默认选择项索引（从0开始）", type = FieldTypeEnum.NUMBER)
    private Integer defaultChecked;

    @Field(key = "nav", name = "导航项配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(NavSettings.class)
    private NavSettings nav;

    @Field(key = "defaultNavs", name = "导航默认项配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<NavData> defaultNavs;

    @Field(key = "buttons", name = "导航右栏操作配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(OptItemSettings.class)
    private List<OptItemSettings> opts;

    public NavBarElementConfig() {
        super(SystemElementPathTool.buildJsPath("nav_bar"), SystemElementPathTool.buildCssPath("nav_bar"),
                SystemElementPathTool.buildTemplatePath("nav_bar"), NavBarElementTemplateLogicConfig.class);
    }

    @SubConfig
    public static class OptItemSettings {

        @Field(key = "text", name = "按钮文本", type = FieldTypeEnum.OBJECT, isArray = true)
        private String text;

        @Field(key = "triggers", name = "按钮触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
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

//        @Field(key = "children", name = "默认项子项（子项配置项与“settings.defaultNavs”相同）", type = FieldTypeEnum.OBJECT, isArray = true)
//        private List<NavData> children;

        @Field(key = "triggers", name = "默认项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
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

        public Object getTriggers() {
            return triggers;
        }

        public void setTriggers(Object triggers) {
            this.triggers = triggers;
        }
    }

    @SubConfig
    public static class NavSettings {

        @Field(key = "text", name = "导航项文本", type = FieldTypeEnum.TEXT, isRequired = true)
        private String text;

//        @Field(key = "children", name = "导航项子项", type = FieldTypeEnum.TEXT)
//        private String children;

        @Field(key = "triggers", name = "导航项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
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
