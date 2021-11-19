package com.info.mvc1.domain;

import lombok.Data;

@Data
public class HelloData {
    private String username;
    private int age;


    public HelloData() {
    }

    public HelloData(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public String toString() {
        return "HelloData{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
