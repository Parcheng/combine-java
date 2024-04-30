package com.parch.combine.core.settings.spi;

import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.core.settings.handler.ComponentSettingHelper;
import com.parch.combine.core.settings.config.ComponentClassifySetting;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.vo.ComponentInitVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取组件接口
 */
public abstract class AbsGetComponents implements IGetComponents{

    private ComponentClassifySetting setting;

    public AbsGetComponents(String key, String desc) {
        setting = ComponentSettingHelper.buildClassify(key, desc);
        List<ComponentSetting> items = init();
        if (items != null) {
            for (ComponentSetting item : items) {
                if (item.getKey().indexOf(key) != 0) {
                    item.setKey(key + SymbolConstant.DOT + item.getKey());
                }
            }
            setting.setSettings(items);
        }
    }

    /**
     * 初始化设置
     *
     * @return 组件设置信息集合
     */
    public abstract List<ComponentSetting> init();

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    @Override
    public List<ComponentInitVO> get() {
        List<ComponentInitVO> components = new ArrayList<>();
        for (ComponentSetting setting : setting.getSettings()) {
            components.add(new ComponentInitVO(setting.getKey(), setting.getComponentClass()));
            // 防止返回到API DOC中
            setting.setComponentClass(null);
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
