package com.parch.combine.components.mail;

import com.parch.combine.core.component.spi.AbsGetComponents;

/**
 * 获取邮件组件实现类
 */
public class GetMailComponents extends AbsGetComponents {

    public GetMailComponents() {
        super("mail", "邮件", GetMailComponents.class);
    }
}
