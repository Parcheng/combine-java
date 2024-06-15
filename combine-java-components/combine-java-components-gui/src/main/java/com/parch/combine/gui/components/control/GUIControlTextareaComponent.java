package com.parch.combine.gui.components.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.textarea.GUIControlTextareaInitConfig;
import com.parch.combine.gui.base.control.textarea.GUIControlTextareaLogicConfig;
import com.parch.combine.gui.base.control.textarea.GUITextareaElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.textarea", name = "GUI多行文本输入控件", logicConfigClass = GUIControlTextareaLogicConfig.class, initConfigClass = GUIControlTextareaInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTextareaComponent extends AbsGUIControlComponent<GUIControlTextareaInitConfig, GUIControlTextareaLogicConfig> {

    public GUIControlTextareaComponent() {
        super(GUIControlTextareaInitConfig.class, GUIControlTextareaLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTextareaInitConfig initConfig = getInitConfig();
        GUIControlTextareaLogicConfig logicConfig = getLogicConfig();

        GUITextareaElement.Config config = new GUITextareaElement.Config();
        config.value = logicConfig.value();
        config.columns = logicConfig.columns();
        config.isWrapStyleWord = logicConfig.isWrapStyleWord();
        return new GUITextareaElement(initConfig.template(), config);
    }
}
