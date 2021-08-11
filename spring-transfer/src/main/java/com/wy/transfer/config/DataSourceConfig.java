package com.wy.transfer.config;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @Classname TransferSerlvert
 * @Description TODO
 * @Date 2021/8/11 2:01 上午
 * @Company
 * @Author wy
 */
public class DataSourceConfig {

    private DataSourceConfig(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("rootroot");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
