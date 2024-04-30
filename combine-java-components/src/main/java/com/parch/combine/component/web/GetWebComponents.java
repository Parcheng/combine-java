package com.parch.combine.component.web;

import com.parch.combine.component.web.elements.WebElementGroupSettingHandler;
import com.parch.combine.component.web.page.custom.PageCustomSettingHandler;
import com.parch.combine.component.web.page.management.PageManagementSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取页面组件实现类
 */
public class GetWebComponents extends AbsGetComponents {

    public GetWebComponents() {
        super("web", "页面");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(WebElementGroupSettingHandler.get());
        setting.add(PageManagementSettingHandler.get());
        setting.add(PageCustomSettingHandler.get());
        return setting;
    }
}
