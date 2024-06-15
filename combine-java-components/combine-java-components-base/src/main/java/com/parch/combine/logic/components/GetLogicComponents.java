package com.parch.combine.logic.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取逻辑组件实现类
 */
public class GetLogicComponents extends AbsGetComponents {

    public GetLogicComponents() {
        super("logic", "逻辑处理", GetLogicComponents.class);
    }
}