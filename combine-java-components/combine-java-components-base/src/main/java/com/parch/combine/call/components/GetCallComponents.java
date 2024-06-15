package com.parch.combine.call.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 组件加载器
 */
public class GetCallComponents extends AbsGetComponents {

    public GetCallComponents() {
        super("call", "调用", GetCallComponents.class);
    }
}
