package com.tistory.byrage.springboot.webserver.config;

import com.tistory.byrage.springboot.webserver.servlet.HelloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<HelloServlet> servletRegistrationBean() {
        return new ServletRegistrationBean<>(new HelloServlet());
    }
}
