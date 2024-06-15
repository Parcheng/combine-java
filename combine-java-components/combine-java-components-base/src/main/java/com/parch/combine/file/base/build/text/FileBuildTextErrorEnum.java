package com.parch.combine.file.base.build.text;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileBuildTextErrorEnum implements IComponentError {
    DATA_IS_NULL("数据为空","数据为空"),
    ;

    private String msg;

    private String showMsg;

    FileBuildTextErrorEnum(String msg, String showMsg) {
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
