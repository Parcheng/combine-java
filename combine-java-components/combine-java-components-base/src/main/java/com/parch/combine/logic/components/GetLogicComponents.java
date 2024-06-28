package com.parch.combine.logic.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

/**
 * 获取逻辑组件实现类
 */
public class GetLogicComponents extends AbstractGetComponents {

    public GetLogicComponents() {
        super("logic", "逻辑处理", GetLogicComponents.class);
    }
}
