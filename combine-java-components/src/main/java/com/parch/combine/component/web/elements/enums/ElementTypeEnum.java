package com.parch.combine.component.web.elements.enums;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.settings.config.IOptionSetting;

/**
 * 页面元素枚举
 */
public enum ElementTypeEnum implements IOptionSetting {

    TEXT("文本元素", true),
    TABLE("表格元素", true),
    BUTTON("按钮元素", true),
    WINDOW("窗口元素", true),
    PAGINATION("分页元素", true),
    PAGE_TURNING("翻页元素", true),
    LIST("列表元素", true),
    TAG("标签元素", true),
    POP("泡泡窗元素", true),
    TAB("页签元素", true),
    BREADCRUMB("面包削元素", true),
    CONTENT("内容元素", true),
    FROM("表单元素", true),
    NAV_BAR("导航条元素", true),
    TREE("树型元素", true),
    THUMBNAIL("缩略图元素", true),
    INPUT("输入元素", true),
    SELECT("下拉选择元素", true),
    RADIO("单选元素", true),
    CHECKBOX("多选元素", true),
    TEXTAREA("多行文本输入元素", true),
    AUDIO("音频元素", true),
    VIDEO("视频元素", true),
    LINE("分割线元素", true),
    TITLE("标题元素", true);

    private String name;
    private boolean isValid;

    ElementTypeEnum(String name, boolean isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    public static ElementTypeEnum get(String name) {
        if (CheckEmptyUtil.isEmpty(name)) {
            return TEXT;
        }
        for (ElementTypeEnum value : ElementTypeEnum.values()) {
            if (value.toString().equals(name.toUpperCase())) {
                return value;
            }
        }
        return TEXT;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public boolean isValid() {
        return this.isValid;
    }
}