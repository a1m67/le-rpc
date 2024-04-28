package com.le.example.common.model;

import java.io.Serializable;
// 序列化为后续网络传输序列化提供支持
public class User implements Serializable {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
