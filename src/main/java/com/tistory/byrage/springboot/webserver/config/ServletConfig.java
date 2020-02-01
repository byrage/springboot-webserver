package com.tistory.byrage.springboot.webserver.config;

import com.tistory.byrage.springboot.webserver.servlet.CalculatorServlet;
import com.tistory.byrage.springboot.webserver.servlet.HelloServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
public class ServletConfig {

    @Bean
    @Primary
    public ServletRegistrationBean<CalculatorServlet> calculatorServletRegistrationBean() {
        return new ServletRegistrationBean<>(new CalculatorServlet());
    }

    @Bean
    public ServletRegistrationBean<HelloServlet> helloServletRegistrationBean() {
        return new ServletRegistrationBean<>(new HelloServlet());
    }
}
