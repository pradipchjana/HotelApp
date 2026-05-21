package com.hotelApp.security;

import com.hotelApp.user.modal.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
  private final String secret = "ourhotelappmadebykhasimjanadinesh";
  private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

  public String generateToken(User user){
    int expirationTime = 1000 * 60 * 60;

    return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() * expirationTime))
            .signWith(key)
            .compact();
  }

  public String extractUsername(String token){
    Claims claims = Jwts.parser().verifyWith((javax.crypto.SecretKey) key).build().parseSignedClaims(token).getPayload();

    return claims.getSubject();
  }

  public boolean isValid(String token,String username){
    return  extractUsername(token).equals(username);
  }
}
