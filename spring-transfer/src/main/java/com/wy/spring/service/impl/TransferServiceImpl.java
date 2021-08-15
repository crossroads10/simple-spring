package com.wy.spring.service.impl;

import com.wy.spring.dao.AccountDao;
import com.wy.spring.dao.impl.JdbcAccountDaoImpl;
import com.wy.spring.pojo.Account;
import com.wy.spring.service.TransferService;

/**
 * @Classname TransferServiceImpl
 * @Description TODO
 * @Date 2021/8/10 11:42 下午
 * @Company wy
 * @Author wy
 */
public class TransferServiceImpl implements TransferService {
    private AccountDao accountDao = new JdbcAccountDaoImpl();
    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCardNo(to);
        accountDao.updateAccountByCardNo(from);
    }
}
