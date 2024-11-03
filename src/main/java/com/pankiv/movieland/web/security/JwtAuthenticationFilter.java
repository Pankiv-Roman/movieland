package com.pankiv.movieland.web.security;

import com.pankiv.movieland.service.impl.DefaultUserDetailsService;
import com.pankiv.movieland.web.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.pankiv.movieland.web.security.service.JwtService.AUTH_SCHEME;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final DefaultUserDetailsService defaultUserDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwtToken = null;
        String userEmail = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(AUTH_SCHEME)) {
            jwtToken = authorizationHeader.substring(AUTH_SCHEME.length());
        } else {
            if (request.getCookies() != null) {
                for (var cookie : request.getCookies()) {
                    if ("token".equals(cookie.getName())) {
                        jwtToken = cookie.getValue();
                        break;
                    }
                }
            }
        }

        if (jwtToken == null) {
            logger.debug("No JWT token found in the request");
            filterChain.doFilter(request, response);
            return;
        }

        if (jwtService.isTokenInBlacklist(jwtToken)) {
            logger.debug("JWT token is blacklisted");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        userEmail = jwtService.extractUserName(jwtToken);
        logger.debug("Extracted userEmail: {}");

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = defaultUserDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                MDC.put("user", userDetails.getUsername());
                logger.debug("Authenticated user: {}");
            } else {
                logger.debug("JWT token is invalid for user: {}");
            }
        }

        filterChain.doFilter(request, response);
    }
}