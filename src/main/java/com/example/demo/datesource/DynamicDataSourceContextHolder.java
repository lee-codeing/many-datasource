package com.example.demo.datesource;

import com.example.demo.config.DataSourceKey;

import java.util.ArrayList;
import java.util.List;


/**
 * @author he.Lee
 * @version 1.0
 * @Description: 动态数据源
 * @date 2020/5/27 14:22
 */
public class DynamicDataSourceContextHolder {

    private static ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(() -> DataSourceKey.MASTER.getName());

    public static List<Object> dataSourceKeys = new ArrayList<Object>();

    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    public static Object getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    public static Boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
}