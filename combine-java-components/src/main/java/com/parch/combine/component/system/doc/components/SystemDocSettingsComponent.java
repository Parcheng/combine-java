package com.parch.combine.component.system.doc.components;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.settings.ComponentSettingHandler;
import com.parch.combine.core.settings.config.ComponentClassifySetting;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 组件设置信息组件
 */
public class SystemDocSettingsComponent extends AbsComponent<SystemDocSettingsInitConfig, SystemDocSettingsLogicConfig> {

    /**
     * 构造器
     */
    public SystemDocSettingsComponent() {
        super(SystemDocSettingsInitConfig.class, SystemDocSettingsLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return new ArrayList<>();
    }

    @Override
    public DataResult execute() {
        List<ComponentClassifySetting> settings = ComponentSettingHandler.getSettings();
        String json = JsonUtil.serialize(settings);
        return DataResult.success(JsonUtil.parseArray(json, HashMap.class));
    }
}
