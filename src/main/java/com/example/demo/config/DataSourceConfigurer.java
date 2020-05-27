package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.demo.datesource.DynamicDataSourceContextHolder;
import com.example.demo.datesource.DynamicRoutingDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 数据源配置类 生成多个数据源实例并将其注入到 ApplicationContext 中
 * @date 2020/5/27 14:16
 */
@Configuration
public class DataSourceConfigurer {

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(2);
        dataSourceMap.put(DataSourceKey.MASTER.getName(), master());
        dataSourceMap.put(DataSourceKey.SLAVE.getName(), slave());

        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

        return dynamicRoutingDataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 下划线转骆驼
        configuration.setMapUnderscoreToCamelCase(true);

        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean;
    }

    /**
     * 事务
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
