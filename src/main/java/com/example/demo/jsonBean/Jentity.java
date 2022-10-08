package com.example.demo.jsonBean;

public class Jentity {
    String name;
    Object value;
    int state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Jentity(String name, Object value, int state) {
        this.name = name;
        this.value = value;
        this.state = state;
    }

}
