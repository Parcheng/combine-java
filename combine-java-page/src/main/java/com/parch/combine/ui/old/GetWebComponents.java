package com.parch.combine.ui.old;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetWebComponents extends AbsGetComponents {

    public GetWebComponents() {
        super("com/parch/combine/ui", "页面", GetWebComponents.class);
    }
}
