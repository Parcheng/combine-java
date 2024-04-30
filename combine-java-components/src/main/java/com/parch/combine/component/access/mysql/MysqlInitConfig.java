package com.parch.combine.component.access.mysql;

import com.parch.combine.core.base.InitConfig;


public class MysqlInitConfig extends InitConfig {

    private String username;
    private String password;
    private String port;
    private String dbName;
    private String host;
    private Pool pool;
    private Boolean printSql;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Boolean getPrintSql() {
        return printSql;
    }

    public void setPrintSql(Boolean printSql) {
        this.printSql = printSql;
    }
}
