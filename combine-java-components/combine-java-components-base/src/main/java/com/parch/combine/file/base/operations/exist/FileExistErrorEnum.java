package com.parch.combine.file.base.operations.exist;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileExistErrorEnum implements IComponentError {

    SOURCE_PATH_IS_NULL("源文件路径为空", "源文件路径为空"),
    FAIL("文件检查失败", "文件检查失败"),
    ;

    private String msg;

    private String showMsg;

    FileExistErrorEnum(String msg, String showMsg) {
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
