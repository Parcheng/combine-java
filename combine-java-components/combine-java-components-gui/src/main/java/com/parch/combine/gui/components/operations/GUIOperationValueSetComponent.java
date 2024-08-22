package com.parch.combine.gui.components.operations;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gui.base.operations.AbstractGUIOperationComponent;
import com.parch.combine.gui.base.operations.value.set.GUIOperationValueSetInitConfig;
import com.parch.combine.gui.base.operations.value.set.GUIOperationValueSetLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "operations.value.set", order = 300, name = "GUI元素设置值", logicConfigClass = GUIOperationValueSetLogicConfig.class, initConfigClass = GUIOperationValueSetInitConfig.class)
@ComponentResult(name = "是否设置成功")
public class GUIOperationValueSetComponent extends AbstractGUIOperationComponent<GUIOperationValueSetInitConfig, GUIOperationValueSetLogicConfig> {

    public GUIOperationValueSetComponent() {
        super(GUIOperationValueSetInitConfig.class, GUIOperationValueSetLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(IGUIElement element) {
        return ComponentDataResult.success(element.setValue(getLogicConfig().value()));
    }
}
