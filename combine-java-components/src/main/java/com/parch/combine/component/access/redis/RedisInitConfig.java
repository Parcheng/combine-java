package com.parch.combine.component.access.redis;

import com.parch.combine.core.base.InitConfig;

import java.util.List;


public class RedisInitConfig extends InitConfig {

    private List<String> nodes;

    private String password;

    private Pool pool;

    private Integer timeout;

    private Integer maxAttempts;

    private Boolean printCommand;

    public static class Pool {
        private Integer max;

        private Integer min;

        private Long timeout;

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Long getTimeout() {
            return timeout;
        }

        public void setTimeout(Long timeout) {
            this.timeout = timeout;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPrintCommand() {
        return printCommand;
    }

    public void setPrintCommand(Boolean printCommand) {
        this.printCommand = printCommand;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}
