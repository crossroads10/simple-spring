package com.wy.transfer.dao;

import com.wy.transfer.pojo.Account;

/**
 * @Classname AccountDao
 * @Description TODO
 * @Date 2021/8/10 11:36 下午
 * @Author wy
 */
public interface AccountDao {
    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
