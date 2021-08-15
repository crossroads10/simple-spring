package com.wy.ioc.design;

/**
 * @Classname ImplAbs
 * @Description TODO
 * @Date 2021/8/3 1:28 上午
 * @Company wy
 * @Author wy
 */
public abstract class ImplAbs extends AbsPer {
    public ImplAbs() {
        super();
    }
    @Override
    public void load(){
        System.out.println("已经写了");
    }
}
