package com.parch.combine.component.file.build.table;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileBuildTableSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("build.table", "构建表格文件数据组件", false, FileBuildTableComponent.class);
        // builder.addDesc("创建表格文件数据");

        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "表格的数据来源",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "source", "必须是对象或者对象集合的格式");
        builder.addProperty(PropertyTypeEnum.LOGIC, "header", FieldTypeEnum.TEXT, "表头",  false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "header", "个数必须与 filedNames 配置的个数一致");
        builder.addProperty(PropertyTypeEnum.LOGIC, "filedNames", FieldTypeEnum.TEXT, "表格数据KEY",  false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "header", "表格每一列对应的字段取值");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "要将ID为 data001 组件的结果数据，转换成文件的字节数据");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "header", "[\"ID\", \"名称\", \"年龄\"]", "表头为ID, 名称, 年龄");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "filedNames", "[\"id\", \"name\", \"age\"]", "按顺序从 data001 组件的结果中取id, name, age 字段写入表格文件数据中");


        builder.setResult("文件的字节数据，可以下载/保存成 xlsx 文件（其他格式不行）", false);

        return builder.get();
    }
}
