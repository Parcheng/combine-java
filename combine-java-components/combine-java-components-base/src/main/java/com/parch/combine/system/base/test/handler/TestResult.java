package com.parch.combine.system.base.test.handler;

import com.parch.combine.system.base.test.LogLevelEnum;

import java.util.List;

public class TestResult {

    private String domain;

    private String function;

    private boolean success;

    private List<TestLogInfo> logs;

    public static class TestLogInfo {

        private String itemId;

        private LogLevelEnum level;

        private String msg;

        public LogLevelEnum getLevel() {
            return level;
        }

        public void setLevel(LogLevelEnum level) {
            this.level = level;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<TestLogInfo> getLogs() {
        return logs;
    }

    public void setLogs(List<TestLogInfo> logs) {
        this.logs = logs;
    }
}
