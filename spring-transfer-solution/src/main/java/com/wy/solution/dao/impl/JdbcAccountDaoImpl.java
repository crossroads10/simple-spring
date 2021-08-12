package com.wy.solution.dao.impl;

import com.wy.solution.dao.AccountDao;
import com.wy.solution.pojo.Account;
import com.wy.solution.utils.ConnectionPools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Classname AccountDao
 * @Description TODO
 * @Date 2021/8/10 11:36 下午
 * @Author wy
 */
public class JdbcAccountDaoImpl implements AccountDao {

    private ConnectionPools connectionPools;

    public void setConnectionPools(ConnectionPools connectionPools) {
        this.connectionPools=connectionPools;
    }
    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
//        Connection connection = DataSourceConfig.getInstance().getConnection();
        // todo  在此处可以通过连接池的方式来进行连接获取  先获取连接池单实例，然后再获取当前线程所绑定的数据库连接
        Connection connection = connectionPools.currentConnection();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }
        resultSet.close();
        preparedStatement.close();
        // todo  如果使用同一个连接的话，此处不能断开连接，如果断开连接，再有其他的调用的话，会导致connect closed
//        connection.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        // 最原始的使用方式，通过德鲁伊来进行获取数据库连接
//        Connection connection = DataSourceConfig.getInstance().getConnection();
        // todo  在此处可以通过连接池的方式来进行连接获取  先获取连接池单实例，然后再获取当前线程所绑定的数据库连接
        Connection connection = connectionPools.currentConnection();
        String updateSql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        // todo  次处同上
//        connection.close();
        return i;
    }
}
