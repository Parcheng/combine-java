package com.parch.combine.components.system.doc.components;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.ComponentSettingHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.vo.DataResult;

import java.util.HashMap;
import java.util.List;

@Component(key = "doc.components", name = "获取组件API", logicConfigClass = SystemDocSettingsLogicConfig.class, initConfigClass = SystemDocSettingsInitConfig.class)
@ComponentResult(name = "所有组件设API集合")
public class SystemDocSettingsComponent extends AbsComponent<SystemDocSettingsInitConfig, SystemDocSettingsLogicConfig> {

    public SystemDocSettingsComponent() {
        super(SystemDocSettingsInitConfig.class, SystemDocSettingsLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        List<ComponentClassifySetting> settings = ComponentSettingHandler.getSettings();
        String json = JsonUtil.serialize(settings);
        return DataResult.success(JsonUtil.parseArray(json, HashMap.class));
    }
}
