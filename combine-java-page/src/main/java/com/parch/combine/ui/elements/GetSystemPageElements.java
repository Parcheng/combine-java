package com.parch.combine.ui.elements;

import com.parch.combine.ui.settings.spi.AbsGetPageElements;

/**
 * 组件加载器
 */
public class GetSystemPageElements extends AbsGetPageElements {

    public GetSystemPageElements() {
        super("system，", "系统内置", GetSystemPageElements.class);
    }
}
