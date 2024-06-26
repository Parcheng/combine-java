package com.parch.combine.core.component.vo;

import com.parch.combine.core.component.base.AbstractComponent;

import java.util.List;

/**
 * 组件初始化VO
 */
public class ComponentClassInitVO {

    /**
     * 组件KEY
     */
    private String key;

    /**
     * 组件初始化异常信息
     */
    private List<String> errorMsg;

    /**
     * 组件类描述
     */
    private Class<? extends AbstractComponent<?, ?>> component;

    public ComponentClassInitVO(String key, Class<? extends AbstractComponent<?, ?>> component) {
        this.key = key;
        this.component = component;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class<? extends AbstractComponent<?, ?>> getComponent() {
        return component;
    }

    public void setComponent(Class<? extends AbstractComponent<?, ?>> component) {
        this.component = component;
    }

    public List<String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(List<String> errorMsg) {
        this.errorMsg = errorMsg;
    }
}
