package com.parch.combine.components.web;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetWebComponents extends AbsGetComponents {

    public GetWebComponents() {
        super("web", "页面交互", GetWebComponents.class);
    }
}
