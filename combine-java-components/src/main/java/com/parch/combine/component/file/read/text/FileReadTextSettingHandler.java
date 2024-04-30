package com.parch.combine.component.file.read.text;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileReadTextSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("read.text", "读取文本文件数据组件", false, FileReadTextComponent.class);
        builder.addDesc("文件数据读取为文本");

        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "数据来源",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "startRow", FieldTypeEnum.NUMBER, "从第几行开始读取",  true, false, "1");
        builder.addProperty(PropertyTypeEnum.LOGIC, "startIndex", FieldTypeEnum.NUMBER, "从当前行的第几项开始读取",  false, false, "1");
        builder.addProperty(PropertyTypeEnum.LOGIC, "startSkipCount", FieldTypeEnum.NUMBER, "每行跳过前几项",  false, false, "0");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "startSkipCount", "提示：表格文件表示跳过前几列，文本文件表示跳过前几个字符");
        builder.addProperty(PropertyTypeEnum.LOGIC, "endDiscardCount", FieldTypeEnum.NUMBER, "每行丢弃掉后几项",  false, false, "0");
        builder.addProperty(PropertyTypeEnum.LOGIC, "separator", FieldTypeEnum.TEXT, "分隔符",  false, false, "|");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "separator", "提示：如果文件类型是表格，这个字段标识用于文本中区分列，默认“|”字符");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "读取 ID 为 data001 组件的返回结果作为文件数据，解析成数据");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "startRow", "2", "从第二行开始读取，跳过表头");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "startIndex", "1", "从第一行第一项开始读取");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "startSkipCount", "5", "读取时每行跳过前五项");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "endDiscardCount", "5", "读取时每行舍弃掉最后五项");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "separator", ",", "表格文件转成文本数据，每一列的数据添加逗号进行分隔");


        builder.setResult("表格格式的数据（二维数组的矩阵）", false);

        return builder.get();
    }
}
