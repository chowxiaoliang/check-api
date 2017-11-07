package com.zl.service;

public class Info implements Comparable<Info>{

    private String name;

    private String sex;

    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Info o1) {
        return this.getAge().compareTo(o1.getAge());
    }
}
