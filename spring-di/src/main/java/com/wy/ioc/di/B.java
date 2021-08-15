package com.wy.ioc.di;

/**
 * @Classname B
 * @Description TODO
 * @Date 2021/8/15 7:49 下午
 * @Company wy
 * @Author wy
 */
public class B {
    private A a;


    public void sayB(){
        System.out.println("hello world");
    }
    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
