package com.parch.combine.ui.settings.spi;

import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.settings.config.ComponentSetting;
import com.parch.combine.core.component.vo.ComponentInitVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取组件接口
 */
public abstract class AbsGetPageElements implements IGetPageElements {

    private ComponentClassifySetting setting;

    public AbsGetPageElements(String key, String name, Class<?> baseClass) {
        // setting = ComponentClassifySettingBuilder.build(key, name, baseClass);
    }

    public AbsGetPageElements(String key, String name, String packagePath) {
        // setting = ComponentClassifySettingBuilder.build(key, name, packagePath);
    }

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    @Override
    public List<ComponentInitVO> get() {
        List<ComponentInitVO> components = new ArrayList<>();
        for (ComponentSetting setting : setting.getSettings()) {
            components.add(new ComponentInitVO(setting.getKey(), setting.thisComponentClass()));
        }
        return components;
    };

    /**
     * 获取组件设置
     *
     * @return 组件设置集合
     */
    @Override
    public ComponentClassifySetting getSetting() {
        return setting;
    }
}
