package com.parch.combine.file.base.operations.delete;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileDeleteErrorEnum implements IComponentError {

    SOURCE_PATH_IS_NULL("源文件路径为空", "源文件路径为空"),
    TARGET_PATH_IS_NULL("目标文件路径为空", "目标文件路径为空"),
    FILE_NOT_EXIST("文件不存在", "文件不存在"),
    FAIL("文件复制失败", "文件复制失败"),
    ;

    private String msg;

    private String showMsg;

    FileDeleteErrorEnum(String msg, String showMsg) {
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
