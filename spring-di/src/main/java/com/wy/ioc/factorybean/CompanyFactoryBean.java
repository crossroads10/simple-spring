package com.wy.ioc.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Classname CompanyFactoryBean
 * @Description TODO
 * @Date
 * @Company
 * @Author wy
 */
public class CompanyFactoryBean implements FactoryBean<Company> {

    @Override
    public Company getObject() throws Exception {
        Company company = new Company();
        company.setName("gy");
        company.setAddress("自贸大厦");
        return company;
    }

    @Override
    public Class<?> getObjectType() {
        return Company.class;
    }

    @Override
    public boolean isSingleton() {
        return Boolean.TRUE;
    }
}
