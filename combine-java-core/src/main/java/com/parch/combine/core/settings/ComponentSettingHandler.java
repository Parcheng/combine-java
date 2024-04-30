package com.parch.combine.core.settings;

import com.parch.combine.core.settings.config.ComponentClassifySetting;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.spi.IGetComponents;
import com.parch.combine.core.vo.ComponentInitVO;

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
    public static List<ComponentInitVO> getComponents() {
        // 通过SPI加在所有组件
        List<ComponentInitVO> components = new ArrayList<>();
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
