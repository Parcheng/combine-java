package com.parch.combine.component.file.output.disk;

import com.parch.combine.core.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileOutputDiskErrorEnum implements IComponentError {

    DATA_IS_NULL("数据不存在", "数据异常"),
    FAIL("文件写入异常", "文件写入失败"),
    ;

    private String msg;

    private String showMsg;

    FileOutputDiskErrorEnum(String msg, String showMsg) {
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
