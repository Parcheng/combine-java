package com.parch.combine.ui.components.doc.elements;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.ui.core.settings.PageElementSettingHandler;
import com.parch.combine.ui.core.settings.config.PageElementClassifySetting;

import java.util.HashMap;
import java.util.List;

@Component(order = 900, key = "doc.elements", name = "获取UI元素API", logicConfigClass = UIElementDocSettingsLogicConfig.class, initConfigClass = UIElementDocSettingsInitConfig.class)
@ComponentResult(name = "所有UI元素API集合")
public class UIElementDocSettingsComponent extends AbstractComponent<UIElementDocSettingsInitConfig, UIElementDocSettingsLogicConfig> {

    public UIElementDocSettingsComponent() {
        super(UIElementDocSettingsInitConfig.class, UIElementDocSettingsLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<PageElementClassifySetting> settings = PageElementSettingHandler.getSettings();
        String json = JsonUtil.serialize(settings);
        return ComponentDataResult.success(JsonUtil.parseArray(json, HashMap.class));
    }
}
