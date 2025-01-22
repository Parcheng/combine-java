package com.parch.combine.mysql.base.execute;

import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface MysqlInitConfig extends IInitConfig {
    
    @Field(key = "username", name = "数据库用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "admin", desc = "数据库用户名为admin")
    String username();

    @Field(key = "password", name = "数据库密码", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "123456", desc = "数据库密码为123456")
    String password();

    @Field(key = "port", name = "数据库端口", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "3306", desc = "数据库连接端口为3306")
    String port();

    @Field(key = "dbName", name = "数据库名称", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "test", desc = "连接数据库名称为test")
    String dbName();

    @Field(key = "host", name = "数据库HOST地址", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "127.0.0.1", desc = "数据库主机地址是本机")
    String host();

    @Field(key = "pool", name = "数据库连接池配置", type = FieldTypeEnum.CONFIG)
    @FieldDesc("不配置则不使用连接池")
    @FieldObject(Pool.class)
    Pool pool();

    @Field(key = "paramNameConfig", name = "参数名称配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(ParamNameConfig.class)
    ParamNameConfig paramNameConfig();

    @Field(key = "printSql", name = "是否打印SQL", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean printSql();

    interface Pool {

        @Field(key = "max", name = "数据库连接最大数量", type = FieldTypeEnum.NUMBER, defaultValue = "20")
        @FieldEg(eg = "10", desc = "连接池最大连接数10")
        Integer max();

        @Field(key = "min", name = "数据库连接最小数量", type = FieldTypeEnum.NUMBER, defaultValue = "5")
        @FieldEg(eg = "5", desc = "连接池最小连接数5")
        Integer min();

        @Field(key = "timeout", name = "数据库连接超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "30000")
        @FieldEg(eg = "10000", desc = "连接超时时间为10000毫秒")
        Long timeout();
    }

    interface ParamNameConfig {

        String CURR_PAGE_NAME = "page";
        String PAGE_SIZE_NAME = "pageSize";
        String COUNT_NAME = "totalCount";

        @Field(key = "currPage", name = "当前页参数名", type = FieldTypeEnum.TEXT, defaultValue = CURR_PAGE_NAME)
        String currPage();

        @Field(key = "pageSize", name = "每页条数参数名", type = FieldTypeEnum.TEXT, defaultValue = PAGE_SIZE_NAME)
        String pageSize();

        @Field(key = "totalCount", name = "数据总条数参数名", type = FieldTypeEnum.TEXT, defaultValue = COUNT_NAME)
        String totalCount();
    }
}
