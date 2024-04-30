package com.parch.combine.component.logic.loop;

import com.parch.combine.core.settings.handler.CompareGroupSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class LogicLoopSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("loop", "逻辑循环组件", false, LogicLoopComponent.class);
        builder.addDesc("逻辑循环执行");

        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "要遍历的数据，提示：source 和 count 至少要配置一项",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "count", FieldTypeEnum.NUMBER, "要循环的次数，提示：source 和 count 至少要配置一项",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "condition", FieldTypeEnum.OBJECT, "停止循环的条件配置",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "condition.skip", FieldTypeEnum.OBJECT, "跳过当前循环的条件配置",  false, false);
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "condition.skip");
        builder.addProperty(PropertyTypeEnum.LOGIC, "condition.finish", FieldTypeEnum.OBJECT, "终止循环的条件配置",  false, false);
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "condition.finish");
        builder.addProperty(PropertyTypeEnum.LOGIC, "components", FieldTypeEnum.TEXT, "要循环执行的逻辑配置，可以是组件ID，也可以是组件配置",  true, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "failStop", FieldTypeEnum.BOOLEAN, "逻辑执行失败是否终止，默认 true",  false, false, "false");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data", "遍历组件 ID 为 data 的执行结果");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "count", "5", "只遍历前五条，如果数据来源不足五条，则将数据遍历完为止");
        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder, "condition.skip");
        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder, "condition.finish");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "components", "[\"loopTest1\", { \"type\": \"XXX\", ... }",
                "条件满足后，先执行 ID 为 loopTest1 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "failStop", "true", "组件逻辑执行报错时，不停止循环，依然继续执行");


        builder.setResult("循环次数", false);

        return builder.get();
    }
}
