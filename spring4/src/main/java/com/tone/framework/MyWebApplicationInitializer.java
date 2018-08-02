package com.tone.framework;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 在Servlet 3.0以上的环境下，你可以通过编程的方式来配置Servlet容器了。你可以完全放弃web.xml，也可以两种配置方式同时使用
 *
 * @author zhaoxiang.liu
 * @date 2018/8/2
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation("classpath:dispatcher-servlet.xml");

        ServletRegistration.Dynamic registration = container
                .addServlet("dispatcher", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

}
