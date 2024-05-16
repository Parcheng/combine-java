package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.config.IOptionSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较类型
 */
public enum CompareTypeEnum implements IOptionSetting {
    EQ("等于", true),
    NEQ("不等于", true),
    GT("大于", true),
    GTE("大于或等于", true),
    LT("小于", true),
    LTE("小于或等于", true),
    IS_EMPTY("是空", true),
    IS_NULL("是NULL", true),
    NO_EMPTY("不是空", true),
    NO_NULL("不是NULL", true),
    CONTAINS("包含", true),
    IS_STRING("是字符串", true),
    IS_NUMBER("是数字", true),
    IS_BOOLEAN("是布尔", true),
    IS_DATE("是日期", true),
    IS_OBJECT("是对象结构", true),
    IS_LIST("是集合", true),
    NONE("未知", false);

    private String name;
    private boolean isValid;

    CompareTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    /**
     * 根据类型获取枚举
     *
     * @param type 类型
     * @return 枚举
     */
    public static CompareTypeEnum get(String type) {
        if (CheckEmptyUtil.isEmpty(type)) {
            return NONE;
        }
        for (CompareTypeEnum value : CompareTypeEnum.values()) {
            if (value.toString().equals(type.toUpperCase())) {
                return value;
            }
        }
        return NONE;
    }

    /**
     * 获取不需要右值的类型集合
     *
     * @return 类型集合
     */
    public static List<CompareTypeEnum> getNoTargetTypes() {
        List<CompareTypeEnum> noTargetType = new ArrayList<>();
        noTargetType.add(CompareTypeEnum.IS_EMPTY);
        noTargetType.add(CompareTypeEnum.IS_NULL);
        noTargetType.add(CompareTypeEnum.NO_EMPTY);
        noTargetType.add(CompareTypeEnum.NO_NULL);
        return noTargetType;
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }
}
