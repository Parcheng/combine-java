package com.parch.combine.core.ui.vo;

import com.parch.combine.core.ui.base.element.ElementConfig;

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
    private Class<? extends ElementConfig<?, ?>> elementConfigClass;

    public PageElementInitVO(String key, Class<? extends ElementConfig<?, ?>> elementConfigClass) {
        this.key = key;
        this.elementConfigClass = elementConfigClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class<? extends ElementConfig<?, ?>> getElementConfigClass() {
        return elementConfigClass;
    }

    public void setElementConfigClass(Class<? extends ElementConfig<?, ?>> elementConfigClass) {
        this.elementConfigClass = elementConfigClass;
    }
}
