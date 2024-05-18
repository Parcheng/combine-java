package com.parch.combine.components.ui.doc.elements;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.ui.settings.PageElementSettingHandler;
import com.parch.combine.core.ui.settings.config.PageElementClassifySetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component(key = "doc.elements", name = "获取UI元素API", logicConfigClass = UIElementDocSettingsLogicConfig.class, initConfigClass = UIElementDocSettingsInitConfig.class)
@ComponentResult(name = "所有UI元素API集合")
public class UIElementDocSettingsComponent extends AbsComponent<UIElementDocSettingsInitConfig, UIElementDocSettingsLogicConfig> {

    /**
     * 构造器
     */
    public UIElementDocSettingsComponent() {
        super(UIElementDocSettingsInitConfig.class, UIElementDocSettingsLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return new ArrayList<>();
    }

    @Override
    public DataResult execute() {
        List<PageElementClassifySetting> settings = PageElementSettingHandler.getSettings();
        String json = JsonUtil.serialize(settings);
        return DataResult.success(JsonUtil.parseArray(json, HashMap.class));
    }
}
