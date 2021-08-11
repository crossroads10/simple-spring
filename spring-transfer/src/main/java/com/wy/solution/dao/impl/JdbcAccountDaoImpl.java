package com.wy.solution.dao.impl;

import com.wy.solution.config.DataSourceConfig;
import com.wy.solution.dao.AccountDao;
import com.wy.solution.pojo.Account;

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

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        Connection connection = DataSourceConfig.getInstance().getConnection();
        String sql="select * from account where cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account=new Account();
        while (resultSet.next()){
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        Connection connection = DataSourceConfig.getInstance().getConnection();
        String updateSql="update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return i;
    }
}
