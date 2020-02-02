package com.tistory.byrage.springboot.webserver.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
