package com.wy.solution.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Classname TransctionManager
 * @Description 事务管理器也应该是单例的, 只需要存在一个管理器即可
 * 进行事务控制需要通过当前线程调用的数据库来接来进行
 *
 * 此类在xml文件中也进行了配置，从BeanFactory中去拿也可以，通过单例的方式来进行也可以，都可行，此处还是通过单例来进行获取对象
 * @Date
 * @Company
 * @Author wy
 */
public class TransactionManager {


    private ConnectionPools connectionPools;

    public void setConnectionPools(ConnectionPools connectionPools){
        this.connectionPools=connectionPools;
    }
//    private TransactionManager() {
//
//    }

//    public static TransactionManager instance;

//    public static TransactionManager getInstance() {
//        if (Objects.isNull(instance)) {
//            synchronized (TransactionManager.class) {
//                if (Objects.isNull(instance)) {
//                    instance = new TransactionManager();
//                }
//            }
//        }
//        return instance;
//    }


    public void beginTransaction() throws SQLException {
        Connection connection = connectionPools.currentConnection();
        connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        Connection connection = connectionPools.currentConnection();
        connection.commit();
    }

    public void rollback() throws SQLException {
        Connection connection = connectionPools.currentConnection();
        connection.rollback();
    }

}
