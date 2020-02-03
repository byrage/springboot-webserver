package com.tistory.byrage.springboot.webserver.controller;

import com.tistory.byrage.springboot.webserver.component.Controller;
import com.tistory.byrage.springboot.webserver.component.DataBinding;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CalculatorController implements Controller, DataBinding {

    public static final String PATH = "/calc.test";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws IOException {
        int num1 = (int) model.get("num1");
        int num2 = (int) model.get("num2");

        response.setContentType("text/plain");

        PrintWriter writer = response.getWriter();
        writer.println("num1=" + num1 + ", num2=" + num2);
        writer.println(num1 + " + " + num2 + " = " + (num1 + num2));
        return null;
    }

    @Override
    public Map<String, Class<?>> getDataBinder() {
        Map<String, Class<?>> dataBinder = new HashMap<>();
        dataBinder.put("num1", Integer.class);
        dataBinder.put("num2", Integer.class);
        return dataBinder;
    }

}
