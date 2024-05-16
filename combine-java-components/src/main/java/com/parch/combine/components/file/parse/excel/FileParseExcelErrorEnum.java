package com.parch.combine.components.file.parse.excel;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileParseExcelErrorEnum implements IComponentError {

    FAIL("读取表格文件失败", "读取表格文件失败");

    private String msg;

    private String showMsg;

    FileParseExcelErrorEnum(String msg, String showMsg) {
        this.msg = msg;
        this.showMsg = showMsg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
