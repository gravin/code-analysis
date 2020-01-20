package com.codeanalysis.cglib;

public class MultiFieldBean {
    private String name;
    private String address;
    private Integer age;

    public MultiFieldBean() {
    }

    public MultiFieldBean(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
    // 省略getter和setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
