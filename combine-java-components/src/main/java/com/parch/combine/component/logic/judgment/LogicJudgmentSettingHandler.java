package com.parch.combine.component.logic.judgment;

import com.parch.combine.core.settings.handler.CompareGroupSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class LogicJudgmentSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("judgment", "逻辑判断组件", false, LogicJudgmentComponent.class);
        // builder.addDesc("逻辑判断");

        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "配置项集合",  true, true);
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items.components", FieldTypeEnum.TEXT, "要执行的逻辑，可以是组件ID，也可以是组件配置",  true, true);


        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items.components", "[\"logicJudgmentTestData\", { \"type\": \"XXX\", ... }",
                "条件满足后，先执行 ID 为 logicJudgmentTestData 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑");

        builder.setResult("被执行的组件 ID 集合", false);
        return builder.get();
    }
}
