package com.parch.combine.components.file.operations.compress.zip;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileCompressZipErrorEnum implements IComponentError {

    FAIL("ZIP压缩或解压失败", "ZIP压缩或解压失败"),
    ;

    private String msg;

    private String showMsg;

    FileCompressZipErrorEnum(String msg, String showMsg) {
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
