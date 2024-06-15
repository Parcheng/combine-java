package com.parch.combine.ui.core.spi;

import com.parch.combine.ui.core.settings.builder.PageElementClassifySettingBuilder;
import com.parch.combine.ui.core.settings.config.PageElementClassifySetting;
import com.parch.combine.ui.core.settings.config.PageElementSetting;
import com.parch.combine.ui.core.vo.PageElementClassInitVO;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsGetUIElements implements IGetUIElements {

    private PageElementClassifySetting setting;

    public AbsGetUIElements(String key, String name, Class<?> baseClass) {
        setting = PageElementClassifySettingBuilder.build(key, name, baseClass);
    }

    public AbsGetUIElements(String key, String name, String packagePath) {
        setting = PageElementClassifySettingBuilder.build(key, name, packagePath);
    }

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    @Override
    public List<PageElementClassInitVO> get() {
        List<PageElementClassInitVO> initVOs = new ArrayList<>();
        for (PageElementSetting setting : setting.getSettings()) {
            initVOs.add(new PageElementClassInitVO(setting.getKey(), setting.thisElementClass()));
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
