package com.wy.ioc.factorybean;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname FactoryBeanTest
 * @Description
 * @Date
 * @Company
 * @Author wy
 */
public class FactoryBeanTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext context= new ClassPathXmlApplicationContext("/beans.xml");
        CompanyFactoryBean companyFactoryBean = (CompanyFactoryBean) context.getBean("&companyFactoryBean");
        Company object = companyFactoryBean.getObject();
        Class<?> objectType = companyFactoryBean.getObjectType();
        boolean singleton = companyFactoryBean.isSingleton();
        System.out.println(companyFactoryBean);
        System.out.println(JSON.toJSONString(companyFactoryBean));
        System.out.println(object);
        System.out.println(objectType);
        System.out.println(singleton);


    }
}
