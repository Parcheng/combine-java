package com.parch.combine.gui.components.operations;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gui.base.operations.AbstractGUIOperationComponent;
import com.parch.combine.gui.base.operations.visible.GUIOperationVisibleInitConfig;
import com.parch.combine.gui.base.operations.visible.GUIOperationVisibleLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "operations.visible", order = 300, name = "GUI可见性设置", logicConfigClass = GUIOperationVisibleLogicConfig.class, initConfigClass = GUIOperationVisibleInitConfig.class)
@ComponentResult(name = "异常或 true")
public class GUIOperationVisibleComponent extends AbstractGUIOperationComponent<GUIOperationVisibleInitConfig, GUIOperationVisibleLogicConfig> {

    public GUIOperationVisibleComponent() {
        super(GUIOperationVisibleInitConfig.class, GUIOperationVisibleLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(IGUIElement element) {
        GUIOperationVisibleLogicConfig logicConfig = getLogicConfig();
        element.setVisible(logicConfig.visible());
        return ComponentDataResult.success(true);
    }
}
