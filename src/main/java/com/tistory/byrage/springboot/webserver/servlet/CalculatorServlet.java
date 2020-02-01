package com.tistory.byrage.springboot.webserver.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CalculatorServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));

        res.setContentType("text/plain");

        PrintWriter writer = res.getWriter();
        writer.println("num1=" + num1 + ", num2=" + num2);
        writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
    }
}
