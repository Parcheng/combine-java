package com.parch.combine.component.access.mysql;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.PrintHelper;
import com.parch.combine.core.tools.pool.DbConnPoolHandler;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.tools.sql.SqlHelper;
import com.parch.combine.common.util.TypeConversionUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Mysql操作处理器
 */
public class MysqlOperationHandler {

    public static final String CURR_PAGE_NAME = "page";
    public static final String PAGE_SIZE_NAME = "pageSize";
    public static final String COUNT_NAME = "count";

    /**
     * 连接池
     */
    private static Map<String, Connection> CONN_POOL = new HashMap<>();

    /**
     * 执行Sql
     *
     * @param connKey 连接Key
     * @param params 入参
     * @param logicConfig 逻辑配置
     * @param initConfig 初始化配置
     * @return 结果集
     */
    public static DataResult execute(String connKey, Map<String, Object> params, MysqlLogicConfig logicConfig, MysqlInitConfig initConfig) {
        Connection conn;
        try {
            conn = getConnection(connKey, initConfig);
            conn.setAutoCommit(false);
            return execute(conn, params, logicConfig, initConfig);
        } catch (SQLException e) {
            ComponentErrorHandler.print(MysqlErrorEnum.CONN_ERROR, e);
            return DataResult.fail(MysqlErrorEnum.CONN_ERROR);
        } catch (ClassNotFoundException e) {
            ComponentErrorHandler.print(MysqlErrorEnum.LOAD_JDBC_ERROR, e);
            return DataResult.fail(MysqlErrorEnum.LOAD_JDBC_ERROR);
        }
    }

    /**
     * 执行SQL
     *
     * @param conn 连接对象
     * @param params 入参
     * @param logicConfig 逻辑配置
     * @return 结果集
     */
    public static DataResult execute(Connection conn, Map<String, Object> params, MysqlLogicConfig logicConfig, MysqlInitConfig initConfig) {
        DataResult result;

        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement psCount = null;
        ResultSet rsCount = null;
        try {
            List<Object> sqlParams = new ArrayList<>();
            String sqlStr = SqlHelper.buildSql(logicConfig.getSqlConfigs());
            String changeSql = SqlHelper.replaceByParam(sqlStr, sqlParams);
            if (initConfig.getPrintSql()) {
                PrintHelper.printSql(changeSql);
            }

            SqlTypeEnum sqlType = SqlTypeEnum.get(logicConfig.getSqlType());
            switch (sqlType) {
                case INSERT:
                case UPDATE:
                case DELETE:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    result =  DataResult.success(ps.executeUpdate());
                    break;
                case SELECT:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    rs = ps.executeQuery();
                    List<Map<String, Object>> dataList = getResultData(rs);
                    result =  DataResult.success(dataList);
                    break;
                case SELECT_ONE:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    rs = ps.executeQuery();
                    dataList = getResultData(rs);
                    result =  DataResult.success(dataList.size() > 0 ? dataList.get(0) : null);
                    break;
                case SELECT_LIMIT:
                    // 分页查询
                    String limitSql = SqlHelper.setLimit(changeSql, CURR_PAGE_NAME, PAGE_SIZE_NAME);
                    ps = getPreparedStatement(conn, limitSql, sqlParams);
                    rs = ps.executeQuery();
                    dataList = getResultData(rs);

                    // count 查询
                    String countSql = SqlHelper.replaceCount(changeSql, COUNT_NAME);
                    PrintHelper.printSql(countSql);
                    psCount = getPreparedStatement(conn, countSql, sqlParams);
                    rsCount = psCount.executeQuery();
                    List<Map<String, Object>> countDataList = getResultData(rsCount);

                    // 组装结果集
                    int currPage = TypeConversionUtil.parseInt(params.get(CURR_PAGE_NAME), 0);
                    int pageSize = TypeConversionUtil.parseInt(params.get(PAGE_SIZE_NAME), 0);
                    int count = TypeConversionUtil.parseInt(countDataList.get(0).get(COUNT_NAME), 0);
                    result = DataResult.success(dataList, currPage, pageSize, count);
                    break;
                case DDL:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    ps.execute();
                    result =  DataResult.success(true);
                    break;
                default:
                    ComponentErrorHandler.print(MysqlErrorEnum.SQL_TYPE_ERROR);
                    result = DataResult.fail(MysqlErrorEnum.SQL_TYPE_ERROR);
                    break;
            }

            return result;
        } catch (SQLException e) {
            ComponentErrorHandler.print(MysqlErrorEnum.SQL_EXECUTE_ERROR, e);
            result = DataResult.fail(MysqlErrorEnum.SQL_EXECUTE_ERROR);
        } catch (Exception e) {
            ComponentErrorHandler.print(MysqlErrorEnum.UNKNOWN_ERROR, e);
            result = DataResult.fail(MysqlErrorEnum.UNKNOWN_ERROR);
        } finally {
            closeStatement(ps, rs);
            closeStatement(psCount, rsCount);
        }

        return result;
    }

