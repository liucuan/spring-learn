package com.tone.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * <p>
 * 如果配置了@Configuration的类同时配置了@Profile，那么所有的配置了@Bean注解的方法和@Import注解的相关的类都会被传递为该Profile。
 * <p>
 * 除非这个Profile激活了，否则其中的Bean定义都不会激活。
 * <p>
 * 如果配置为@Component或者@Configuration的类标记了@Profile({"p1","p2"}),
 * <p>
 * 那么这个类当且仅当Profile是p1或者p2的时候才会激活。
 * <p>
 * 如果某个Profile的前缀是!这个非操作符，那么@Profile注解的类会只有当前的Profile没有激活的时候才能生效。
 * <p>
 * 举例来说，如果配置为@Profile({"p1", "!p2"})，那么注册的行为会在Profile为p1或者是Profile为非p2的时候才会激活。
 *
 * @author zhaoxiang.liu
 * @date 2018/8/1
 */
@Log4j2
@Configuration
@MapperScan("com.tone.dao")
@PropertySource("classpath:${my.placeholder:}/app.properties")
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public TestBean testBean() {
        TestBean testBean = new TestBean();
        testBean.setName(env.getProperty("testbean.name"));
        log.info("testBean.name={}", testBean.getName());
        return testBean;
    }

    @Bean(destroyMethod = "")
    @Profile("prd")
    public DataSource prdDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
    }

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:sql/schema.sql")
                .addScript("classpath:sql/test-data.sql").build();
    }
}
