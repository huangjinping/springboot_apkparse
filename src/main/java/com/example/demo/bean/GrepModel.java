package com.example.demo.bean;

public class GrepModel {
    private String target;
    private String searchCode;

    public GrepModel(String target, String searchCode) {
        this.target = target;
        this.searchCode = searchCode;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }
}
