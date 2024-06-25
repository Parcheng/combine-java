package com.parch.combine.gui.components.operations;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.gui.base.operations.AbsGUIOperationComponent;
import com.parch.combine.gui.base.operations.call.GUIOperationCallInitConfig;
import com.parch.combine.gui.base.operations.call.GUIOperationCallLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

import java.util.HashMap;
import java.util.List;

@Component(key = "operations.call", name = "GUI元素函数调用", logicConfigClass = GUIOperationCallLogicConfig.class, initConfigClass = GUIOperationCallInitConfig.class)
@ComponentResult(name = "调用结果")
public class GUIOperationCallComponent extends AbsGUIOperationComponent<GUIOperationCallInitConfig, GUIOperationCallLogicConfig> {

    public GUIOperationCallComponent() {
        super(GUIOperationCallInitConfig.class, GUIOperationCallLogicConfig.class);
    }

    @Override
    public DataResult execute(IGUIElement element) {
        GUIOperationCallLogicConfig logicConfig = getLogicConfig();
        Object[] params = logicConfig.params();

        Object result;
        if (params == null || params.length == 0) {
            result = element.call(logicConfig.key());
        } else {
            result = element.call(logicConfig.key(), params);
        }

        return DataResult.success(result);
    }
}
