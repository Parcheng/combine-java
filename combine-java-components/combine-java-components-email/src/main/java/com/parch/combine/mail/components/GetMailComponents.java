package com.parch.combine.mail.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

/**
 * 获取邮件组件实现类
 */
public class GetMailComponents extends AbstractGetComponents {

    public GetMailComponents() {
        super("mail", "邮件", GetMailComponents.class);
    }
}
