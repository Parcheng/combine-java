package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.LineSettings;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "分割线页面元素", desc = "当 TYPE = LINE 时的参数列表")
public class LineElementEntity extends ElementEntity<LineSettings> {

    @Field(key = "line", name = "线DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig line;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = LineSettings.class)
    private LineSettings settings;

    public LineElementEntity() {
        super(ElementTypeEnum.LINE);
    }

    @Override
    public LineSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(LineSettings settings) {
        this.settings = settings;
    }

    public ElementDomConfig getLine() {
        return line;
    }

    public void setLine(ElementDomConfig line) {
        this.line = line;
    }
}
