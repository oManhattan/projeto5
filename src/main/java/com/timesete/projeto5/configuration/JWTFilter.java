package com.timesete.projeto5.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.timesete.projeto5.business.service.JWTService;
import com.timesete.projeto5.business.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        String token = null, email = null;

        if (authorization != null && authorization.startsWith("Bearer")) {
            email = jwtService.getSubject(authorization);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                if (jwtService.isValidToken(authorization)) {
                    var user = userService.getUserByToken(token);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(user.getAccessType().toSimpleGrantedAuthority()));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    response.sendError(401, "Token is invalid");
                }

            } catch (Exception e) {
                response.sendError(401, e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
