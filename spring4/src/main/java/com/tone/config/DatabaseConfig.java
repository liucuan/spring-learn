package com.tone.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author zhaoxiang.liu
 * @date 2018/8/2
 */
@Log4j2
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatabaseConfig {

    @Value("${jdbc.url}")
    private String dbUrl;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.connectionTimeout}")
    private long connectionTimeOut;

    @Value("${jdbc.idleTimeout}")
    private long idleTimeout;

    @Value("${jdbc.maxLifetime}")
    private long maxLifetime;

    @Value("${jdbc.maximumPoolSize}")
    private int maximumPoolSize;

    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource() {
        log.info("mysql url:" + dbUrl);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

    private HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setPoolName("poolName");
        config.setConnectionInitSql("SELECT 1");
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        config.setDataSourceProperties(dataSourceProperties());
        config.setConnectionTimeout(connectionTimeOut);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setMaximumPoolSize(maximumPoolSize);
        return config;
    }

    private Properties dataSourceProperties() {
        final Properties properties = new Properties();
        properties.put("url", dbUrl);
        properties.put("user", username);
        properties.put("password", password);
        return properties;
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }
}
