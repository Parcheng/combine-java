package com.parch.combine.system.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetSystemComponents extends AbsGetComponents {

    public GetSystemComponents() {
        super("system", "系统", GetSystemComponents.class);
    }
}
