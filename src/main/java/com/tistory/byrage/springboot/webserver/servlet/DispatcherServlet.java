package com.tistory.byrage.springboot.webserver.servlet;

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
        if (("/calc" + DISPATCHER_SERVLET_SUFFIX).equals(req.getServletPath())) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/calc");
            requestDispatcher.include(req, resp);
            return;
        }

        if (("/test/session" + DISPATCHER_SERVLET_SUFFIX).equals(req.getServletPath())) {
            Member member = Member.builder()
                    .id(Long.valueOf(req.getParameter("id")))
                    .name(req.getParameter("name"))
                    .email(req.getParameter("email"))
                    .build();
            req.setAttribute("member", member);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/test/session");
            requestDispatcher.include(req, resp);
            return;
        }
    }
}
