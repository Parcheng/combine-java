package com.parch.combine.file.base.input.upload;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件异常信息
 */
public enum FileInputUploadErrorEnum implements IComponentError {

    DATA_ERROR("数据异常", "数据异常"),
    ;

    private String msg;

    private String showMsg;

    FileInputUploadErrorEnum(String msg, String showMsg) {
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
