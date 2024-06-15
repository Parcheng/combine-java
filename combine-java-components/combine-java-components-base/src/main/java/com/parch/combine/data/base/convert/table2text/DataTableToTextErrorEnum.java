package com.parch.combine.data.base.convert.table2text;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum DataTableToTextErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    DataTableToTextErrorEnum(String msg, String showMsg) {
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
