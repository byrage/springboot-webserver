package com.tistory.byrage.springboot.webserver.listener;

import com.tistory.byrage.springboot.webserver.controller.CalculatorController;
import com.tistory.byrage.springboot.webserver.controller.SessionController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("[ContextListener] contextInitialized.");

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(CalculatorController.PATH, new CalculatorController());
        servletContext.setAttribute(SessionController.PATH, new SessionController());
    }
}
