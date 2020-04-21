package com.xingpc.sbo.entities;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/2/18 15:31
 * @Version: 1.0
 */
public class Dog {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
