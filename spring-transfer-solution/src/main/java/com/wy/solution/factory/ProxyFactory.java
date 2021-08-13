package com.wy.solution.factory;

import com.wy.solution.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @Classname ProxyFactory
 * @Description 关于事务的最终解决方案，通过代理工厂的模式来处理
 * 使用单例来处理也可以，或者是通过set方法的注入的方式，
 * 在此和以上一样，通过set注入的方式来进行
 * @Date
 * @Company
 * @Author wy
 */
public class ProxyFactory {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object jdkProxy(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    // 事务开启
                    transactionManager.beginTransaction();
                    result = method.invoke(object, args);
                    // 事务提交
                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 事务回滚
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });
    }

    public Object cglibProxy(Object object) {
        return Enhancer.create(object.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try {
                    transactionManager.beginTransaction();
                    result = method.invoke(object, objects);
                    transactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback();
                    throw e;
                }
                return result;
            }
        });

    }


}
