package com.tone.service;

import javax.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/31
 */
@Log4j2
@Service
public class Service1 implements InitializingBean {

    @Autowired
    private Service2 service2;

    public Service1() {
        log.info("constructor");
    }

    public void print(String str) {
        log.info("print str={}", str);
    }

    public void print2(String str) {
        service2.print(str);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }

    @PreDestroy
    public void destroy() {
        log.info("destroy");
    }
}
