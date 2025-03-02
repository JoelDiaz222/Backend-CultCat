package com.cultcat.backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtParser;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = Dotenv.load().get("JWT_SECRET_KEY");
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 hour

    public static String generateToken(String googleId, String email) {
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(googleId)
                .issuedAt(now)
                .expiration(expirationDate)
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String getGoogleIdFromToken(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public static boolean isTokenExpired(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public static boolean validateToken(String token, String googleId) {
        String tokenGoogleId = getGoogleIdFromToken(token);
        return googleId.equals(tokenGoogleId) && !isTokenExpired(token);
    }
}