    /**
     * 获取结果集
     *
     * @param rs ResultSet对象
     * @return 结果集
     * @throws SQLException 异常
     */
    private static List<Map<String, Object>> getResultData(ResultSet rs) throws SQLException {
        List<Map<String, Object>> dataList = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            dataList.add(rowData);
        }
        return dataList;
    }

    /**
     * 获取连接对象
     *
     * @param connKey 连接Key
     * @param initConfig 初始化配置
     * @return 连接对象
     * @throws SQLException 异常
     */
    private static Connection getConnection(String connKey, MysqlInitConfig initConfig) throws SQLException, ClassNotFoundException {
        Connection conn = CONN_POOL.get(connKey);
        if (conn != null) {
            return conn;
        }

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://"+initConfig.getHost()+":"+initConfig.getPort()+"/"+initConfig.getDbName();
        if (initConfig.getPool() == null) {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, initConfig.getUsername(), initConfig.getPassword());
        } else {
            String key = "Mysql_" + (CheckEmptyUtil.isEmpty(initConfig.getId()) ? CheckEmptyUtil.EMPTY : initConfig.getId());
            DataSource data = DbConnPoolHandler.getPool(key);
            if (data == null) {
                MysqlInitConfig.Pool poolConfig = initConfig.getPool();
                data = DbConnPoolHandler.initAndGet(key, url, initConfig.getUsername(), initConfig.getPassword(), driver, poolConfig.getMax(), poolConfig.getMin(), poolConfig.getTimeout());
            }
            conn = data.getConnection();
        }

        CONN_POOL.put(connKey, conn);
        return conn;
    }

    /**
     * 获取 PreparedStatement 对象
     *
     * @param conn 连接对象
     * @param sql 执行的SQL
     * @param sqlParams SQL的参数
     * @return PreparedStatement 对象
     * @throws SQLException 异常
     */
    private static PreparedStatement getPreparedStatement(Connection conn, String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < sqlParams.size(); i++) {
            ps.setObject(i+1, sqlParams.get(i));
        }
        return ps;
    }

    /**
     * 关闭连接
     *
     * @param connKey 连接Key
     */
    public static void closeConnection(String connKey, boolean isCommit) {
        try {
            Connection conn = CONN_POOL.get(connKey);
            if (conn != null) {
                if (isCommit) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.close();
                CONN_POOL.remove(connKey);
            }
        } catch (SQLException e) {
            ComponentErrorHandler.print(MysqlErrorEnum.CONN_CLOSE_ERROR, e);
        }
    }

    /**
     * 关闭Statement
     *
     * @param ps PreparedStatement对象
     * @param rs ResultSet对象
     */
    private static void closeStatement(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            ComponentErrorHandler.print(MysqlErrorEnum.STATEMENT_CLOSE_ERROR, e);
        }
    }
}
