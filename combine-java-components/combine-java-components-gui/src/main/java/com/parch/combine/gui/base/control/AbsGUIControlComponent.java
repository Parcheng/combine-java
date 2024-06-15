package com.parch.combine.gui.base.control;

import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.vo.DataResult;

public abstract class AbsGUIControlComponent<T extends GUIControlInitConfig, R extends GUIControlLogicConfig> extends AbsComponent<T, R> {

    public AbsGUIControlComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public DataResult execute() {
        IGUIElement element = getElement();
        if (element == null) {
            return DataResult.fail(GUIControlErrorEnum.FAIL);
        }

        String flowContextId = ComponentContextHandler.getId();
        GUIElementManager manager = GUIElementManagerHandler.getAndRegisterManager(flowContextId);

        String elementId = getLogicConfig().elementId();
        manager.register(elementId == null ? getLogicConfig().id() : elementId, element);

        // JPanel、JScrollPane、JSplitPane
        return DataResult.success(true);
    }

    public abstract IGUIElement getElement();
}
