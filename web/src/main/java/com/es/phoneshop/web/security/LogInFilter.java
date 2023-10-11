package com.es.phoneshop.web.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogInFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (httpServletRequest.getMethod().equals("GET") && isSuccessfulLogIn(httpServletRequest)) {
            httpServletRequest.getSession().setAttribute("urlBefore", httpServletRequest.getHeader("Referer"));
        }

        chain.doFilter(request, response);
    }

    private boolean isSuccessfulLogIn(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getPathInfo() != null && !httpServletRequest.getPathInfo().contains("/login?error");
    }
}
