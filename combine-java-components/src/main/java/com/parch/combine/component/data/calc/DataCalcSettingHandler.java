package com.parch.combine.component.data.calc;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class DataCalcSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("calc", "数据计算组件", false, DataCalcComponent.class);
        // builder.addDesc("数据计算");

        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "配置项集合",  true, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "计算配置项集合");
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "计算结果要写入的数据源（“-”表示不写入）", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "要使用的计算函数", FieldTypeEnum.SELECT,true, Arrays.asList(DataCalcModeEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "函数参数（多个用空格分隔）", FieldTypeEnum.TEXT,  false, null);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "函数详细介绍：");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【CALC】: 数学运算");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：运算表达式，如：100 - 20 * 2 + (30 / 2 + 26 % 20) - 30");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【MAX】: 计算最大值，支持数字/日期/字符串比较，至少有1个参数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1-n（必填）：参与比较的值，支持 #{...} 表达式（如果是集合，则集合值都参与比较）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【MIN】: 计算最小值，支持数字/日期/字符串比较，至少有1个参数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1-n（必填）：参与比较的值，支持 #{...} 表达式（如果是集合，则集合值都参与比较）");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【RANDOM】: 生成随机数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数1（必填）：生成随机数值的区间的最大值或最小值，必须为整数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t参数2（选填）：生成随机数值的区间的最大值或最小值，必须为整数");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "【UUID】: 生成UUID");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "items", "\t无参数");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "- CALC 100-20*2+(30/2+26%20)-30", "计算算术表达式");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "#{$r.data001.min} MIN a 1 b 34", "将 a 1 b 34 中的最大值写入到组件 data001 的 min 字段中");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "#{$r.data001.max} MAX #{list} 100", "将 100 和入参的 list 集合所有值中的最大值写入到组件 data001 的 max 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "#{$r.data001.random} RANDOM 100000", "获取 1 - 100000 的随机数写入到将组件 data001 的 random 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "#{$r.data001.uuid} UUID", "生成一个 UUID 写入到组件 data001 的 uuid 字段");


        builder.setResult("调用API返回的数据", false);
        return builder.get();
    }
}
