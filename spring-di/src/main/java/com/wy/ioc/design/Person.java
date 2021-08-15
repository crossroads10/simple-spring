package com.wy.ioc.design;

/**
 * @Classname Person
 * @Description TODO
 * @Date 2021/8/2 11:32 下午
 * @Company wy
 * @Author wy
 */
public class Person {

    private String name;
    private Integer age;

    public Person() {

    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
