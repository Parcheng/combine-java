package com.parch.combine.system.components;

import com.parch.combine.core.common.settings.builder.PropertySettingBuilder;
import com.parch.combine.core.common.settings.config.PropertySetting;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.GlobalContext;
import com.parch.combine.core.component.context.GlobalContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.system.base.doc.config.SystemDocConfigErrorEnum;
import com.parch.combine.system.base.doc.config.SystemDocConfigInitConfig;
import com.parch.combine.system.base.doc.config.SystemDocConfigLogicConfig;

import java.util.HashMap;
import java.util.List;

@Component(key = "doc.config", name = "获取系统设置API", logicConfigClass = SystemDocConfigLogicConfig.class, initConfigClass = SystemDocConfigInitConfig.class)
@ComponentResult(name = "系统设置信息API")
public class SystemDocConfigComponent extends AbsComponent<SystemDocConfigInitConfig, SystemDocConfigLogicConfig> {

    private List<HashMap> result;

    public SystemDocConfigComponent() {
        super(SystemDocConfigInitConfig.class, SystemDocConfigLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        GlobalContext context = GlobalContextHandler.get(getScopeKey());
        if (context == null) {
            return DataResult.fail(SystemDocConfigErrorEnum.FAIL);
        }

        if (result == null) {
            synchronized (SystemDocConfigComponent.class) {
                if (result == null) {
                    List<PropertySetting> properties = PropertySettingBuilder.build("global", GlobalContext.class);
                    String json = JsonUtil.serialize(properties);
                    result = JsonUtil.parseArray(json, HashMap.class);
                }
            }
        }

        return DataResult.success(result);
    }
}
