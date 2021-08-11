package com.wy.solution.service.impl;

import com.wy.solution.dao.AccountDao;
import com.wy.solution.dao.impl.JdbcAccountDaoImpl;
import com.wy.solution.pojo.Account;
import com.wy.solution.service.TransferService;

/**
 * @Classname TransferServiceImpl
 * @Description TODO
 * @Date 2021/8/10 11:42 下午
 * @Company 杭州光云科技有限公司
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
