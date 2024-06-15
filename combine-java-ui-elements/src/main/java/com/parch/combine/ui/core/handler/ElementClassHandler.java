package com.parch.combine.ui.core.handler;

import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.PageElementSettingHandler;
import com.parch.combine.ui.core.vo.PageElementClassInitVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementClassHandler {

    private final static Map<String, Class<? extends ElementConfig<?>>> ELEMENT_CLASS_MAP = new HashMap<>(16);

    public static List<PageElementClassInitVO> init() {
        // 所有组件
        List<PageElementClassInitVO> elements = PageElementSettingHandler.getElements();

        // 注册所有组件
        for (PageElementClassInitVO vo : elements) {
            register(vo.getKey(), vo.getElementConfigClass());
        }

        return elements;
    }

    public synchronized static void register(String key, Class<? extends ElementConfig<?>> elementConfigClass) {
        ELEMENT_CLASS_MAP.put(key.toLowerCase(), elementConfigClass);
    }

    public static Class<? extends ElementConfig<?>> get(String key) {
        return ELEMENT_CLASS_MAP.get(key.toLowerCase());
    }
}
