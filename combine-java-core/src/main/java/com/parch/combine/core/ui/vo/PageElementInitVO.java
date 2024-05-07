package com.parch.combine.core.ui.vo;

import com.parch.combine.core.component.base.AbsComponent;

/**
 * 初始化VO
 */
public class PageElementInitVO {

    /**
     * 组件KEY
     */
    private String key;

    /**
     * 组件类描述
     */
    private Class<? extends AbsComponent<?, ?>> component;

    public PageElementInitVO(String key, Class<? extends AbsComponent<?, ?>> component) {
        this.key = key;
        this.component = component;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class<? extends AbsComponent<?, ?>> getComponent() {
        return component;
    }

    public void setComponent(Class<? extends AbsComponent<?, ?>> component) {
        this.component = component;
    }
}
