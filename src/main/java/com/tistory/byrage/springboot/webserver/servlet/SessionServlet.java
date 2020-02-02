package com.tistory.byrage.springboot.webserver.servlet;

import com.tistory.byrage.springboot.webserver.model.Member;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Slf4j
@WebServlet("/test/session")
public class SessionServlet extends HttpServlet {

    protected static final String SESSION_NAME = "member";

    // show session
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("show session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
    }

    // create session
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(SESSION_NAME, Member.dummy(1L));
        log.info("create session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
    }

    // delete sesion
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        log.info("delete session. memberSession={}", req.getSession().getAttribute(SESSION_NAME));
    }
}
