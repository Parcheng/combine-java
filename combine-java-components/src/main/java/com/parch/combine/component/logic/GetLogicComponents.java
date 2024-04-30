package com.parch.combine.component.logic;

import com.parch.combine.component.logic.error.LogicErrorSettingHandler;
import com.parch.combine.component.logic.judgment.LogicJudgmentSettingHandler;
import com.parch.combine.component.logic.loop.LogicLoopSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取逻辑组件实现类
 */
public class GetLogicComponents extends AbsGetComponents {

    public GetLogicComponents() {
        super("logic", "逻辑处理");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(LogicErrorSettingHandler.get());
        setting.add(LogicJudgmentSettingHandler.get());
        setting.add(LogicLoopSettingHandler.get());
        return setting;
    }
}
