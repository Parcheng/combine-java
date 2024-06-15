package com.parch.combine.mysql.components;

import com.parch.combine.core.component.spi.AbsGetComponents;

public class GetMysqlComponents extends AbsGetComponents {

    public GetMysqlComponents() {
        super("mysql", "Mysql", GetMysqlComponents.class);
    }
}
