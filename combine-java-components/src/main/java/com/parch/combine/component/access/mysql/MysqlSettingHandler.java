package com.parch.combine.component.access.mysql;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.handler.CompareGroupSettingHandler;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class MysqlSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("mysql", "MYSQL数据库组件", true, MysqlComponent.class);
        builder.addDesc("MYSQL数据库相关操作，支持DDL和DML语句");

        builder.addProperty(PropertyTypeEnum.INIT, "username", FieldTypeEnum.TEXT, "数据库用户名",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "password", FieldTypeEnum.TEXT, "数据库密码",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "port", FieldTypeEnum.TEXT, "数据库端口",   true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "dbName", FieldTypeEnum.TEXT, "数据库名称",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "host", FieldTypeEnum.TEXT, "数据库HOST地址", true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "pool", FieldTypeEnum.TEXT, "数据库连接池配置", false, false);
        builder.addPropertyDesc(PropertyTypeEnum.INIT, "pool", "不配置则不使用连接池");
        builder.addProperty(PropertyTypeEnum.INIT, "pool.max", FieldTypeEnum.TEXT, "数据库连接最大数量",  false, false);
        builder.addProperty(PropertyTypeEnum.INIT, "pool.min", FieldTypeEnum.TEXT, "数据库连接最小数量",  false, false);
        builder.addProperty(PropertyTypeEnum.INIT, "pool.timeout", FieldTypeEnum.TEXT, "数据库连接超时时间", false, false);
        builder.addProperty(PropertyTypeEnum.INIT, "pool", FieldTypeEnum.BOOLEAN, "是否打印SQL", false, false, "true");


        builder.addPropertyEg(PropertyTypeEnum.INIT, "username", "admin", "数据库用户名为admin");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "password", "123456", "数据库密码为123456");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "port", "3306", "数据库连接端口为3306");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "dbName", "test", "连接数据库名称为dbName");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "host", "127.0.0.1", "数据量主机地址是本机");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.max", "10", "连接池最大连接数10");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.min", "5", "连接池最小连接数5");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.timeout", "10000", "连接超时时间为10000毫秒");


        builder.addProperty(PropertyTypeEnum.LOGIC, "sqlType", FieldTypeEnum.SELECT, "SQL类型", true, false);
        builder.setPropertyOption(PropertyTypeEnum.LOGIC, "sqlType", Arrays.asList(SqlTypeEnum.values()));
        builder.addProperty(PropertyTypeEnum.LOGIC, "sql", FieldTypeEnum.TEXT, "SQL语句",false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "sql", "简单的SQL语句，注意：sql和sqlConfigs必须只配置一项");
        builder.addProperty(PropertyTypeEnum.LOGIC, "sqlConfigs", FieldTypeEnum.TEXT, "SQL语句配置",false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "sqlConfigs", "复杂的SQL语句配置，最终执行时会将所有满足条件的SQL语句拼接到一起，注意：sql和sqlConfigs必须只配置一项");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "sqlConfigs", "提示：如果不配置条件则认为满足条件，SQL语句（段）会生效");
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "sqlConfigs");
        builder.addProperty(PropertyTypeEnum.LOGIC, "sqlConfigs.sql", FieldTypeEnum.TEXT, "满足条件时会生效的SQL语句（段）", true, true);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "sqlType", "SELECT_ONE", "查询一条数据");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "sql", "select * from t_user limit 1", "要直接执行SQL语句");
        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder,"sqlConfigs");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "sqlConfigs.sql", "select * from t_user where 1=1 ", "要执行的SQL语句（段）");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "sqlConfigs.sql", "and name = ${name}", "要执行的SQL语句（段）");


        builder.setResult("SQL执行结果，其中：", false);
        builder.addResultDesc("INSERT/UPDATE/DELETE语句：返回影响行（整数类型）");
        builder.addResultDesc("SELECT语句返回：数组集合");
        builder.addResultDesc("SELECT_ONE语句返回：对象结构");
        builder.addResultDesc("SELECT_LIMIT语句返回分页对象结构，如下：");
        builder.addResultDesc("{");
        builder.addResultDesc("\tpage: 1");
        builder.addResultDesc("\tpageSize: 10");
        builder.addResultDesc("\tmaxPage: 20");
        builder.addResultDesc("\ttotalCount: 200");
        builder.addResultDesc("\tdata: {数组集合（实际的SQL查询结果集合）");
        builder.addResultDesc("}");

        return builder.get();
    }
}
