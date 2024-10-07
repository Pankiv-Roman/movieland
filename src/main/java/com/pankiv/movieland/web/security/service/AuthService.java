package com.pankiv.movieland.web.security.service;


import com.pankiv.movieland.entity.User;
import com.pankiv.movieland.repository.UserRepository;
import com.pankiv.movieland.service.impl.DefaultUserDetailsService;
import com.pankiv.movieland.web.request.AuthRequest;
import com.pankiv.movieland.web.responce.AuthResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final DefaultUserDetailsService defaultUserDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest signInRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        UserDetails userDetails = defaultUserDetailsService.loadUserByUsername(signInRequest.getEmail());
        Optional<User> user = userRepository.findByEmail(signInRequest.getEmail());
        String token = jwtService.generateToken(userDetails);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(7200000);
        response.addCookie(cookie);

        return new AuthResponse(user.get().getNickname(), token, "Successfully Signed In");
    }

    public void logout(String token) {
        jwtService.invalidateToken(token); // Видаляємо токен
    }
}