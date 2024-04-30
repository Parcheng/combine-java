package com.parch.combine.component.access.mysql;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Mysql组件
 */
public class MysqlComponent extends AbsComponent<MysqlInitConfig, MysqlLogicConfig> {

    /**
     * 默认的缓存连接Key
     */
    private static final String CONN_KEY = "SYSTEM_MYSQL_CONN_KEY";

    public MysqlComponent() {
        super(MysqlInitConfig.class, MysqlLogicConfig.class);
    }

    @Override
    public List<String> init() {
        // 检查初始化配置
        List<String> checkMsg = new ArrayList<>();
        MysqlInitConfig initConfig = getInitConfig();
        if (initConfig.getPool() != null) {
            if (initConfig.getPool().getMax() == null) {
                initConfig.getPool().setMax(20);
            }
            if (initConfig.getPool().getMin() == null) {
                initConfig.getPool().setMin(5);
            }
            if (initConfig.getPool().getTimeout() == null) {
                initConfig.getPool().setTimeout(30000L);
            }
            if (initConfig.getPrintSql() == null) {
                initConfig.setPrintSql(true);
            }
        }

        if (CheckEmptyUtil.isAnyEmpty(initConfig.getUsername(), initConfig.getPassword(), initConfig.getHost(), initConfig.getDbName(), initConfig.getPort())) {
            checkMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "数据库配置信息缺失"));
        }

        // 检查逻辑配置
        MysqlLogicConfig logicConfig = getLogicConfig();
        if (SqlTypeEnum.get(logicConfig.getSqlType()) == SqlTypeEnum.NONE) {
            checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "SQL类型不合规"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getSqlConfigs())) {
            checkMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "SQL语句不能为空"));
        }

        return checkMsg;
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

            DataResult result = ComponentContextHandler.getResultData(getLogicConfig().getId());
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
