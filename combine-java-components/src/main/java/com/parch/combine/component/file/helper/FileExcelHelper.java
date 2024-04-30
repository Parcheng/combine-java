package com.parch.combine.component.file.helper;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.read.FileDataReadLogicConfig;
import com.parch.combine.core.error.ComponentErrorHandler;
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

/**
 * 表格文件读取
 */
public class FileExcelHelper {

    /**
     * 构建xlsx
     *
     * @param data 数据
     * @return 结果
     */
    public static byte[] buildXlsx(List<List<String>> data) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < data.size(); i++) {
            List<String> rowDatas = data.get(i);
            Row row = sheet.createRow(i);
            for (int j = 0; j < rowDatas.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(rowDatas.get(j));
            }
        }
        return workbook.getBytes();
    }

    /**
     * 读取
     *
     * @param data 文件数据
     * @return 文本
     */
    public static List<List<String>> readXls(byte[] data, FileDataReadLogicConfig config) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            return read(new HSSFWorkbook(is), config);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 读取
     *
     * @param data 文件数据
     * @return 文本
     */
    public static List<List<String>> readXlsx(byte[] data, FileDataReadLogicConfig config) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            return read(new XSSFWorkbook(is), config);
        } catch (IOException e) {
            ComponentErrorHandler.print("读取XLSX文件异常", e);
        }
        return null;
    }

    /**
     * 读取表格数据
     *
     * @param workbook 表格对象
     * @return 数据
     */
    private static List<List<String>> read(Workbook workbook, FileDataReadLogicConfig config) {
        if(workbook == null) {
            return null;
        }

        int startRowIndex = config.getStartRow() - 1;
        int startIndex = config.getStartIndex() - 1;
        int startSkipCount = config.getStartSkipCount() + startIndex;
        int endDiscardCount = config.getEndDiscardCount();

        List<List<String>> result = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        // 从第 startRowIndex 行开始读取
        int rowCount = sheet.getLastRowNum();
        for (int i = startRowIndex; i < rowCount + 1; i++) {
            // 跳过指定行数
            if (i <= startRowIndex) {
                continue;
            }

            Row row = sheet.getRow(i);

            // 每行跳过前 startSkipCount 项，忽略掉后 endDiscardCount项
            List<String> rowData = new ArrayList<>();
            int colCount = row.getLastCellNum();
            if (colCount > endDiscardCount + startSkipCount) {
                colCount = colCount - endDiscardCount;
            } else {
                continue;
            }

            // 首行跳过前 startIndex 项
            for (int j = startSkipCount; j < colCount + 1; j++) {
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
