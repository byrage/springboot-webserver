package com.tistory.byrage.springboot.webserver.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet
public class CalculatorHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));

        resp.setContentType("text/plain");

        PrintWriter writer = resp.getWriter();
        writer.println("num1=" + num1 + ", num2=" + num2);
        writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
    }
}
