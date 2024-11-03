package com.pankiv.movieland.web.security.config;

import com.pankiv.movieland.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtMdcFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtMdcFilter.class);
    public static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    private final UserRepository userRepository;

    @Override
    public void doFilter(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            logger.debug("JwtMdcFilter is called for request: {}", request.getRequestURI());
            String token = getTokenFromRequest(request);
            String email = getEmailFromToken(token);
            String username = findUsernameByEmail(email);

            MDC.put("token", token != null ? token.length() > 10 ? token.substring(token.length() - 10,token.length()) : token : "N/A");
            MDC.put("email", Objects.requireNonNullElse(username, "guest"));

            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private @Nullable String getTokenFromRequest(@NotNull HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private @Nullable String getEmailFromToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
            JwtParser parser = Jwts.parser().setSigningKey(key).build();

            Claims claims = parser.parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            logger.error("Error parsing token", e);
            return null;
        }
    }

    private @Nullable String findUsernameByEmail(String email) {
        if (email == null) {
            return "quest";
        }
        return userRepository.findByEmail(email).getNickname();
    }
}
