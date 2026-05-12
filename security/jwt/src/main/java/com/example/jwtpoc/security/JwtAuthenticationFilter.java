package com.example.jwtpoc.security;

import com.example.jwtpoc.user.AppUser;
import com.example.jwtpoc.user.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Reads the Authorization header on each request and authenticates the request
 * when a valid Bearer JWT is present.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AppUserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * If a Bearer token exists, validate it and populate Spring Security's
     * SecurityContext. If the header is missing, the request continues without
     * authentication and protected APIs are rejected later by Spring Security.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring("Bearer ".length());
        String email = jwtService.extractSubject(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            userRepository.findByEmailIgnoreCase(email)
                    .filter(user -> jwtService.isTokenValid(token, user))
                    .ifPresent(user -> authenticateRequest(request, user));
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Stores the authenticated user in Spring Security for the rest of this request.
     */
    private void authenticateRequest(HttpServletRequest request, AppUser user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
