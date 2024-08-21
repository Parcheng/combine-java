package com.parch.combine.gui.core.call.refresh;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;

public class GUIRefreshFunction implements IGUIElementCallFunction {

    protected String id;
    protected String key;
    protected IGUIRefreshHandler handler;

    public GUIRefreshFunction(String key, IGUIRefreshHandler handler) {
        this.id = handler.getId();
        this.key = "REFRESH";
        this.handler = handler;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Object execute(Object... params) {
        if (params == null) {
            return false;
        }

        return handler.refresh(params);
    }
}
