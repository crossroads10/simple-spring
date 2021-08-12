package com.wy.solution.utils;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.wy.solution.config.DataSourceConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Classname ConnectionPools
 * @Description 通过单例模式的方式来进行创建对象以及使用，或者通过property的set注入方式来进行也是可以的
 * 在此处采用单例模式来进行
 * <p>
 * 因为要实现事务控制的话，当前线程所使用的连接必须是同一个，不然的话，两次数据库的update操作使用的是连个connection
 * 是不能进行事务控制的，所以要做这个保证，那这种情况下，就要使得连接和当前线程要进行绑定，这就可以使用到ThreadLocal类
 * 会进行线程的隔离，当前线程只能拿到当前线程set的内容，其他的线程的内容，当前线程是拿不到的
 * @Date
 * @Company
 * @Author wy
 */
public class ConnectionPools {

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

//    private ConnectionPools() {
//
//    }
//
//    public static ConnectionPools instance;
//
//    public static ConnectionPools getInstance() {
//        if (Objects.isNull(instance)) {
//            synchronized (ConnectionPools.class) {
//                if (Objects.isNull(instance)) {
//                    instance = new ConnectionPools();
//                }
//            }
//        }
//        return instance;
//    }

    public Connection currentConnection() throws SQLException {
        Connection connection = connectionThreadLocal.get();
        if (Objects.isNull(connection)) {
            // 从德鲁伊连接池获取连接  DruidPooledConnection getConnection()
            DruidPooledConnection connection1 = DataSourceConfig.getInstance().getConnection();
            connectionThreadLocal.set(connection1);
            connection = connection1;
        }
        return connection;

    }

}
