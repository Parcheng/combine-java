package com.parch.combine.components.access;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取DB组件实现类
 */
public class GetDBComponents extends AbsGetComponents {

    public GetDBComponents() {
        super("access", "外部接入软件组件", GetDBComponents.class);
    }
}
