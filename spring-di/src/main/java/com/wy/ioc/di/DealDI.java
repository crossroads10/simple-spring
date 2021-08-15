package com.wy.ioc.di;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname DealDI
 * @Description 手动实现简单循环依赖，通过缓存方式来处理
 * @Date
 * @Company
 * @Author wy
 */
public class DealDI {

    public static Map<String, Object> cacheMap = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        Class[] classes = {A.class, B.class};
        for (Class aClass : classes) {
            getBean(aClass);
        }
        System.out.println(getBean(B.class).getA()==(getBean(A.class)));
        System.out.println(getBean(A.class).getB()==(getBean(B.class)));
    }

    @SneakyThrows
    private static <T> T getBean(Class<T> beanClass) {
        String beanName = beanClass.getSimpleName().toLowerCase();
        if (cacheMap.containsKey(beanName)) {
            return (T) cacheMap.get(beanName);
        }
        Object object = beanClass.getDeclaredConstructor().newInstance();
        cacheMap.put(beanName, object);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldClass = field.getType();
            String fieldBeanName = fieldClass.getSimpleName().toLowerCase();
            Object bean = getBean(fieldClass);
            Object o = cacheMap.get(fieldBeanName);
            boolean b = cacheMap.containsKey(fieldBeanName);
            if (b){
                // o字段的新值 0bject 旧值
                field.set(object,o);
            }else {
                field.set(object,bean);
            }
        }
        return (T) object;
    }
}
