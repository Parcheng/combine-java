package com.parch.combine.components.access.mysql;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class MysqlInitConfig extends InitConfig {
    
    @Field(key = "username", name = "数据库用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "admin", desc = "数据库用户名为admin")
    private String username;

    @Field(key = "password", name = "数据库密码", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "123456", desc = "数据库密码为123456")
    private String password;

    @Field(key = "port", name = "数据库端口", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "3306", desc = "数据库连接端口为3306")
    private String port;

    @Field(key = "dbName", name = "数据库名称", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "test", desc = "连接数据库名称为test")
    private String dbName;

    @Field(key = "host", name = "数据库HOST地址", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "127.0.0.1", desc = "数据库主机地址是本机")
    private String host;

    @Field(key = "pool", name = "数据库连接池配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = Pool.class)
    @FieldDesc("不配置则不使用连接池")
    private Pool pool;

    @Field(key = "printSql", name = "是否打印SQL", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean printSql;

    public static class Pool {

        @Field(key = "max", name = "数据库连接最大数量", type = FieldTypeEnum.NUMBER)
        @FieldEg(eg = "10", desc = "连接池最大连接数10")
        private Integer max;

        @Field(key = "min", name = "数据库连接最小数量", type = FieldTypeEnum.NUMBER)
        @FieldEg(eg = "5", desc = "连接池最小连接数5")
        private Integer min;

        @Field(key = "timeout", name = "数据库连接超时时间", type = FieldTypeEnum.NUMBER)
        @FieldEg(eg = "10000", desc = "连接超时时间为10000毫秒")
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

    @Override
    public void init() {
        if (this.pool != null) {
            if (this.pool.getMax() == null) {
                this.pool.setMax(20);
            }
            if (this.pool.getMin() == null) {
                this.pool.setMin(5);
            }
            if (this.pool.getTimeout() == null) {
                this.pool.setTimeout(30000L);
            }
        }
        if (this.printSql == null) {
            this.printSql = true;
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
