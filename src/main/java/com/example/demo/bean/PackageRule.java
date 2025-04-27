package com.example.demo.bean;

public class PackageRule {
    private String name;
    private int versionCode;

    public PackageRule() {
    }

    public PackageRule(String name, int versionCode) {
        this.name = name;
        this.versionCode = versionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
