package com.tistory.byrage.springboot.webserver.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Controller {

    String execute(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) throws Exception;
}
