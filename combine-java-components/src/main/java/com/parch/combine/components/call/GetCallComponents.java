package com.parch.combine.components.call;

import com.parch.combine.core.component.settings.spi.AbsGetComponents;

/**
 * 组件加载器
 */
public class GetCallComponents extends AbsGetComponents {

    public GetCallComponents() {
        super("call", "调用", GetCallComponents.class);
    }
}
