package com.parch.combine.component.file.read.text;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.StringUtil;
import com.parch.combine.component.file.helper.FileExcelHelper;
import com.parch.combine.component.file.helper.FileTextHelper;
import com.parch.combine.component.file.read.FileReadComponent;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.FilePostfixEnum;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 文件读取组件
 */
public class FileReadTextComponent extends FileReadComponent<FileReadTextInitConfig, FileReadTextLogicConfig> {

    private final static String DEFAULT_SEPARATOR = "|";

    /**
     * 构造器
     */
    public FileReadTextComponent() {
        super(FileReadTextInitConfig.class, FileReadTextLogicConfig.class);
    }

    @Override
    public List<String> init() {
        FileReadTextLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(getLogicConfig().getSeparator())) {
            logicConfig.setSeparator(DEFAULT_SEPARATOR);
        }
        init(logicConfig);
        return null;
    }

    @Override
    protected DataResult execute(FileInfo fileInfo) {
        List<String> text;
        FileReadTextLogicConfig config = getLogicConfig();

        // 根据类型解析文件
        FilePostfixEnum type = FilePostfixEnum.get(fileInfo.getType());
        switch (type) {
            case XLS:
                text = tableDataHandler(FileExcelHelper.readXls(fileInfo.getData(), config));
                break;
            case XLSX:
                text = tableDataHandler(FileExcelHelper.readXlsx(fileInfo.getData(), config));
                break;
            default:
                text = FileTextHelper.read(fileInfo.getData(), config);
                break;
        }

        return DataResult.success(text);
    }

    /**
     * 表格数据处理
     *
     * @param data 表格数据
     * @return 文本集合
     */
    private List<String> tableDataHandler(List<List<String>> data) {
        List<String> text = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(data)) {
            return text;
        }

        String separator = getLogicConfig().getSeparator();
        for (List<String> row : data) {
            text.add(StringUtil.join(row, separator));
        }
        return text;
    }
}
