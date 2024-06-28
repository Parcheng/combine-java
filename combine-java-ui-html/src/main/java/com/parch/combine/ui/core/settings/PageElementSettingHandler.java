package com.parch.combine.ui.core.settings;

import com.parch.combine.ui.core.settings.config.PageElementClassifySetting;
import com.parch.combine.ui.core.spi.AbstractGetUIElements;
import com.parch.combine.ui.core.spi.IGetUIElements;
import com.parch.combine.ui.core.vo.PageElementClassInitVO;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PageElementSettingHandler {

    /**
     * 获取
     *
     * @return 组件集合
     */
    public static List<PageElementClassInitVO> getElements() {
        // 通过SPI加在所有组件
        List<PageElementClassInitVO> elements = new ArrayList<>();
        ServiceLoader<AbstractGetUIElements> spiList = ServiceLoader.load(AbstractGetUIElements.class);
        for (IGetUIElements service : spiList) {
            elements.addAll(service.get());
        }

        return elements;
    }

    /**
     * 获取设置集合
     *
     * @return 组件设置集合
     */
    public static List<PageElementClassifySetting> getSettings() {
        // 通过SPI加在所有组件
        List<PageElementClassifySetting> settings = new ArrayList<>();
        ServiceLoader<AbstractGetUIElements> spiList = ServiceLoader.load(AbstractGetUIElements.class);
        for (IGetUIElements service : spiList) {
            settings.add(service.getSetting());
        }

        return settings;
    }
}
