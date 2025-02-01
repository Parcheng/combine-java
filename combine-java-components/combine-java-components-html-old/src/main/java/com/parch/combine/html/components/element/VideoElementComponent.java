package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.VideoElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.video.register", order = 400, name = "视频元素模板配置注册组件", logicConfigClass = VideoElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class VideoElementComponent extends AbstractElementComponent<VideoElementLogicConfig> {

    /**
     * 构造器
     */
    public VideoElementComponent() {
        super(VideoElementLogicConfig.class, "SYSTEM.VIDEO");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
