package com.springboot.D2;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    public User(Long id,String name,Integer age){
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public User(String name,Integer age){
        this.age = age;
        this.name = name;
    }
    public User(){

    }
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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