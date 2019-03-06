package com.ideal.test;

/**
 * @author zhaopei
 * @create 2019-02-22 11:51
 */
public class Person {
    String name;
    String sex;
    Person p;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p.getP().getP().getP().getName());
    }
}
