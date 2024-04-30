package com.parch.combine.components.access.mysql;

import com.parch.combine.core.error.IComponentError;

public enum MysqlErrorEnum implements IComponentError {

    LOAD_JDBC_ERROR("加载数据库驱动异常", "数据库驱动异常"),
    CONN_ERROR("数据库连接异常", "数据库异常"),
    SQL_TYPE_ERROR("SQL类型错误", "系统异常"),
    SQL_EXECUTE_ERROR("SQL执行异常", "数据库操作异常"),
    UNKNOWN_ERROR("未知异常", "系统异常"),
    END_FUNCTION_ERROR("结束函数执行异常", null),
    CONN_CLOSE_ERROR("连接关闭异常", null),
    STATEMENT_CLOSE_ERROR("Statement关闭异常", null),
    ;

    private String msg;

    private String showMsg;

    MysqlErrorEnum(String msg, String showMsg) {
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
