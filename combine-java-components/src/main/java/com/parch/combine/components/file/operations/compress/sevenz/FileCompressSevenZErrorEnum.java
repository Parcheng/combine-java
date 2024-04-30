package com.parch.combine.components.file.operations.compress.sevenz;

import com.parch.combine.core.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileCompressSevenZErrorEnum implements IComponentError {

    FAIL("ZIP压缩或解压失败", "ZIP压缩或解压失败"),
    ;

    private String msg;

    private String showMsg;

    FileCompressSevenZErrorEnum(String msg, String showMsg) {
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
