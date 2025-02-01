package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.AudioElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.audio.register", order = 300, name = "音频元素模板配置注册组件", logicConfigClass = AudioElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class AudioTemplateComponent extends AbstractTemplateComponent<AudioElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public AudioTemplateComponent() {
        super(AudioElementTemplateLogicConfig.class, "SYSTEM.AUDIO");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
