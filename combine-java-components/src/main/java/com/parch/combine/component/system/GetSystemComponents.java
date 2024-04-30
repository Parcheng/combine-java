package com.parch.combine.component.system;

import com.parch.combine.component.system.doc.config.SystemDocConfigSettingHandler;
import com.parch.combine.component.system.doc.components.SystemDocSettingHandler;
import com.parch.combine.component.system.template.SystemTemplateSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取页面组件实现类
 */
public class GetSystemComponents extends AbsGetComponents {

    public GetSystemComponents() {
        super("system", "系统");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(SystemTemplateSettingHandler.get());
        setting.add(SystemDocSettingHandler.get());
        setting.add(SystemDocConfigSettingHandler.get());
        return setting;
    }
}
