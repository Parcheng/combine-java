package com.parch.combine.file.base.parse.pdf;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileParsePdfErrorEnum implements IComponentError {

    FAIL("读取文件失败", "读取文件失败");

    private String msg;

    private String showMsg;

    FileParsePdfErrorEnum(String msg, String showMsg) {
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
