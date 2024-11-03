package com.pankiv.movieland.web.security.service;

import com.pankiv.movieland.entity.BlacklistedToken;
import com.pankiv.movieland.repository.BlacklistRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DefaultJwtService implements JwtService {

    private final BlacklistRepository blacklistRepository;


    public String generateToken(@NotNull UserDetails userDetails) {
        String login = userDetails.getUsername();
        return Jwts.builder()
                .subject(login)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 7200000))
                .signWith(getSignInKey())
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        if (blacklistRepository.existsByToken(token)) {
            return false;
        }
        return !isTokenExpired(token) && extractUserName(token).equals(userDetails.getUsername());
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    public boolean isTokenInBlacklist(String token) {
        return blacklistRepository.existsByToken(token);
    }

    public void invalidateToken(String token) {
        Date expirationDate = extractExpirationDate(token);
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationDate(expirationDate);

        blacklistRepository.save(blacklistedToken);
    }
    private Date extractExpirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private <T> T extractClaims(String token, @NotNull Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    private @NotNull SecretKey getSignInKey() {
        String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
