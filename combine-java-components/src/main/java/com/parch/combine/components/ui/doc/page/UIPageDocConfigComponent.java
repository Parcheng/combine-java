package com.parch.combine.components.ui.doc.page;

import com.parch.combine.core.common.settings.builder.PropertySettingBuilder;
import com.parch.combine.core.common.settings.config.PropertySetting;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.ui.base.HtmlConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component(key = "doc.page", name = "获取UI页面配置API", logicConfigClass = UIPageDocConfigLogicConfig.class, initConfigClass = UIPageDocConfigInitConfig.class)
@ComponentResult(name = "UI页面配置API")
public class UIPageDocConfigComponent extends AbsComponent<UIPageDocConfigInitConfig, UIPageDocConfigLogicConfig> {

    private List<HashMap> result;

    /**
     * 构造器
     */
    public UIPageDocConfigComponent() {
        super(UIPageDocConfigInitConfig.class, UIPageDocConfigLogicConfig.class);
    }


    @Override
    public List<String> init(){
        List<PropertySetting> properties = PropertySettingBuilder.build("global", HtmlConfig.class);
        String json = JsonUtil.serialize(properties);
        result = JsonUtil.parseArray(json, HashMap.class);
        return new ArrayList<>();
    }

    @Override
    public DataResult execute() {
        return DataResult.success(result);
    }
}
