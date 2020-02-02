package com.tistory.byrage.springboot.webserver.controller;

import com.tistory.byrage.springboot.webserver.component.Controller;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.*;

@Slf4j
public class SessionController implements Controller {

    public static final String PATH = "/test/session";
    protected static final String SESSION_NAME = "member";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if ("GET".equals(request.getMethod())) {
            return showSession(request);
        }

        if ("POST".equals(request.getMethod())) {
            return createSession(request);
        }

        if ("DELETE".equals(request.getMethod())) {
            return deleteSession(request);
        }

        return null;
    }

    private String showSession(HttpServletRequest req) {
        log.info("show session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
        return null;
    }

    private String createSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_NAME, req.getAttribute("member"));
        log.info("create session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
        return null;
    }

    private String deleteSession(HttpServletRequest req) {
        req.getSession().invalidate();
        log.info("delete session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
        return null;
    }
}
