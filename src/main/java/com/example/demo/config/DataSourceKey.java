package com.example.demo.config;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 数据源
 * @date 2020/5/27 14:16
 */
public enum DataSourceKey {
    MASTER("master"),
    SLAVE("slave");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private DataSourceKey(String name) {
        this.name = name;
    }
}
