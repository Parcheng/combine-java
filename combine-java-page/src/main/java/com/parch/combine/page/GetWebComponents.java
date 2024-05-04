package com.parch.combine.page;

import com.parch.combine.core.settings.spi.AbsGetComponents;

/**
 * 获取页面组件实现类
 */
public class GetWebComponents extends AbsGetComponents {

    public GetWebComponents() {
        super("com/parch/combine/page", "页面", GetWebComponents.class);
    }
}
