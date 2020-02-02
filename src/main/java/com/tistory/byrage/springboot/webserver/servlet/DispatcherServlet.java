package com.tistory.byrage.springboot.webserver.servlet;

import com.tistory.byrage.springboot.webserver.controller.CalculatorController;
import com.tistory.byrage.springboot.webserver.controller.SessionController;
import com.tistory.byrage.springboot.webserver.model.Member;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Slf4j
@WebServlet("*" + DispatcherServlet.DISPATCHER_SERVLET_SUFFIX)
public class DispatcherServlet extends HttpServlet {

    protected static final String DISPATCHER_SERVLET_SUFFIX = ".test";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("[DispatchServlet] requestURI={}", req.getRequestURI());
        if ((CalculatorController.PATH + DISPATCHER_SERVLET_SUFFIX).equals(req.getServletPath())) {
            CalculatorController calculatorController = new CalculatorController();
            String viewName = calculatorController.execute(req, resp);
            dispatchRequest(CalculatorController.PATH, viewName, req, resp);
            return;
        }

        if ((SessionController.PATH + DISPATCHER_SERVLET_SUFFIX).equals(req.getServletPath())) {
            SessionController sessionController = new SessionController();
            Member member = Member.builder()
                    .id(Long.valueOf(req.getParameter("id")))
                    .name(req.getParameter("name"))
                    .email(req.getParameter("email"))
                    .build();
            req.setAttribute("member", member);
            String viewName = sessionController.execute(req, resp);
            dispatchRequest(SessionController.PATH, viewName, req, resp);
        }
    }

    private void dispatchRequest(String url, String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(url);
        if (viewName == null || "".equals(viewName)) {
            requestDispatcher.include(req, resp);
            return;
        }

        resp.sendRedirect("redirect://" + viewName);
    }
}
