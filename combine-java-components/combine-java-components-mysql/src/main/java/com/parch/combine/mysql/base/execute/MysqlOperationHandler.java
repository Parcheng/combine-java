package com.parch.combine.mysql.base.execute;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;
import com.parch.combine.core.component.tools.conn.DbConnPoolTool;
import com.parch.combine.core.component.tools.sql.SqlItem;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.tools.sql.SqlTool;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * @param requestId 请求ID
     * @param connKey 连接Key
     * @param params 入参
     * @param logicConfig 逻辑配置
     * @param initConfig 初始化配置
     * @return 结果集
     */
    public static ComponentDataResult execute(String requestId, String connKey, Map<String, Object> params, MysqlLogicConfig logicConfig, MysqlInitConfig initConfig) {
        Connection conn;
        try {
            conn = getConnection(requestId, connKey, initConfig);
            conn.setAutoCommit(false);
            return execute(conn, params, logicConfig, initConfig);
        } catch (SQLException e) {
            PrintErrorHelper.print(MysqlErrorEnum.CONN_ERROR, e);
            return ComponentDataResult.fail(MysqlErrorEnum.CONN_ERROR);
        } catch (ClassNotFoundException e) {
            PrintErrorHelper.print(MysqlErrorEnum.LOAD_JDBC_ERROR, e);
            return ComponentDataResult.fail(MysqlErrorEnum.LOAD_JDBC_ERROR);
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
    public static ComponentDataResult execute(Connection conn, Map<String, Object> params, MysqlLogicConfig logicConfig, MysqlInitConfig initConfig) {
        ComponentDataResult result;

        SqlItem[] sqlItems = logicConfig.sqlConfigs();
        if (CheckEmptyUtil.isEmpty(sqlItems)) {
            String[] sqlArr = logicConfig.sql();
            String sql = StringUtil.join(sqlArr, " ");
            if (sql != null) {
                sqlItems = new SqlItem[]{new SqlItem() {
                    @Override public String sql() { return sql; }
                    @Override public CompareGroupConfig compare() { return null; }
                }};
            } else {
                sqlItems = new SqlItem[0];
            }
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement psCount = null;
        ResultSet rsCount = null;
        try {
            List<Object> sqlParams = new ArrayList<>();
            String sqlStr = SqlTool.buildSql(sqlItems);
            String changeSql = SqlTool.replaceByParam(sqlStr, sqlParams);
            if (initConfig.printSql()) {
                PrintHelper.printSql(changeSql, sqlParams);
            }

            SqlTypeEnum sqlType = SqlTypeEnum.get(logicConfig.sqlType());
            switch (sqlType) {
                case INSERT_INCR:
                    ps = getAddPreparedStatement(conn, changeSql, sqlParams);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            result = ComponentDataResult.success(generatedKeys.getLong(1));
                            break;
                        }
                    }
                    result = ComponentDataResult.success(-1);
                    break;
                case INSERT:
                case UPDATE:
                case DELETE:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    result =  ComponentDataResult.success(ps.executeUpdate());
                    break;
                case SELECT:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    rs = ps.executeQuery();
                    List<Map<String, Object>> dataList = getResultData(rs);
                    result =  ComponentDataResult.success(dataList);
                    break;
                case SELECT_ONE:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    rs = ps.executeQuery();
                    dataList = getResultData(rs);
                    result =  ComponentDataResult.success(!dataList.isEmpty() ? dataList.get(0) : null);
                    break;
                case SELECT_LIMIT:
                    // 分页查询
                    String limitSql = SqlTool.setLimit(changeSql, CURR_PAGE_NAME, PAGE_SIZE_NAME);
                    ps = getPreparedStatement(conn, limitSql, sqlParams);
                    rs = ps.executeQuery();
                    dataList = getResultData(rs);

                    // count 查询
                    String countSql = SqlTool.replaceCount(changeSql, COUNT_NAME);
                    if (initConfig.printSql()) {
                        PrintHelper.printSql(countSql, sqlParams);
                    }
                    psCount = getPreparedStatement(conn, countSql, sqlParams);
                    rsCount = psCount.executeQuery();
                    List<Map<String, Object>> countDataList = getResultData(rsCount);

                    // 组装结果集
                    int currPage = DataParseUtil.getInteger(params.get(CURR_PAGE_NAME), 0);
                    int pageSize = DataParseUtil.getInteger(params.get(PAGE_SIZE_NAME), 0);
                    int count = DataParseUtil.getInteger(countDataList.get(0).get(COUNT_NAME), 0);
                    result = ComponentDataResult.success(dataList, currPage, pageSize, count);
                    break;
                case DDL:
                    ps = getPreparedStatement(conn, changeSql, sqlParams);
                    ps.execute();
                    result =  ComponentDataResult.success(true);
                    break;
                default:
                    PrintErrorHelper.print(MysqlErrorEnum.SQL_TYPE_ERROR);
                    result = ComponentDataResult.fail(MysqlErrorEnum.SQL_TYPE_ERROR);
                    break;
            }

            return result;
        } catch (SQLException e) {
            PrintErrorHelper.print(MysqlErrorEnum.SQL_EXECUTE_ERROR, e);
            result = ComponentDataResult.fail(MysqlErrorEnum.SQL_EXECUTE_ERROR);
        } catch (Exception e) {
            PrintErrorHelper.print(MysqlErrorEnum.UNKNOWN_ERROR, e);
            result = ComponentDataResult.fail(MysqlErrorEnum.UNKNOWN_ERROR);
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
                Object value = rs.getObject(i);
                if (value instanceof LocalDateTime) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    value = ((LocalDateTime) value).format(formatter);
                }
                rowData.put(md.getColumnName(i), value);
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
    private static Connection getConnection(String requestId, String connKey, MysqlInitConfig initConfig) throws SQLException, ClassNotFoundException {
        String finalConnKey = buildKey(requestId, connKey);
        Connection conn = CONN_POOL.get(finalConnKey);
        if (conn != null) {
            return conn;
        }

        String driver = "com.mysql.cj.jdbc.Driver";
        // 添加 "?useUnicode=true&characterEncoding=utf-8"
        String url = "jdbc:mysql://"+initConfig.host()+":"+initConfig.port()+"/"+initConfig.dbName();
        if (initConfig.pool() == null) {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, initConfig.username(), initConfig.password());
        } else {
            String key = "Mysql_" + (CheckEmptyUtil.isEmpty(initConfig.id()) ? CheckEmptyUtil.EMPTY : initConfig.id());
            DataSource data = DbConnPoolTool.getPool(key);
            if (data == null) {
                MysqlInitConfig.Pool poolConfig = initConfig.pool();
                data = DbConnPoolTool.initAndGet(key, url, initConfig.username(), initConfig.password(), driver, poolConfig.max(), poolConfig.min(), poolConfig.timeout());
            }
            conn = data.getConnection();
        }

        CONN_POOL.put(finalConnKey, conn);
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
     * 获取 PreparedStatement 对象
     *
     * @param conn 连接对象
     * @param sql 执行的SQL
     * @param sqlParams SQL的参数
     * @return PreparedStatement 对象
     * @throws SQLException 异常
     */
    private static PreparedStatement getAddPreparedStatement(Connection conn, String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < sqlParams.size(); i++) {
            ps.setObject(i+1, sqlParams.get(i));
        }
        return ps;
    }

    /**
     * 关闭连接
     *
     * @param requestId 请求ID
     * @param connKey 连接Key
     */
    public static void closeConnection(String requestId, String connKey, boolean isCommit) {
        try {
            String finalConnKey = buildKey(requestId, connKey);
            Connection conn = CONN_POOL.get(finalConnKey);
            if (conn != null) {
                if (isCommit) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.close();
            }

            CONN_POOL.remove(finalConnKey);
        } catch (SQLException e) {
            PrintErrorHelper.print(MysqlErrorEnum.CONN_CLOSE_ERROR, e);
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
            PrintErrorHelper.print(MysqlErrorEnum.STATEMENT_CLOSE_ERROR, e);
        }
    }

    private static String buildKey(String requestId, String connKey) {
        return requestId + "-" + connKey;
    }
}
