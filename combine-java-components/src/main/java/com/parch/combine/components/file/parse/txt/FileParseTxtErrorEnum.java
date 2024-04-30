package com.parch.combine.components.file.parse.txt;

import com.parch.combine.core.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileParseTxtErrorEnum implements IComponentError {

    FAIL("读取文件失败", "读取文件失败");

    private String msg;

    private String showMsg;

    FileParseTxtErrorEnum(String msg, String showMsg) {
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
