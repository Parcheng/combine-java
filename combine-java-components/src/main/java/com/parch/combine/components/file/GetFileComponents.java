package com.parch.combine.components.file;

import com.parch.combine.core.component.settings.spi.AbsGetComponents;

/**
 * 获取文件组件加载器
 */
public class GetFileComponents extends AbsGetComponents {

    public GetFileComponents() {
        super("file", "文件", GetFileComponents.class);
    }
}
