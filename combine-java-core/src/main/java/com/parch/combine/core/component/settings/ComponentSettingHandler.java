package com.parch.combine.core.component.settings;

import com.parch.combine.core.component.spi.AbsGetComponents;
import com.parch.combine.core.component.spi.IGetComponents;
import com.parch.combine.core.component.vo.ComponentClassInitVO;
import com.parch.combine.core.component.settings.config.ComponentClassifySetting;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 获取组件处理器
 */
public class ComponentSettingHandler {

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    public static List<ComponentClassInitVO> getComponents() {
        // 通过SPI加在所有组件
        List<ComponentClassInitVO> components = new ArrayList<>();
        ServiceLoader<AbsGetComponents> spiList = ServiceLoader.load(AbsGetComponents.class);
        for (IGetComponents service : spiList) {
            components.addAll(service.get());
        }

        return components;
    }

    /**
     * 获取组件设置集合
     *
     * @return 组件设置集合
     */
    public static List<ComponentClassifySetting> getSettings() {
        // 通过SPI加在所有组件
        List<ComponentClassifySetting> settings = new ArrayList<>();
        ServiceLoader<AbsGetComponents> spiList = ServiceLoader.load(AbsGetComponents.class);
        for (IGetComponents service : spiList) {
            settings.add(service.getSetting());
        }

        return settings;
    }
}
