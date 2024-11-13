package com.parch.combine.system.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.ComponentSettingHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.system.base.doc.components.SystemDocSettingsInitConfig;
import com.parch.combine.system.base.doc.components.SystemDocSettingsLogicConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component(key = "doc.components", name = "获取组件API", logicConfigClass = SystemDocSettingsLogicConfig.class, initConfigClass = SystemDocSettingsInitConfig.class)
@ComponentResult(name = "所有组件设API集合")
public class SystemDocSettingsComponent extends AbstractComponent<SystemDocSettingsInitConfig, SystemDocSettingsLogicConfig> {

    public SystemDocSettingsComponent() {
        super(SystemDocSettingsInitConfig.class, SystemDocSettingsLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<ComponentClassifySetting> settings = ComponentSettingHandler.getSettings();
        String json = JsonUtil.obj2String(settings);
        return ComponentDataResult.success(JsonUtil.string2Obj(json, List.class, HashMap.class));
    }
}
