package com.parch.combine.gui.components.operations;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.gui.base.operations.AbsGUIOperationComponent;
import com.parch.combine.gui.base.operations.value.get.GUIOperationValueGetInitConfig;
import com.parch.combine.gui.base.operations.value.get.GUIOperationValueGetLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "operations.value.get", name = "GUI元素获取值", logicConfigClass = GUIOperationValueGetLogicConfig.class, initConfigClass = GUIOperationValueGetInitConfig.class)
@ComponentResult(name = "GUI元素值")
public class GUIOperationValueGetComponent extends AbsGUIOperationComponent<GUIOperationValueGetInitConfig, GUIOperationValueGetLogicConfig> {

    public GUIOperationValueGetComponent() {
        super(GUIOperationValueGetInitConfig.class, GUIOperationValueGetLogicConfig.class);
    }

    @Override
    public DataResult execute(IGUIElement element) {
        return DataResult.success(element.getValue());
    }
}
