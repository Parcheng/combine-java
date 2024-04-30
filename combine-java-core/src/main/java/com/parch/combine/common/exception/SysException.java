package com.parch.combine.common.exception;

public class SysException extends BaseException {
    public SysException(IBaseError baseError, Object... args) {
        super(500, BaseException.format(baseError.getMsg(), args), BaseException.format(baseError.getShowMsg(), args), "sys");
    }

    public SysException(IBaseError baseError, Throwable throwable, Object... args) {
        super(500, BaseException.format(baseError.getMsg(), args), BaseException.format(baseError.getShowMsg(), args), "sys", throwable);
    }
}
