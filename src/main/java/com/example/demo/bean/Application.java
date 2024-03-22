package com.example.demo.bean;

public class Application {
    private String requestLegacyExternalStorage;
    private String networkSecurityConfig;
    private int compileSdkVersion;
    private String packageName;
    private String largeHeap;

    public String getLargeHeap() {
        return largeHeap;
    }

    public void setLargeHeap(String largeHeap) {
        this.largeHeap = largeHeap;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getCompileSdkVersion() {
        return compileSdkVersion;
    }

    public void setCompileSdkVersion(int compileSdkVersion) {
        this.compileSdkVersion = compileSdkVersion;
    }

    public String getRequestLegacyExternalStorage() {
        return requestLegacyExternalStorage;
    }

    public void setRequestLegacyExternalStorage(String requestLegacyExternalStorage) {
        this.requestLegacyExternalStorage = requestLegacyExternalStorage;
    }

    public String getNetworkSecurityConfig() {
        return networkSecurityConfig;
    }

    public void setNetworkSecurityConfig(String networkSecurityConfig) {
        this.networkSecurityConfig = networkSecurityConfig;
    }
}
