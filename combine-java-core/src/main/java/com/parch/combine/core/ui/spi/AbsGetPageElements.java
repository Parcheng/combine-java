package com.parch.combine.core.ui.spi;

import com.parch.combine.core.ui.settings.builder.PageElementClassifySettingBuilder;
import com.parch.combine.core.ui.settings.config.PageElementClassifySetting;
import com.parch.combine.core.ui.vo.PageElementInitVO;
import java.util.List;

/**
 * 获取组件接口
 */
public abstract class AbsGetPageElements implements IGetPageElements {

    private PageElementClassifySetting setting;

    public AbsGetPageElements(String key, String name, Class<?> baseClass) {
        setting = PageElementClassifySettingBuilder.build(key, name, baseClass);
    }

    public AbsGetPageElements(String key, String name, String packagePath) {
        setting = PageElementClassifySettingBuilder.build(key, name, packagePath);
    }

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    @Override
    public List<PageElementInitVO> get() {
//        List<ComponentInitVO> components = new ArrayList<>();
//        for (ComponentSetting setting : setting.getSettings()) {
//            components.add(new ComponentInitVO(setting.getKey(), setting.thisComponentClass()));
//        }
        return null;
    };

    /**
     * 获取组件设置
     *
     * @return 组件设置集合
     */
    @Override
    public PageElementClassifySetting getSetting() {
        return setting;
    }
}
