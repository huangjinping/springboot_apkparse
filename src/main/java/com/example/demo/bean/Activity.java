package com.example.demo.bean;

public class Activity {

    private String exported;
    private String launcherName;
    private Scheme scheme;
    private AppLink appLink;

    public AppLink getAppLink() {
        return appLink;
    }

    public void setAppLink(AppLink appLink) {
        this.appLink = appLink;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public String getExported() {
        return exported;
    }

    public void setExported(String exported) {
        this.exported = exported;
    }

    public String getLauncherName() {
        return launcherName;
    }

    public void setLauncherName(String launcherName) {
        this.launcherName = launcherName;
    }
}
