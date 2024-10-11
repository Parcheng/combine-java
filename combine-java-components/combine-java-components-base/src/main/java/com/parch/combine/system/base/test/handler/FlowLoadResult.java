package com.parch.combine.system.base.test.handler;

import com.parch.combine.system.base.test.LogLevelEnum;

import java.util.List;

public class FlowLoadResult {

    private String flowKey;

    private boolean success;

    private List<InitLogInfo> logs;

    public static class InitLogInfo {

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
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<InitLogInfo> getLogs() {
        return logs;
    }

    public void setLogs(List<InitLogInfo> logs) {
        this.logs = logs;
    }
}
