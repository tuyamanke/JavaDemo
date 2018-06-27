package com.wen.model;

import java.io.Serializable;

/**
 * Created by wen on 2018/6/27
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
