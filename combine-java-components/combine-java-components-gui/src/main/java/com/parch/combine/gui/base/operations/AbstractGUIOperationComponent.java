package com.parch.combine.gui.base.operations;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;

public abstract class AbstractGUIOperationComponent<T extends IInitConfig, R extends GUIOperationConfig> extends AbstractComponent<T, R> {

    public AbstractGUIOperationComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public ComponentDataResult execute() {
        GUIElementManager guiElementManager = GUIElementManagerHandler.getManager(getLogicConfig().domain());
        if (guiElementManager == null) {
            return ComponentDataResult.fail(GUIOperationErrorEnum.ELEMENT_DOMAIN_NOT_EXIST);
        }

        IGUIElement element = guiElementManager.get(getLogicConfig().elementId());
        if (element == null) {
            return ComponentDataResult.fail(GUIOperationErrorEnum.ELEMENT_NOT_EXIST);
        }

        return execute(element);
    }

    public abstract ComponentDataResult execute(IGUIElement element);
}
