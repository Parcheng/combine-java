package com.parch.combine.gui.base.build;

import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.vo.DataResult;

public abstract class AbsGUIControlComponent<T extends GUIControlInitConfig, R extends GUIControlLogicConfig> extends AbsComponent<T, R> {

    protected GUIElementManager guiElementManager;

    public AbsGUIControlComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public DataResult execute() {
        initGuiElementManager();
        String elementId = getLogicConfig().elementId();
        elementId = elementId == null ? getLogicConfig().id() : elementId;

        IGUIElement element = getElement(elementId);
        if (element == null) {
            return DataResult.fail(GUIControlErrorEnum.FAIL);
        }

        guiElementManager.register(elementId, element);

        return DataResult.success(true);
    }

    private void initGuiElementManager() {
        String flowContextId = ComponentContextHandler.getId();
        guiElementManager = GUIElementManagerHandler.getAndRegisterManager(flowContextId);
    }

    public abstract IGUIElement getElement(String elementId);
}
