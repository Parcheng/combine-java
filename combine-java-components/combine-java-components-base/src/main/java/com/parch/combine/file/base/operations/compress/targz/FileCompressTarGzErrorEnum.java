package com.parch.combine.file.base.operations.compress.targz;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileCompressTarGzErrorEnum implements IComponentError {

    FAIL("ZIP压缩或解压失败", "ZIP压缩或解压失败"),
    ;

    private String msg;

    private String showMsg;

    FileCompressTarGzErrorEnum(String msg, String showMsg) {
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
