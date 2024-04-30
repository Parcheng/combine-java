package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

import java.util.List;

/**
 * 页面元素设置
 */
public class NavBarSettings extends BaseSettings{

    private String brandText;

    private String brandImg;

    private Integer defaultChecked;

    private NavSettings nav;

    private List<NavData> defaultNavs;

    private List<ButtonSettings.ButtonItemSettings> buttons;

    public static class NavData {
        private String text;

        private Integer index;

        private List<NavData> children;

        private TriggerEntity trigger;

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

        public TriggerEntity getTrigger() {
            return trigger;
        }

        public void setTrigger(TriggerEntity trigger) {
            this.trigger = trigger;
        }
    }

    public static class NavSettings {

        private String text;

        private String children;

        private TriggerEntity trigger;

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

        public TriggerEntity getTrigger() {
            return trigger;
        }

        public void setTrigger(TriggerEntity trigger) {
            this.trigger = trigger;
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
