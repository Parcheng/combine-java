package com.parch.combine.file.base.input.open;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileInputOpenErrorEnum implements IComponentError {
    PATH_IS_NULL("文件路径为空", "文件路径为空"),
    FILE_OVERLENGTH("文件超大", "文件超大"),
    FILE_DATA_SCARCITY("数据未全部读取", "数据未全部读取"),
    OPEN_ERROR("打开文件异常", "打开文件异常"),
    ;

    private String msg;

    private String showMsg;

    FileInputOpenErrorEnum(String msg, String showMsg) {
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
