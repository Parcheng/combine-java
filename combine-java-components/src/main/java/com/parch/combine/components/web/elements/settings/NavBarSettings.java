package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class NavBarSettings extends BaseSettings{

    @ComponentField(key = "brandText", name = "商标文本", type = FieldTypeEnum.TEXT)
    private String brandText;

    @ComponentField(key = "brandImg", name = "商标LOGO图片地址", type = FieldTypeEnum.TEXT)
    private String brandImg;

    @ComponentField(key = "defaultChecked", name = "默认选择项索引（从0开始）", type = FieldTypeEnum.NUMBER)
    private Integer defaultChecked;

    @ComponentField(key = "nav", name = "导航项配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @ComponentFieldObject(type = NavSettings.class)
    private NavSettings nav;

    @ComponentField(key = "defaultNavs", name = "导航默认项配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<NavData> defaultNavs;

    @ComponentField(key = "buttons", name = "导航右栏按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldObject(type = ButtonSettings.ButtonItemSettings.class)
    private List<ButtonSettings.ButtonItemSettings> buttons;

    public static class NavData {

        @ComponentField(key = "text", name = "默认项文本", type = FieldTypeEnum.TEXT)
        private String text;

        @ComponentField(key = "index", name = "默认项位置索引（从0开始）", type = FieldTypeEnum.NUMBER)
        private Integer index;

        @ComponentField(key = "children", name = "默认项子项（子项配置项与“settings.defaultNavs”相同）", type = FieldTypeEnum.OBJECT, isArray = true)
        private List<NavData> children;

        @ComponentField(key = "triggers", name = "默认项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @ComponentFieldRef(key = WebSettingCanstant.TRIGGER_KEY)
        private List<TriggerEntity> triggers;

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

        public List<TriggerEntity> getTriggers() {
            return triggers;
        }

        public void setTriggers(List<TriggerEntity> triggers) {
            this.triggers = triggers;
        }
    }

    public static class NavSettings {

        @ComponentField(key = "text", name = "导航项文本", type = FieldTypeEnum.OBJECT, isRequired = true)
        private String text;

        @ComponentField(key = "children", name = "导航项子项", type = FieldTypeEnum.OBJECT)
        private String children;

        @ComponentField(key = "triggers", name = "导航项触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
        @ComponentFieldRef(key = WebSettingCanstant.TRIGGER_KEY)
        private List<TriggerEntity> triggers;

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

        public List<TriggerEntity> getTriggers() {
            return triggers;
        }

        public void setTriggers(List<TriggerEntity> triggers) {
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

    public List<ButtonSettings.ButtonItemSettings> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonSettings.ButtonItemSettings> buttons) {
        this.buttons = buttons;
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
