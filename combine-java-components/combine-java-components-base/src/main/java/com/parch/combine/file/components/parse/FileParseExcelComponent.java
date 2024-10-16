package com.parch.combine.file.components.parse;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.FilePostfixEnum;
import com.parch.combine.file.base.parse.FileParseComponent;
import com.parch.combine.file.base.parse.excel.FileParseExcelErrorEnum;
import com.parch.combine.file.base.parse.excel.FileParseExcelInitConfig;
import com.parch.combine.file.base.parse.excel.FileParseExcelLogicConfig;
import com.parch.combine.file.base.parse.txt.FileParseTxtErrorEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(order = 500, key = "parse.excel", name = "解析表格文件数据组件", logicConfigClass = FileParseExcelLogicConfig.class, initConfigClass = FileParseExcelInitConfig.class)
@ComponentDesc("依赖 poi 和 poi-ooxml，推荐版本 4.1.2")
@ComponentResult(name = "表格数据矩阵（二维数组）")
public class FileParseExcelComponent extends FileParseComponent<FileParseExcelInitConfig, FileParseExcelLogicConfig> {

    /**
     * 构造器
     */
    public FileParseExcelComponent() {
        super(FileParseExcelInitConfig.class, FileParseExcelLogicConfig.class, FilePostfixEnum.XLS, FilePostfixEnum.XLSX);
    }

    @Override
    protected ComponentDataResult execute(FileInfo fileInfo) {
        List<List<String>> result = new ArrayList<>();

        // 根据类型解析文件
        FilePostfixEnum type = FilePostfixEnum.get(fileInfo.getType());
        try (ByteArrayInputStream is = new ByteArrayInputStream(fileInfo.getData())){
            switch (type) {
                case XLS:
                    result = read(new HSSFWorkbook(is));
                    break;
                case XLSX:
                    result = read(new XSSFWorkbook(is));
                    break;
            }
        } catch (IOException e) {
            PrintErrorHelper.print(FileParseTxtErrorEnum.FAIL, e);
            return ComponentDataResult.fail(FileParseExcelErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }

    /**
     * 读取表格数据
     *
     * @param workbook 表格对象
     * @return 数据
     */
    private static List<List<String>> read(Workbook workbook) {
        List<List<String>> result = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        int rowCount = sheet.getLastRowNum();
        for (int i = 0; i < rowCount + 1; i++) {
            List<String> rowData = new ArrayList<>();
            Row row = sheet.getRow(i);
            int colCount = row.getLastCellNum();
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                Object cellValue;
                switch (cell.getCellType()) {
                    case BOOLEAN:
                        cellValue = cell.getBooleanCellValue();
                        break;
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellValue = cell.getNumericCellValue();
                        break;
                    case FORMULA:
                        cellValue = cell.getRichStringCellValue();
                        break;
                    default:
                        cellValue = CheckEmptyUtil.EMPTY;
                        break;
                }
                rowData.add(cellValue.toString());
            }

            result.add(rowData);
        }

        return result;
    }
}
