package com.tistory.byrage.springboot.webserver.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        initParams = @WebInitParam(name = CharacterEncodingFilter.ENCODING_TYPE, value = "UTF-8"),
        urlPatterns = "/*"
)
public class CharacterEncodingFilter implements Filter {

    protected static final String ENCODING_TYPE = "encodingType";
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding(filterConfig.getInitParameter("encodingType"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
