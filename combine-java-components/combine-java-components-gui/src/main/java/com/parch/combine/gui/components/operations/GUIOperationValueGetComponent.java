package com.parch.combine.gui.components.operations;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gui.base.operations.AbstractGUIOperationComponent;
import com.parch.combine.gui.base.operations.value.get.GUIOperationValueGetInitConfig;
import com.parch.combine.gui.base.operations.value.get.GUIOperationValueGetLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "operations.value.get", order = 300, name = "GUI元素获取值", logicConfigClass = GUIOperationValueGetLogicConfig.class, initConfigClass = GUIOperationValueGetInitConfig.class)
@ComponentResult(name = "GUI元素值")
public class GUIOperationValueGetComponent extends AbstractGUIOperationComponent<GUIOperationValueGetInitConfig, GUIOperationValueGetLogicConfig> {

    public GUIOperationValueGetComponent() {
        super(GUIOperationValueGetInitConfig.class, GUIOperationValueGetLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(IGUIElement element) {
        return ComponentDataResult.success(element.getValue());
    }
}
