package com.parch.combine.component.file.read.table;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.helper.FileExcelHelper;
import com.parch.combine.component.file.helper.FileTextHelper;
import com.parch.combine.component.file.read.FileReadComponent;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.FilePostfixEnum;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件读取组件
 */
public class FileReadTableComponent extends FileReadComponent<FileReadTableInitConfig, FileReadTableLogicConfig> {

    private final static String DEFAULT_SEPARATOR = "|";

    /**
     * 构造器
     */
    public FileReadTableComponent() {
        super(FileReadTableInitConfig.class, FileReadTableLogicConfig.class);
    }

    @Override
    public List<String> init() {
        FileReadTableLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getSeparator())) {
            logicConfig.setSeparator(DEFAULT_SEPARATOR);
        }
        init(logicConfig);
        return null;
    }

    @Override
    protected DataResult execute(FileInfo fileInfo) {
        List<List<String>> table;
        FileReadTableLogicConfig config = getLogicConfig();

        // 根据类型解析文件
        FilePostfixEnum type = FilePostfixEnum.get(fileInfo.getType());
        switch (type) {
            case XLS:
                table = FileExcelHelper.readXls(fileInfo.getData(), config);
                break;
            case XLSX:
                table = FileExcelHelper.readXlsx(fileInfo.getData(), config);
                break;
            default:
                table = textDataHandler(FileTextHelper.read(fileInfo.getData(), config));
                break;
        }

        return DataResult.success(table);
    }

    /**
     * 表格数据处理
     *
     * @param data 表格数据
     * @return 文本集合
     */
    private List<List<String>> textDataHandler(List<String> data) {
        List<List<String>> table = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(data)) {
            return table;
        }

        String separator = getLogicConfig().getSeparator();
        for (String row : data) {
            if (row == null) {
                table.add(new ArrayList<>());
            } else {
                table.add(Arrays.asList(row.split(separator)));
            }
        }

        return table;
    }
}
