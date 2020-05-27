package com.example.demo.datesource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author he.Lee
 * @version 1.0
 * @Description: 数据源切面
 * @date 2020/5/27 14:16
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
 
	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	
	@Before("@annotation(targetDataSource))")
	public void switchDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){
		if ( !DynamicDataSourceContextHolder.containDataSourceKey( targetDataSource.value().getName() ) ) {
			logger.error("DataSource [{}] doesn't exist, use default DataSource [{}]", targetDataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey( targetDataSource.value().getName() );
            logger.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
        }
	}
	
	@After("@annotation(targetDataSource))")
	public void restoreDataSource(JoinPoint joinPoint,TargetDataSource targetDataSource){
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
	}
	
}
