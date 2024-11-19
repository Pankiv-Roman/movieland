package com.pankiv.movieland.web.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String AUTH_SCHEME = "Bearer ";

    String generateToken(UserDetails userDetails);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    void invalidateToken(String token);

    boolean isTokenInBlacklist(String jwtToken);
}