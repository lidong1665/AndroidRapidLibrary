package com.lidong.demo.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Person
 */
public class Person extends RealmObject {
    @PrimaryKey
    private String code;//编号
    private String name;//姓名
    private int age;//年龄

    public Person() {
    }

    public Person(int age, String code, String name) {
        this.age = age;
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
        return "Person{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
