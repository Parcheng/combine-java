package com.parch.combine.core.component.spi;

import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.settings.config.ComponentSetting;
import com.parch.combine.core.component.vo.ComponentClassInitVO;
import com.parch.combine.core.component.settings.builder.ComponentClassifySettingBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsGetComponents implements IGetComponents {

    private ComponentClassifySetting setting;

    public AbsGetComponents(String key, String name, Class<?> baseClass) {
        setting = ComponentClassifySettingBuilder.build(key, name, baseClass);
    }

    public AbsGetComponents(String key, String name, String packagePath) {
        setting = ComponentClassifySettingBuilder.build(key, name, packagePath);
    }

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    @Override
    public List<ComponentClassInitVO> get() {
        List<ComponentClassInitVO> components = new ArrayList<>();
        for (ComponentSetting setting : setting.getSettings()) {
            components.add(new ComponentClassInitVO(setting.getKey(), setting.thisComponentClass()));
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
