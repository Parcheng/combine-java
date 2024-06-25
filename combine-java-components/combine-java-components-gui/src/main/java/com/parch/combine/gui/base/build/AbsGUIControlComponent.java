package com.parch.combine.gui.base.build;

import com.parch.combine.gui.core.element.GUIElementManagerHandler;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.GUIElementManager;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.vo.DataResult;

public abstract class AbsGUIControlComponent<T extends GUIControlInitConfig, R extends GUIControlLogicConfig> extends AbsComponent<T, R> {

    protected String domain;
    protected GUIElementManager guiElementManager;
    protected String elementId;

    public AbsGUIControlComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public DataResult execute() {
        initGuiElementManager();
        IGUIElement element = this.getElement();
        if (element == null) {
            return DataResult.fail(GUIControlErrorEnum.FAIL);
        }

        guiElementManager.register(elementId, element);

        return DataResult.success(true);
    }

    private void initGuiElementManager() {
        this.domain = getLogicConfig().domain();
        this.guiElementManager = GUIElementManagerHandler.getAndRegisterManager(domain);

        this.elementId = getLogicConfig().elementId();
        this.elementId = elementId == null ? getLogicConfig().id() : elementId;
    }

    public abstract IGUIElement getElement();
}
