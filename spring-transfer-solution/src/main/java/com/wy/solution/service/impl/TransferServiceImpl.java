package com.wy.solution.service.impl;

import com.wy.solution.factory.BeanFactory;
import com.wy.solution.service.TransferService;
import com.wy.solution.dao.AccountDao;
import com.wy.solution.pojo.Account;
import com.wy.solution.utils.TransactionManager;

/**
 * @Classname TransferServiceImpl
 * @Description TODO
 * @Date
 * @Company
 * @Author wy
 */
public class TransferServiceImpl implements TransferService {
    // todo 第一版 spring-transfer
    //    private AccountDao accountDao = new JdbcAccountDaoImpl();
    // 其实这种情况下，也不是太友好，因为还需要使用BeanFactory进行获取bean并且还要再此处还要显式声明，很繁琐
    // 想要得到最佳的效果是，直接写 private AccountDao accountDao;即可，不想要后面的创建和获取Bean的过程了，由底层来实现
    // 就显得极为简洁，以及扩展性贼强，并且可读性也很高，维护性也挺高，更加的不需要修改任何的代码
    // todo 第二版  spring-transfer-spring
//    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    //todo  第三版  spring-transfer-spring 又经过一版本的改造  通过配置，并通过反射来进行注入
    private AccountDao accountDao;

    // set方法需要些，不然找不到方法，不能进行set方法的注入，或者使用lombok也是可以的，省去了写此方法
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private TransactionManager transactionManager;

    // 如果是private的话，通过反射正常情况下是找不到此方法的
    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        try {
            // 添加事务控制  此处通过单例的方式去获取事务管理器
            // todo 其实这样搞呢，还是不优雅，如果业务接口非常多的话，会带来非常多的一个冗余代码，而且不利于扩展
            // todo 所以这个时候就可以通过动态代理来实现对接口的增强，通过代理类来实现功能增强
            // todo  或者是通过注解的方式来实现也会更加优雅
            transactionManager.beginTransaction();
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);


            from.setMoney(from.getMoney() - money);
            to.setMoney(to.getMoney() + money);

            accountDao.updateAccountByCardNo(to);
            accountDao.updateAccountByCardNo(from);
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 事务回滚
            transactionManager.rollback();
            // 主要是用于上层进行catch捕获异常
            throw e;
        }

    }
}
