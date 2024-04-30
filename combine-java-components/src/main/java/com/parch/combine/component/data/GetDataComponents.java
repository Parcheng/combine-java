package com.parch.combine.component.data;

import com.parch.combine.component.data.calc.DataCalcSettingHandler;
import com.parch.combine.component.data.create.DataCreateSettingHandler;
import com.parch.combine.component.data.edit.DataEditSettingHandler;
import com.parch.combine.component.data.filter.DataFilterSettingHandler;
import com.parch.combine.component.data.format.DataFormatSettingHandler;
import com.parch.combine.component.data.mapping.DataMappingSettingHandler;
import com.parch.combine.component.data.reset.DataResetSettingHandler;
import com.parch.combine.component.data.verify.DataVerifySettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取数据处理组件加载器
 */
public class GetDataComponents extends AbsGetComponents {

    public GetDataComponents() {
        super("data", "数据处理");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(DataCalcSettingHandler.get());
        setting.add(DataCreateSettingHandler.get());
        setting.add(DataEditSettingHandler.get());
        setting.add(DataFilterSettingHandler.get());
        setting.add(DataFormatSettingHandler.get());
        setting.add(DataMappingSettingHandler.get());
        setting.add(DataResetSettingHandler.get());
        setting.add(DataVerifySettingHandler.get());
        return setting;
    }
}
