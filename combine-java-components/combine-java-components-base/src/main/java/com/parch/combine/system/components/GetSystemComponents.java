package com.parch.combine.system.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetSystemComponents extends AbstractGetComponents {

    public GetSystemComponents() {
        super("system", "系统", GetSystemComponents.class);
    }
}
