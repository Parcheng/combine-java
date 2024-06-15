package com.parch.combine.ui.core.base;

import com.parch.combine.core.common.base.ICheck;
import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.ui.core.tools.ConfigErrorMsgTool;

import java.util.ArrayList;
import java.util.List;

public class HtmlHeaderMetaConfig implements IInit, ICheck {

    @Field(key = "name", name = "页面的媒体信息名称", type = FieldTypeEnum.TEXT, isRequired = true)
    private String name;

    @Field(key = "content", name = "页面的媒体信息内容", type = FieldTypeEnum.TEXT, isRequired = true)
    private String content;

    @Override
    public List<String> check() {
        List<String> result = new ArrayList<>(2);
        if (CheckEmptyUtil.isEmpty(this.name)) {
            result.add(ConfigErrorMsgTool.fieldCheckError("name", "不能为空"));
        }
        if (CheckEmptyUtil.isEmpty(this.content)) {
            result.add(ConfigErrorMsgTool.fieldCheckError("content", "不能为空"));
        }

        return result;
    }

    @Override
    public void init() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
