package com.parch.combine.components.gui.build;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import javax.swing.*;

@Component(key = "build", name = "GUI构建组件", logicConfigClass = GUIBuildLogicConfig.class, initConfigClass = GUIBuildInitConfig.class)
@ComponentResult(name = "构建失败的错误信息或 true")
public class GUIBuildComponent extends AbsComponent<GUIBuildInitConfig, GUIBuildLogicConfig> {

    public GUIBuildComponent() {
        super(GUIBuildInitConfig.class, GUIBuildLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        GUIBuildLogicConfig logicConfig = getLogicConfig();

        GUIFrameBuilder builder = new GUIFrameBuilder();
        builder.setId(ComponentContextHandler.getId());
        builder.setTitle(logicConfig.title());
        builder.setTopElements(logicConfig.topElements());
        builder.setBottomElement(logicConfig.bottomElement());
        builder.setLeftElement(logicConfig.leftElement());
        builder.setRightElement(logicConfig.rightElement());
        builder.setCenterElements(logicConfig.centerElements());
        builder.setClose(logicConfig.close());
        builder.setVisible(logicConfig.visible());

        try {
            SwingUtilities.invokeLater(builder::build);
        } catch (Exception e) {
            ComponentErrorHandler.print(GUIBuildErrorEnum.FAIL, e);
            return DataResult.fail(GUIBuildErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
