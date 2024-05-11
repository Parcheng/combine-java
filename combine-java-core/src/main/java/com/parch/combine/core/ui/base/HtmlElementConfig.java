package com.parch.combine.core.ui.base;

import com.parch.combine.core.common.base.ICheck;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.tools.ConfigErrorMsgTool;

import java.util.ArrayList;
import java.util.List;

public class HtmlElementConfig extends DomConfig implements ICheck {

    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("对应模板中每部分的KEY，系统内置模板包含：header、footer、left、right、content 五部分")
    private String key;

    @Field(key = "defaultShowGroupId", name = "默认展示的元素组ID", type = FieldTypeEnum.TEXT)
    public String defaultShowGroupId;

    public HtmlElementConfig() {}

    public HtmlElementConfig(String key, String tag) {
        this.key = key;
        this.setTag(tag == null ? "div" : tag);
    }

    @Override
    public List<String> check() {
        List<String> result = new ArrayList<>(1);
        if (CheckEmptyUtil.isEmpty(this.key)) {
            result.add(ConfigErrorMsgTool.fieldCheckError("key", "不能为空"));
        }
        return result;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefaultShowGroupId() {
        return defaultShowGroupId;
    }

    public void setDefaultShowGroupId(String defaultShowGroupId) {
        this.defaultShowGroupId = defaultShowGroupId;
    }


}