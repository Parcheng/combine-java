package com.parch.combine.component.tool.lock;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class ToolLockSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("lock", "锁组件", true, ToolLockComponent.class);
        // builder.addDesc("锁");

        builder.addProperty(PropertyTypeEnum.INIT, "autoUnLock", FieldTypeEnum.TEXT, "流程执行完成是否自动释放锁",  false, false, "true");
        builder.addProperty(PropertyTypeEnum.INIT, "fair", FieldTypeEnum.TEXT, "否为公平锁（非公平锁，性能会好一点）",  false, false, "false");

        builder.addPropertyEg(PropertyTypeEnum.INIT, "autoUnLock", "false", "流程全部组件主席执行完毕后不自动解锁");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "fair", "true",  "加锁时使用公平锁");


        builder.addProperty(PropertyTypeEnum.LOGIC, "key", FieldTypeEnum.TEXT, "锁的KEY",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "key", "默认所有流程公用一把锁");
        builder.addProperty(PropertyTypeEnum.LOGIC, "count", FieldTypeEnum.NUMBER, "加锁数量",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "count", "大于0表示加锁，小于0表示解锁");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "key", "user_change", "添加或解除 KEY 为 user_change 的锁");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "count", "1", "加一把锁");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "count", "-1", "解一把锁");

        builder.setResult("true 或报错", false);

        return builder.get();
    }
}
