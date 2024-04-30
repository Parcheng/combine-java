package com.parch.combine.component.mail;

import com.parch.combine.component.mail.receive.MailReceiveSettingHandler;
import com.parch.combine.component.mail.send.MailSendSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取邮件组件实现类
 */
public class GetMailComponents extends AbsGetComponents {

    public GetMailComponents() {
        super("mail", "邮件");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(MailSendSettingHandler.get());
        setting.add(MailReceiveSettingHandler.get());
        return setting;
    }
}
