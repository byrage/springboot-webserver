package com.tistory.byrage.springboot.webserver.controller;

import com.tistory.byrage.springboot.webserver.component.Controller;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CalculatorController implements Controller {

    public static final String PATH = "/calc";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return calculator(request, response);
    }

    private String calculator(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));

        resp.setContentType("text/plain");

        PrintWriter writer = resp.getWriter();
        writer.println("num1=" + num1 + ", num2=" + num2);
        writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
        return null;
    }
}
