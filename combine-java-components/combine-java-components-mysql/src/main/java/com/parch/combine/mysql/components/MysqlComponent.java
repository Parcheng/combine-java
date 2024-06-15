package com.parch.combine.mysql.components;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.settings.annotations.ComponentResultDesc;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.mysql.base.MysqlErrorEnum;
import com.parch.combine.mysql.base.MysqlInitConfig;
import com.parch.combine.mysql.base.MysqlLogicConfig;
import com.parch.combine.mysql.base.MysqlOperationHandler;

import java.util.Map;
import java.util.UUID;

@Component(key = "execute", name = "SQL执行组件", logicConfigClass = MysqlLogicConfig.class, initConfigClass = MysqlInitConfig.class)
@ComponentDesc({
        "MYSQL数据库相关操作，支持DDL和DML语句",
        "依赖 mysql-connector-j，推荐版本 8.0.33"
})
@ComponentResult(name = "SQL执行结果")
@ComponentResultDesc({
        "其中：",
        "INSERT_INCR语句：返回ID",
        "INSERT/UPDATE/DELETE语句：返回影响行（整数类型）",
        "SELECT语句返回：数组集合",
        "SELECT_ONE语句返回：对象结构",
        "SELECT_LIMIT语句返回分页对象结构，如下：",
        "{",
        "\tpage: 1",
        "\tpageSize: 10",
        "\tmaxPage: 20",
        "\ttotalCount: 200",
        "\tdata: {数组集合（实际的SQL查询结果集合）",
        "}"
})
public class MysqlComponent extends AbsComponent<MysqlInitConfig, MysqlLogicConfig> {

    /**
     * 默认的缓存连接Key
     */
    private static final String CONN_KEY = "SYSTEM_MYSQL_CONN_KEY";

    public MysqlComponent() {
        super(MysqlInitConfig.class, MysqlLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        // 上下文获取入参
        Map<String, Object> params = ComponentContextHandler.getParams();

        // 上下文获取连接池Key（用于获取mysql连接）
        Object connKeyObj = ComponentContextHandler.getRuntimeData(CONN_KEY);
        if (connKeyObj == null) {
            connKeyObj = UUID.randomUUID().toString();
            ComponentContextHandler.setRuntimeData(CONN_KEY, connKeyObj);
        }

        // 执行SQL逻辑
        return MysqlOperationHandler.execute(connKeyObj.toString(), params, getLogicConfig(), getInitConfig());
    }

    @Override
    public boolean end() {
        String connKey = null;
        boolean success = false;
        try {
            Object connKeyObj = ComponentContextHandler.getRuntimeData(CONN_KEY);
            connKey = connKeyObj == null ? null : connKeyObj.toString();

            DataResult result = ComponentContextHandler.getResultData(getLogicConfig().id());
            success = result != null && result.getSuccess();
        } catch (Exception e) {
            ComponentErrorHandler.print(MysqlErrorEnum.END_FUNCTION_ERROR, e);
            return false;
        } finally {
            MysqlOperationHandler.closeConnection(connKey, success);
        }

        return true;
    }
}
