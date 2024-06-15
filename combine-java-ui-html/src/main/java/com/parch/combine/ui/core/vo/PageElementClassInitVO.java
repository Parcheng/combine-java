package com.parch.combine.ui.core.vo;

import com.parch.combine.ui.core.base.element.ElementConfig;

/**
 * 初始化VO
 */
public class PageElementClassInitVO {

    /**
     * 组件KEY
     */
    private String key;

    /**
     * 组件类描述
     */
    private Class<? extends ElementConfig<?>> elementConfigClass;

    public PageElementClassInitVO(String key, Class<? extends ElementConfig<?>> elementConfigClass) {
        this.key = key;
        this.elementConfigClass = elementConfigClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class<? extends ElementConfig<?>> getElementConfigClass() {
        return elementConfigClass;
    }

    public void setElementConfigClass(Class<? extends ElementConfig<?>> elementConfigClass) {
        this.elementConfigClass = elementConfigClass;
    }
}
