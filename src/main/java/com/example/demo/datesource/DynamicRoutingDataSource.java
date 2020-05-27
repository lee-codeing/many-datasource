package com.example.demo.datesource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author he.Lee
 * @version 1.0
 * @Description: 在访问数据库时会调用该类的 determineCurrentLookupKey() 方法获取数据库实例的 key
 * @date 2020/5/27 14:20
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("current datasource is : {}", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

}
