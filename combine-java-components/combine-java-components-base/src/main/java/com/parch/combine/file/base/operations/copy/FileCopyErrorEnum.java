package com.parch.combine.file.base.operations.copy;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileCopyErrorEnum implements IComponentError {

    FAIL("文件复制失败", "文件复制失败"),
    ;

    private String msg;

    private String showMsg;

    FileCopyErrorEnum(String msg, String showMsg) {
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
