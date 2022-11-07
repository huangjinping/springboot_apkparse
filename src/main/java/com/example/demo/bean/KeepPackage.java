package com.example.demo.bean;

public class KeepPackage {

    /**
     * 0.未配置
     * 1.已配置
     * -1.不可配置
     */
    private String name;
    private int state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
