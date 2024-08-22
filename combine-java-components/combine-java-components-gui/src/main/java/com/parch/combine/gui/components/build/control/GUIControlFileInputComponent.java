package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.file.GUIControlFileInputInitConfig;
import com.parch.combine.gui.base.build.control.file.GUIControlFileInputLogicConfig;
import com.parch.combine.gui.base.build.control.file.GUIFileInputElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.file", order = 100, name = "GUI文件输入控件", logicConfigClass = GUIControlFileInputLogicConfig.class, initConfigClass = GUIControlFileInputInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlFileInputComponent extends AbstractGUIControlComponent<GUIControlFileInputInitConfig, GUIControlFileInputLogicConfig> {

    public GUIControlFileInputComponent() {
        super(GUIControlFileInputInitConfig.class, GUIControlFileInputLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlFileInputInitConfig initConfig = getInitConfig();
        GUIControlFileInputLogicConfig logicConfig = getLogicConfig();

        GUIFileInputElement.Config config = new GUIFileInputElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.columns = logicConfig.columns();
        config.chooseText = logicConfig.chooseText();
        config.events = logicConfig.events();
        return new GUIFileInputElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
