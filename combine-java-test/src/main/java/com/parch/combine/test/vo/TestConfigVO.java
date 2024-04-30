package com.parch.combine.test.vo;

import java.util.List;
import java.util.Map;

/**
 * 检测配置VO
 */
public class TestConfigVO {

    /**
     * 领域
     */
    private String domain;

    /**
     * 函数
     */
    private String function;

    /**
     * 参数
     */
    private Map<String, Object> params;

    /**
     * 检测项配置
     */
    private List<TestConfigItemVO> items;

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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<TestConfigItemVO> getItems() {
        return items;
    }

    public void setItems(List<TestConfigItemVO> items) {
        this.items = items;
    }
}
