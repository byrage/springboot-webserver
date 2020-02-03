package com.tistory.byrage.springboot.webserver.controller;

import com.tistory.byrage.springboot.webserver.component.Controller;
import com.tistory.byrage.springboot.webserver.component.DataBinding;
import com.tistory.byrage.springboot.webserver.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SessionController implements Controller, DataBinding {

    public static final String PATH = "/test/session.test";
    protected static final String SESSION_NAME = "member";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {

        if (HttpMethod.GET.matches(request.getMethod())) {
            return showSession(model);
        }

        if (HttpMethod.POST.matches(request.getMethod())) {
            return createSession(model);
        }

        if (HttpMethod.DELETE.matches(request.getMethod())) {
            return deleteSession(model);
        }

        return null;
    }

    @Override
    public Map<String, Class<?>> getDataBinder() {
        Map<String, Class<?>> dataBinder = new HashMap<>();
        dataBinder.put("member", Member.class);
        return dataBinder;
    }

    private String showSession(Map<String, Object> model) {
        HttpSession session = (HttpSession) model.get("session");
        log.info("show session. memberSession={}", session.getAttribute(SESSION_NAME));
        return null;
    }

    private String createSession(Map<String, Object> model) {
        HttpSession session = (HttpSession) model.get("session");
        session.setAttribute(SESSION_NAME, model.get("member"));
        log.info("create session. memberSession={}", session.getAttribute(SESSION_NAME));
        return null;
    }

    private String deleteSession(Map<String, Object> model) {
        HttpSession session = (HttpSession) model.get("session");
        session.invalidate();
        log.info("delete session. memberSession={}", session.getAttribute(SESSION_NAME));
        return null;
    }
}
