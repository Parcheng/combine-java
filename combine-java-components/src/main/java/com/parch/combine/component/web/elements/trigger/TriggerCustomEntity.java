package com.parch.combine.component.web.elements.trigger;

import java.util.List;

/**
 * 配置类
 */
public class TriggerCustomEntity extends TriggerEntity {

    private String functionName;

    private List<Object> functionParams;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Object> getFunctionParams() {
        return functionParams;
    }

    public void setFunctionParams(List<Object> functionParams) {
        this.functionParams = functionParams;
    }
}
