package com.parch.combine.core.component.tools.conn;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接池帮助类
 */
public class DbConnPoolTool {

    private final static Map<String, DataSource> POOL_MAP = new HashMap<>(16);

    /**
     * 获取连接池
     *
     * @param key KEY
     * @return 连接池
     */
    public static DataSource getPool(String key) {
        return POOL_MAP.get(key);
    }

    /**
     * 初始化
     *
     * @param key KEY
     * @param url 地址
     * @param username 用户名
     * @param password 密码
     * @param driver 驱动
     * @param max 最大连接数
     * @param min 最小连接数
     * @param timeout 超时时间
     */
    public static void init(String key, String url, String username, String password, String driver, int max, int min, long timeout) {
        synchronized (POOL_MAP) {
            if (POOL_MAP.containsKey(key)) {
                return;
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            config.setDriverClassName(driver);
            config.setMaximumPoolSize(max);
            config.setMinimumIdle(min);
            config.setConnectionTimeout(timeout);
            config.setIdleTimeout(timeout * 2);
            config.setMaxLifetime(timeout * 6);

            POOL_MAP.put(key, new HikariDataSource(config));
        }
    }

    /**
     * 初始化并获取一个连接
     *
     * @param key KEY
     * @param url 地址
     * @param username 用户名
     * @param password 密码
     * @param driver 驱动
     * @param max 最大连接数
     * @param min 最小连接数
     * @param timeout 超时时间
     */
    public static DataSource initAndGet(String key, String url, String username, String password, String driver, int max, int min, long timeout) {
        init(key, url, username, password,driver, max, min, timeout);
        return getPool(key);
    }
}
