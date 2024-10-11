package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.VideoElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.video.register", order = 300, name = "视频元素模板配置注册组件", logicConfigClass = VideoElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class VideoTemplateComponent extends AbstractTemplateComponent<VideoElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public VideoTemplateComponent() {
        super(VideoElementTemplateLogicConfig.class, "SYSTEM.VIDEO");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
