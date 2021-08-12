package com.wy.spring.service;

/**
 * @Classname TransferService
 * @Description 转账service业务接口
 * @Date 2021/8/10 11:41 下午
 * @Company
 * @Author wy
 */
public interface TransferService {
    void transfer(String fromCardNo,String toCardNo,int money) throws Exception;
}
