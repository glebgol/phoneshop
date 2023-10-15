package com.es.phoneshop.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        String redirectUrl = (String) request.getSession().getAttribute("urlBefore");

        if (redirectUrl != null) {
            response.sendRedirect(redirectUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
