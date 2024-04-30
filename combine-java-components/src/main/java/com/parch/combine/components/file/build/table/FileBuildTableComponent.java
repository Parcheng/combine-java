package com.parch.combine.components.file.build.table;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.components.file.build.FileBuildComponent;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

/**
 * 构建表格文件组件数据
 */
@Component(order = 100, key = "build.table", name = "构建表格文件数据组件", logicConfigClass = FileBuildTableLogicConfig.class, initConfigClass = FileBuildTableInitConfig.class)
@ComponentResult(name = "文件的字节数据，可以下载/保存成 xlsx 文件（其他格式不行）")
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

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < tableData.size(); i++) {
            List<String> rowDatas = tableData.get(i);
            Row row = sheet.createRow(i);
            for (int j = 0; j < rowDatas.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowDatas.get(j));
            }
        }

        return new FileBuildInfo(FILE_TYPE, workbook.getBytes());
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
