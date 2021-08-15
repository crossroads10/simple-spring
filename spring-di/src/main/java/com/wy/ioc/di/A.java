package com.wy.ioc.di;


/**
 * @Classname A
 * @Description TODO
 * @Date 2021/8/15 7:49 下午
 * @Company wy
 * @Author wy
 */
public class A {
    private B b;

    public void sayA(){
        System.out.println("hello world");
    }
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
