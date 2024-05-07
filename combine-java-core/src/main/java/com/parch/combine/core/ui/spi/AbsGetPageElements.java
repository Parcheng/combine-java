package com.parch.combine.core.ui.spi;

import com.parch.combine.core.ui.settings.builder.PageElementClassifySettingBuilder;
import com.parch.combine.core.ui.settings.config.PageElementClassifySetting;
import com.parch.combine.core.ui.settings.config.PageElementSetting;
import com.parch.combine.core.ui.vo.PageElementInitVO;

import java.util.ArrayList;
import java.util.List;

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
        List<PageElementInitVO> initVOs = new ArrayList<>();
        for (PageElementSetting setting : setting.getSettings()) {
            initVOs.add(new PageElementInitVO(setting.getKey(), setting.thisElementClass()));
        }
        return initVOs;
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
