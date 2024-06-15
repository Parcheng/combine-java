package com.parch.combine.components.call.flow;

import com.parch.combine.core.component.error.IComponentError;

public enum CallFlowErrorEnum implements IComponentError {

    FLOW_IS_NULL("流程不存在", "流程不存在"),
    FAIL("调用流程失败", "调用流程失败"),
    ;

    private String msg;

    private String showMsg;

    CallFlowErrorEnum(String msg, String showMsg) {
        this.msg = msg;
        this.showMsg = showMsg;

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
