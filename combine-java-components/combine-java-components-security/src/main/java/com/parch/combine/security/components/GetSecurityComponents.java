package com.parch.combine.security.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

public class GetSecurityComponents extends AbstractGetComponents {

    public GetSecurityComponents() {
        super("security", "加解密", GetSecurityComponents.class);
    }
}
