package com.parch.combine.core.component.tools.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class DBConnectionPool {
    /**
     * 连接池中的连接队列
     */
    private BlockingQueue<Connection> connections;

    /**
     * 连接池最小连接数
     */
    private int minSize;

    /**
     * 连接池最大连接数
     */
    private int maxSize;

    public DBConnectionPool(int minSize, int maxSize) throws SQLException {
        if (maxSize < minSize) {
            throw new IllegalArgumentException("maxSize cannot be smaller than minSize.");
        }

        this.minSize = minSize;
        this.maxSize = maxSize;
        this.connections = new LinkedBlockingQueue<>(maxSize);
        initializeConnections();
    }

    /**
     * 初始化连接池
     */
    private void initializeConnections() throws SQLException {
        for (int i = 0; i < minSize; i++) {
            Connection connection = createConnection();
            if (connection != null) {
                connections.offer(connection);
            }
        }
    }

//    /**
//     * 创建一个数据库连接
//     */
//    private Connection createConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/mydatabase";
//        String username = "username";
//        String password = "password";
//        return DriverManager.getConnection(url, username, password);
//    }

    protected abstract Connection createConnection() throws SQLException;

    /**
     * 从连接池中获取一个连接
     */
    public Connection getConnection() throws SQLException {
        try {
            Connection connection = connections.take();
            if (connection.isClosed()) {
                return createConnection();
            }
            return connection;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException("Interrupted while waiting for a connection from the pool.", e);
        }
    }

    /**
     * 释放一个连接
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.offer(connection);
        }
    }

    /**
     * 关闭连接池中的所有连接
     */
    public void closeAllConnections() throws SQLException {
        for (Connection connection : connections) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        connections.clear();
    }
}
