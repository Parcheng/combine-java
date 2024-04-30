package com.parch.combine.components.access.redis.command;

/**
 * redis命令结果
 */
public class RedisCommandResult {

    private Boolean success;

    private String msg;

    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static RedisCommandResult success(Object data) {
        RedisCommandResult result = new RedisCommandResult();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static RedisCommandResult fail(String msg) {
        RedisCommandResult result = new RedisCommandResult();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}
