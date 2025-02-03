package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.AudioElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.audio.register", order = 400, name = "音频元素模板配置注册组件", logicConfigClass = AudioElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class AudioElementComponent extends AbstractElementComponent<AudioElementLogicConfig> {

    /**
     * 构造器
     */
    public AudioElementComponent() {
        super(AudioElementLogicConfig.class, "SYSTEM.AUDIO");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
