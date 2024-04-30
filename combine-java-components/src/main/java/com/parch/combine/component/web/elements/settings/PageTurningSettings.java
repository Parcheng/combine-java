package com.parch.combine.component.web.elements.settings;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

/**
 * 页面元素设置
 */
public class PageTurningSettings extends BaseSettings{

    private String currPage;

    private String maxPage;

    private TriggerEntity trigger;

    public String getCurrPage() {
        return currPage;
    }

    public void setCurrPage(String currPage) {
        this.currPage = currPage;
    }

    public String getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    public TriggerEntity getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerEntity trigger) {
        this.trigger = trigger;
    }
}
