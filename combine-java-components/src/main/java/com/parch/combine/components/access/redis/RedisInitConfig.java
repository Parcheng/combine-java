package com.parch.combine.components.access.redis;

import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import java.util.List;


public class RedisInitConfig extends InitConfig {

    @ComponentField(key = "nodes", name = "节点集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    @ComponentFieldDesc("格式要求：127.0.0.1:8888")
    @ComponentFieldEg(eg = "[\"127.0.0.1:8888\", \"127.0.0.1:8889\"]", desc = "两个节点，分别为本机的 8888 和 8889 端口")
    private List<String> nodes;

    @ComponentField(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "123456", desc = "密码为123456")
    private String password;

    @ComponentField(key = "pool", name = "连接池配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = Pool.class)
    private Pool pool;

    @ComponentField(key = "timeout", name = "超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "2000")
    @ComponentFieldEg(eg = "6000", desc = "超时时间为 6 秒")
    private Integer timeout;

    @ComponentField(key = "maxAttempts", name = "最大重试次数", type = FieldTypeEnum.NUMBER, defaultValue = "10")
    @ComponentFieldEg(eg = "10", desc = "最大重试次数 10 次")
    private Integer maxAttempts;

    @ComponentField(key = "printCommand", name = "是否打印命令信息", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean printCommand;

    public static class Pool {

        @ComponentField(key = "max", name = "连接最大数量", type = FieldTypeEnum.NUMBER, defaultValue = "10")
        @ComponentFieldEg(eg = "10", desc = "连接池最大连接数10")
        private Integer max;

        @ComponentField(key = "min", name = "连接最小数量", type = FieldTypeEnum.NUMBER, defaultValue = "5")
        @ComponentFieldEg(eg = "5", desc = "连接池最小连接数5")
        private Integer min;

        @ComponentField(key = "timeout", name = "连接超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "10000")
        @ComponentFieldEg(eg = "10000", desc = "连接超时时间为10000毫秒")
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
        if (getTimeout() == null) {
            setTimeout(2000);
        }
        if (getMaxAttempts() == null) {
            setMaxAttempts(10);
        }
        if (getPool() != null) {
            if (getPool().getMax() == null) {
                getPool().setMax(10);
            }
            if (getPool().getMin() == null) {
                getPool().setMin(5);
            }
            if (getPool().getTimeout() == null) {
                getPool().setTimeout(10000L);
            }
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
