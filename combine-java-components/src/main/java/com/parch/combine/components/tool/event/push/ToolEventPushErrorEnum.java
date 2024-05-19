package com.parch.combine.components.tool.event.push;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum ToolEventPushErrorEnum implements IComponentError {

    FAIL("推送事件消息异常", "推送事件消息失败");

    private String msg;

    private String showMsg;

    ToolEventPushErrorEnum(String msg, String showMsg) {
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
