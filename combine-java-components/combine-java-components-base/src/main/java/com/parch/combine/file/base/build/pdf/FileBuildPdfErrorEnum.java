package com.parch.combine.file.base.build.pdf;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件异常信息
 */
public enum FileBuildPdfErrorEnum implements IComponentError {
    BUILD_ERROR("PDF文件构建异常","PDF文件构建异常"),
    ;

    private String msg;

    private String showMsg;

    FileBuildPdfErrorEnum(String msg, String showMsg) {
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
