package com.parch.combine.gui.core.call.option;


import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.gui.base.build.GUIControlOptionConfig;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.IGUIElement;

import java.util.Map;

public abstract class AbstractGUIOptionCallFunction implements IGUIElementCallFunction {

    protected String id;
    protected String key;
    protected IGUIOptionHandler handler;

    protected AbstractGUIOptionCallFunction(String key, IGUIOptionHandler handler) {
        this.id = handler.getId();
        this.key = key.toUpperCase();
        this.handler = handler;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @SuppressWarnings("unchecked")
    protected GUIControlOptionConfig[] buildOptions(Object[] params) {
        GUIControlOptionConfig[] newOptions = new GUIControlOptionConfig[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                continue;
            }

            if (params[i] instanceof Map) {
                Map<String, Object> mapParam = (Map<String, Object>) params[i];
                Object text = mapParam.get("text");
                Object value = mapParam.get("value");
                if (text == null && value == null) {
                    continue;
                }

                newOptions[i] = new GUIControlOptionConfig();
                newOptions[i].setText(text == null ? value.toString() : text.toString());
                newOptions[i].setValue(value == null ? text.toString() : value.toString());
            } else {
                String value = params[i].toString();
                newOptions[i] = new GUIControlOptionConfig();
                newOptions[i].setText(value);
                newOptions[i].setValue(value);
            }
        }

        return newOptions;
    }

    protected void printError(String msg) {
        PrintUtil.printError("【GUI CALL FUNCTION】【" + id + "】【" + key + "】 ERROR: " + msg);
    }
}
