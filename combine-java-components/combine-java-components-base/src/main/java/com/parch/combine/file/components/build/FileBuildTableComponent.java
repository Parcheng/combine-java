package com.parch.combine.file.components.build;

import com.parch.combine.file.base.build.DefaultFileBuildComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.build.table.FileBuildTableInitConfig;
import com.parch.combine.file.base.build.table.FileBuildTableLogicConfig;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

@Component(order = 100, key = "build.table", name = "构建表格文件数据组件", logicConfigClass = FileBuildTableLogicConfig.class, initConfigClass = FileBuildTableInitConfig.class)
@ComponentDesc("依赖 mail，推荐版本 1.4.7")
@ComponentResult(name = "文件的字节数据，可以下载/保存成 xlsx 文件（其他格式不行）")
public class FileBuildTableComponent extends DefaultFileBuildComponent<FileBuildTableInitConfig, FileBuildTableLogicConfig> {

    /**
     * 文件类型（后缀）
     */
    private static final String FILE_TYPE = "xlsx";

    public FileBuildTableComponent() {
        super(FileBuildTableInitConfig.class, FileBuildTableLogicConfig.class);
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
        String[] header = logicConfig.header();
        if (CheckEmptyUtil.isNotEmpty(header)) {
            tableData.add(Arrays.asList(header));
        }

        // 组装表格数据
        for (Map<String, Object> item : list) {
            tableData.add(disposeItem(item, Arrays.asList(logicConfig.filedNames())));
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
