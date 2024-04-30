package com.parch.combine.common.exception;

public abstract class BaseException extends RuntimeException {
    private long code;
    private String errorMsg;
    private String showMsg;
    private String errorType;
    private Throwable throwable;

    protected BaseException(long code, String errorMsg, String showMsg, String errorType) {
        this(code, errorMsg, showMsg, errorType, (Throwable)null);
    }

    protected BaseException(long code, String errorMsg, String showMsg, String errorType, Throwable throwable) {
        super("[" + errorType + "]" + code + "-" + errorMsg, throwable);
        this.code = code;
        this.errorMsg = errorMsg;
        this.showMsg = showMsg;
        this.errorType = errorType;
        this.throwable = throwable;
    }

    public long getCode() {
        return this.code;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public String getShowMsg() {
        return this.showMsg;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public static String format(String format, Object... args) {
        String result = null;
        if (format != null) {
            result = String.format(format, args);
        }

        return result;
    }
}
