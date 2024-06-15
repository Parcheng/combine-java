package com.parch.combine.file.base.output.download;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileOutputDownloadErrorEnum implements IComponentError {

    DATA_IS_NULL("数据不存在", "数据异常"),
    FILE_NAME_IS_NULL("文件名为空", "文件名为空"),
    FAIL("文件写入异常", "文件写入失败"),
    ;

    private String msg;

    private String showMsg;

    FileOutputDownloadErrorEnum(String msg, String showMsg) {
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
