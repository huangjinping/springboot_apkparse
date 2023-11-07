package com.example.demo.bean;

import java.util.List;

public class BackUp {

    private boolean allowBackup;
    private String fullBackupContent;
    private List<String> fullBackupContentList;

    private Jentity jentity;

    public Jentity getJentity() {
        return jentity;
    }

    public void setJentity(Jentity jentity) {
        this.jentity = jentity;
    }

    public boolean isAllowBackup() {
        return allowBackup;
    }

    public void setAllowBackup(boolean allowBackup) {
        this.allowBackup = allowBackup;
    }

    public String getFullBackupContent() {
        return fullBackupContent;
    }

    public void setFullBackupContent(String fullBackupContent) {
        this.fullBackupContent = fullBackupContent;
    }

    public List<String> getFullBackupContentList() {
        return fullBackupContentList;
    }

    public void setFullBackupContentList(List<String> fullBackupContentList) {
        this.fullBackupContentList = fullBackupContentList;
    }
}
