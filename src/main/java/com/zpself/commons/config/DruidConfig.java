package com.zpself.commons.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据源配置
 */
@Configuration
public class DruidConfig {
    /**
     * Druid配置
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource getDuridDS(){
        return new DruidDataSource();
    }
}