package com.parch.combine.tool.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取数据处理组件加载器
 */
public class GetToolComponents extends AbsGetComponents {

    public GetToolComponents() {
        super("tool", "工具", GetToolComponents.class);
    }
}
