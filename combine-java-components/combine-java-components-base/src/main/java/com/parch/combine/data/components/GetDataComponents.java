package com.parch.combine.data.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

/**
 * 获取数据处理组件加载器
 */
public class GetDataComponents extends AbstractGetComponents {

    public GetDataComponents() {
        super("data", "数据处理", GetDataComponents.class);
    }
}
