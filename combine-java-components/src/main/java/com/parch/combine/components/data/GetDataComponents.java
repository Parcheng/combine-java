package com.parch.combine.components.data;

import com.parch.combine.core.component.settings.spi.AbsGetComponents;

/**
 * 获取数据处理组件加载器
 */
public class GetDataComponents extends AbsGetComponents {

    public GetDataComponents() {
        super("data", "数据处理", GetDataComponents.class);
    }
}
