package com.parch.combine.gui.base.control.slider;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlSliderLogicConfig extends GUIControlLogicConfig {

    @Field(key = "orientation", name = "按钮显示文本", type = FieldTypeEnum.SELECT, defaultValue = "HORIZONTAL")
    @FieldSelect(enumClass = SliderOrientationEnum.class)
    String orientation();

    @Field(key = "min", name = "最小刻度", type = FieldTypeEnum.NUMBER, isRequired = true, defaultValue = "0")
    Integer min();

    @Field(key = "max", name = "最大刻度", type = FieldTypeEnum.NUMBER, isRequired = true, defaultValue = "100")
    Integer max();

    @Field(key = "value", name = "值", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer value();

    @Field(key = "majorTickSpacing", name = "主刻度间距", type = FieldTypeEnum.NUMBER)
    Integer majorTickSpacing();

    @Field(key = "minorTickSpacing", name = "次刻度间距", type = FieldTypeEnum.NUMBER)
    Integer minorTickSpacing();

    @Field(key = "paintTicks", name = "是否绘制刻度线", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean paintTicks();

    @Field(key = "paintLabels", name = "是否显示刻度标签", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean paintLabels();
}