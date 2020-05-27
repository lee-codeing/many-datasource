package com.example.demo.datesource;

import com.example.demo.config.DataSourceKey;

import java.lang.annotation.*;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 数据源自定义注解
 * @date 2020/5/27 14:30
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DataSourceKey value() default DataSourceKey.MASTER;
}