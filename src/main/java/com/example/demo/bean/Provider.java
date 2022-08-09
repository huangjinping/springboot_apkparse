package com.example.demo.bean;

public class Provider {
    private String authorities;
    private String exported;
    private String grantUriPermissions;
    private String name;
    private MetaData metaData;

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getExported() {
        return exported;
    }

    public void setExported(String exported) {
        this.exported = exported;
    }

    public String getGrantUriPermissions() {
        return grantUriPermissions;
    }

    public void setGrantUriPermissions(String grantUriPermissions) {
        this.grantUriPermissions = grantUriPermissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
