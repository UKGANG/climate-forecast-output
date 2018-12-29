package com.ukgang.universal.configuration;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MybatisConfig {

    @Bean(name = "oltpTx")
    @Lazy
    @Autowired
    public PlatformTransactionManager getMyBatisTransactionManager(SqlSessionFactory sqlSessionFactory) throws IOException {
        return new DataSourceTransactionManager(sqlSessionFactory.
                getConfiguration().getEnvironment().getDataSource());
    }
    
}