package com.parch.combine.components.file.build.table;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileBuildTableErrorEnum implements IComponentError {
    DATA_IS_NULL("数据为空","数据为空"),
    DATA_ERROR("数据非对象","数据异常")
    ;

    private String msg;

    private String showMsg;

    FileBuildTableErrorEnum(String msg, String showMsg) {
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
