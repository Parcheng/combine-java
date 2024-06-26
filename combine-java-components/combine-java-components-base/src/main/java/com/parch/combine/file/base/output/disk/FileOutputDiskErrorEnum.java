package com.parch.combine.file.base.output.disk;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileOutputDiskErrorEnum implements IComponentError {

    TARGET_PATH_IS_NULL("目标文件路径为空", "目标文件路径为空"),
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
