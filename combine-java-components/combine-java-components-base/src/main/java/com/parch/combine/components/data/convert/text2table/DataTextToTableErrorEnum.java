package com.parch.combine.components.data.convert.text2table;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum DataTextToTableErrorEnum implements IComponentError {

    ;

    private String msg;

    private String showMsg;

    DataTextToTableErrorEnum(String msg, String showMsg) {
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
