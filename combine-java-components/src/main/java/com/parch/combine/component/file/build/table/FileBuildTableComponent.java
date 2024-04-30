package com.parch.combine.component.file.build.table;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.helper.FileExcelHelper;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.component.file.build.FileBuildComponent;

import java.util.*;

/**
 * 构建表格文件组件数据
 */
public class FileBuildTableComponent extends FileBuildComponent<FileBuildTableInitConfig, FileBuildTableLogicConfig> {

    /**
     * 文件类型（后缀）
     */
    private static final String FILE_TYPE = "xlsx";

    /**
     * 构造器
     */
    public FileBuildTableComponent() {
        super(FileBuildTableInitConfig.class, FileBuildTableLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        FileBuildTableLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getFiledNames())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig,  "字段名称集合为空"));
        }
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getHeader()) && logicConfig.getHeader().size() != logicConfig.getHeader().size()) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig,  "表头和列数量不一致"));
        }
        return result;
    }

    @Override
    protected FileBuildInfo buildFromObject(Map<String, Object> data) {
        return build(Collections.singletonList(data));
    }

    @Override
    protected FileBuildInfo buildFromObjectList(List<Map<String, Object>> dataList) {
        return build(dataList);
    }

    @Override
    protected FileBuildInfo buildFromByte(byte[] data) {
        return new FileBuildInfo(FILE_TYPE, data);
    }

    /**
     * 根据集合构建
     *
     * @param list 集合数据
     * @return 文件构建信息
     */
    private FileBuildInfo build(List<Map<String, Object>> list) {
        FileBuildTableLogicConfig logicConfig = getLogicConfig();

        // 加入表头
        List<List<String>> tableData = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getHeader())) {
            tableData.add(logicConfig.getHeader());
        }

        // 组装表格数据
        for (Map<String, Object> item : list) {
            tableData.add(disposeItem(item, logicConfig.getFiledNames()));
        }

        byte[] byteData = FileExcelHelper.buildXlsx(tableData);
        return new FileBuildInfo(FILE_TYPE, byteData);
    }

    /**
     * 处理数据对象
     *
     * @param data 数据对象
     * @param keys 字段集合
     * @return 结果
     */
    private List<String> disposeItem(Map<String, Object> data, List<String> keys) {
        List<String> result = new ArrayList<>();
        for (String key : keys) {
            Object obj = data.get(key);
            result.add(obj == null ? null : obj.toString());
        }
        return result;
    }
}
