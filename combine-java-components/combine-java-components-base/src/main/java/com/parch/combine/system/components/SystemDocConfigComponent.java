package com.parch.combine.system.components;

import com.parch.combine.core.common.settings.builder.PropertySettingBuilder;
import com.parch.combine.core.common.settings.config.PropertySetting;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ScopeContext;
import com.parch.combine.core.component.context.ScopeContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.system.base.doc.config.SystemDocConfigErrorEnum;
import com.parch.combine.system.base.doc.config.SystemDocConfigInitConfig;
import com.parch.combine.system.base.doc.config.SystemDocConfigLogicConfig;

import java.util.HashMap;
import java.util.List;

@Component(key = "doc.config", name = "获取系统设置API", logicConfigClass = SystemDocConfigLogicConfig.class, initConfigClass = SystemDocConfigInitConfig.class)
@ComponentResult(name = "系统设置信息API")
public class SystemDocConfigComponent extends AbstractComponent<SystemDocConfigInitConfig, SystemDocConfigLogicConfig> {

    private List<HashMap> result;

    public SystemDocConfigComponent() {
        super(SystemDocConfigInitConfig.class, SystemDocConfigLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        ScopeContext context = ScopeContextHandler.get(getScopeKey());
        if (context == null) {
            return ComponentDataResult.fail(SystemDocConfigErrorEnum.FAIL);
        }

        if (result == null) {
            synchronized (SystemDocConfigComponent.class) {
                if (result == null) {
                    List<PropertySetting> properties = PropertySettingBuilder.build("global", ScopeContext.class);
                    String json = JsonUtil.obj2String(properties);
                    result = JsonUtil.string2Obj(json, List.class, HashMap.class);
                }
            }
        }

        return ComponentDataResult.success(result);
    }
}
