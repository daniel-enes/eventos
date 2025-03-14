package com.demandas.eventos.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        System.out.println("The username " + username + "with roles " + authentication.getAuthorities() + " is logged in.");

        boolean hasClient = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("client"));
        boolean hasEmployee = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("employee"));
        boolean hasAdmin = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("admin"));

        if(hasClient || hasEmployee || hasAdmin) {
            response.sendRedirect("/dashboard");
        }
    }
}
