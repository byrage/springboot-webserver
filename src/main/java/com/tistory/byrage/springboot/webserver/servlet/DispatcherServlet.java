package com.tistory.byrage.springboot.webserver.servlet;

import com.tistory.byrage.springboot.webserver.component.Controller;
import com.tistory.byrage.springboot.webserver.component.DataBinding;
import com.tistory.byrage.springboot.webserver.exception.DispatchException;
import com.tistory.byrage.springboot.webserver.utils.DataBindUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet("*.test")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("[DispatchServlet] requestURI={}", req.getRequestURI());

        Controller controller = (Controller) getServletContext().getAttribute(req.getServletPath());
        Map<String, Object> model = new HashMap<>();
        model.put("session", req.getSession());
        prepareModel((DataBinding) controller, req, model);
        try {
            String viewName = controller.execute(req, resp, model);
            dispatchRequest(viewName, req, resp);
        } catch (Exception e) {
            log.error("[DispatchServlet] {} exception occurred.", e.getClass().getSimpleName(), e);
            throw new DispatchException(e);
        }
    }

    private void prepareModel(DataBinding dataBinding, HttpServletRequest req, Map<String, Object> model) {

        Map<String, Class<?>> dataBinder = dataBinding.getDataBinder();
        for (Map.Entry<String, Class<?>> entry : dataBinder.entrySet()) {
            String name = entry.getKey();
            Class<?> type = entry.getValue();

            Object o = DataBindUtils.bind(req, type, String.valueOf(req.getParameter(name)));
            model.put(entry.getKey(), o);
        }
    }

    private void dispatchRequest(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (StringUtils.isEmpty(viewName)) {
            return;
        }

        if (viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring(9));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.include(req, resp);
    }
}
