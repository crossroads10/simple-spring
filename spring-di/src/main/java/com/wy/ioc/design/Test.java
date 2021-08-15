package com.wy.ioc.design;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/8/2 11:34 下午
 * @Company wy
 * @Author wy
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Person.class);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        ImplAbss implAbss = new ImplAbss();
        implAbss.test1();
    }


}
