package com.parch.combine.component.call;

import com.parch.combine.component.call.api.CallApiSettingHandler;
import com.parch.combine.component.call.flow.CallFlowSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;
import java.util.ArrayList;
import java.util.List;

/**
 * 组件加载器
 */
public class GetCallComponents extends AbsGetComponents {

    public GetCallComponents() {
        super("call", "调用");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(CallApiSettingHandler.get());
        setting.add(CallFlowSettingHandler.get());
        return setting;
    }
}
