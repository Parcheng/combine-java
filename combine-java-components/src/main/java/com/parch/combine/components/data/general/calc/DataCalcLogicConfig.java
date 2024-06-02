package com.parch.combine.components.data.general.calc;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.ConfigGroupTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 逻辑配置类
 */
public class DataCalcLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.GROUP, isRequired = true, isArray = true)
    @FieldDesc("计算配置项集合")
    @FieldGroup(index = 0, name = "计算结果要写入的数据源（“-”表示不写入）", type = FieldTypeEnum.TEXT)
    @FieldGroup(index = 1, name = "要使用的计算函数", type = FieldTypeEnum.SELECT)
    @FieldGroupSelect(index = 1, enumClass = DataCalcModeEnum.class)
    @FieldGroup(index = 2, name = "函数参数（多个用空格分隔）", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "- CALC 100-20*2+(30/2+26%20)-30", desc = "计算算术表达式")
    @FieldEg(eg = "#{$r.data001.min} MIN a 1 b 34", desc = "将 a 1 b 34 中的最大值写入到组件 data001 的 min 字段中")
    @FieldEg(eg = "#{$r.data001.max} MAX #{list} 100", desc = "将 100 和入参的 list 集合所有值中的最大值写入到组件 data001 的 max 字段")
    @FieldEg(eg = "#{$r.data001.random} RANDOM 100000", desc = "获取 1 - 100000 的随机数写入到将组件 data001 的 random 字段")
    @FieldEg(eg = "#{$r.data001.uuid} UUID", desc = "生成一个 UUID 写入到组件 data001 的 uuid 字段")
    private List<DataCalcItem> items = new ArrayList<>();

    @Override
    public void init() {}

    public List<DataCalcItem> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = ConfigGroupTool.buildItemList(items, itemStr -> {
            DataCalcItem item = new DataCalcItem();
            item.setTarget(ConfigGroupTool.getConfigByIndex(itemStr,0));
            item.setMode(ConfigGroupTool.getConfigByIndex(itemStr,1));
            item.setParams(Arrays.asList(ConfigGroupTool.getConfigsByIndex(itemStr, 2, itemStr.length -1)));
            return item;
        });
    }

    public static class DataCalcItem {
        /**
         * 运算结果的存储字段
         */
        String target;

        /**
         * 运算方式
         */
        String mode;

        /**
         * 参数
         */
        List<String> params;

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }
    }
}
