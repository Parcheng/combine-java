package com.parch.combine.components.ui.doc.config;

import com.parch.combine.core.common.settings.builder.PropertySettingBuilder;
import com.parch.combine.core.common.settings.config.PropertySetting;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.ui.vo.GlobalConfigVO;
import java.util.HashMap;
import java.util.List;

@Component(order = 900, key = "doc.config", name = "获取UI设置API", logicConfigClass = UIDocConfigLogicConfig.class, initConfigClass = UIDocConfigInitConfig.class)
@ComponentResult(name = "UI设置API")
public class UIDocConfigComponent extends AbsComponent<UIDocConfigInitConfig, UIDocConfigLogicConfig> {

    private static List<HashMap> result;

    /**
     * 构造器
     */
    public UIDocConfigComponent() {
        super(UIDocConfigInitConfig.class, UIDocConfigLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        if (result == null) {
            synchronized (UIDocConfigComponent.class) {
                if (result == null) {
                    List<PropertySetting> properties = PropertySettingBuilder.build("global", GlobalConfigVO.class);
                    String json = JsonUtil.serialize(properties);
                    result = JsonUtil.parseArray(json, HashMap.class);
                }
            }
        }

        return DataResult.success(result);
    }
}
