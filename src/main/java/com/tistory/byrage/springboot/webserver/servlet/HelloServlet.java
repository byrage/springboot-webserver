package com.tistory.byrage.springboot.webserver.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class HelloServlet implements javax.servlet.Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        log.info("getServletConfig");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service. req={}, res={}", req, res);
    }

    @Override
    public String getServletInfo() {
        log.info("getServletInfo");
        return null;
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }
}
