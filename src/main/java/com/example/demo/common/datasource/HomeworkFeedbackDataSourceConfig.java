package com.example.demo.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 */
@Configuration
public class HomeworkFeedbackDataSourceConfig {

    /** 数据源Bean */
    @Bean(name = "homeworkFeedbackDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.homework-feedback")
    public DataSource accountCenterDataSource(){
        return new DruidDataSource();
    }

    /** 事务管理器 */
    @Bean(name = "homeworkFeedbackTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("homeworkFeedbackDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
