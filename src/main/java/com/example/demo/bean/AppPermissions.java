package com.example.demo.bean;

public class AppPermissions implements Comparable<AppPermissions> {
    private String permission;
    /**
     * 0缺失
     * 1配置
     * 2范围外
     * 3严禁配置
     */
    private int state;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public int compareTo(AppPermissions o) {
        return this.state-o.state;
    }
}
