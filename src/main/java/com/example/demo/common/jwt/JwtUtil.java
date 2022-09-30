package com.example.demo.common.jwt;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  public final static long ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 10;

  public final static String ACCESS_TOKEN_NAME = "accessToken";

  private final static String SECRET_KEY = "pyungkang.com2020-secret-key-aaa-bbb";

  private Key getSigningKey(String secretKey) {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public Claims extractAllClaims(String token) throws ExpiredJwtException {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey(SECRET_KEY))
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String getUsername(String token) {
    return extractAllClaims(token).get("username", String.class);
  }

  public Boolean isTokenExpired(String token) {
    final Date expiration = extractAllClaims(token).getExpiration();
    return expiration.before(new Date());
  }

  public String generateToken(User user) {
    return doGenerateToken(user.getUserId(), user.getRole(), ACCESS_TOKEN_VALIDATION_SECOND);
  }

  public String doGenerateToken(String username, Role role, long expireTime) {

    Claims claims = Jwts.claims();
    claims.put("username", username);
    claims.put("role", role);

    String jwt = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expireTime))
        .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
        .compact();

    return jwt;
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsername(token);

    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
