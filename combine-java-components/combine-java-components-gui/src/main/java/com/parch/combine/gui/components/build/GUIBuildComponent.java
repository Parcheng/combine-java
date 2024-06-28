package com.parch.combine.gui.components.build;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.gui.base.build.frame.GUIBuildErrorEnum;
import com.parch.combine.gui.base.build.frame.GUIBuildInitConfig;
import com.parch.combine.gui.base.build.frame.GUIBuildLogicConfig;
import com.parch.combine.gui.base.build.frame.GUIFrameBuilder;
import javax.swing.SwingUtilities;

@Component(key = "build", name = "GUI构建组件", logicConfigClass = GUIBuildLogicConfig.class, initConfigClass = GUIBuildInitConfig.class)
@ComponentResult(name = "构建失败的错误信息或 true")
public class GUIBuildComponent extends AbstractComponent<GUIBuildInitConfig, GUIBuildLogicConfig> {

    public GUIBuildComponent() {
        super(GUIBuildInitConfig.class, GUIBuildLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        GUIBuildLogicConfig logicConfig = getLogicConfig();
        GUIBuildInitConfig initConfig = getInitConfig();

        GUIFrameBuilder builder = new GUIFrameBuilder(initConfig.template());
        builder.setDomain(logicConfig.domain());
        builder.setIcon(logicConfig.icon());
        builder.setTitle(logicConfig.title());
        builder.setWidth(logicConfig.width());
        builder.setHeight(logicConfig.height());
        builder.setDistanceLeft(logicConfig.distanceLeft());
        builder.setDistanceTop(logicConfig.distanceTop());
        builder.setResizable(logicConfig.resizable());
        builder.setClose(logicConfig.close());
        builder.setVisible(logicConfig.visible());

        GUIBuildLogicConfig.ElementGroupConfig elements = logicConfig.elements();
        builder.setTopElements(elements.top());
        builder.setBottomElement(elements.bottom());
        builder.setLeftElement(elements.left());
        builder.setRightElement(elements.right());
        builder.setCenterElements(elements.center());

        try {
            SwingUtilities.invokeLater(builder::build);
        } catch (Exception e) {
            ComponentErrorHandler.print(GUIBuildErrorEnum.FAIL, e);
            return DataResult.fail(GUIBuildErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
