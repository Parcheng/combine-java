package com.parch.combine.components.system;

import com.parch.combine.core.component.settings.spi.AbsGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetSystemComponents extends AbsGetComponents {

    public GetSystemComponents() {
        super("system", "系统", GetSystemComponents.class);
    }
}
