package com.parch.combine.core.settings.spi;

import com.parch.combine.core.settings.builder.ComponentClassifySettingBuilder;
import com.parch.combine.core.settings.config.ComponentClassifySetting;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.vo.ComponentInitVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取组件接口
 */
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
