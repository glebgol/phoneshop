package com.es.phoneshop.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String redirectUrl = request.getHeader("Referer");

        if (redirectUrl != null) {
            try {
                response.sendRedirect(redirectUrl);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
