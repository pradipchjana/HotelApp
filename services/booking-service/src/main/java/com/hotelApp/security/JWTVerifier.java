package com.hotelApp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JWTVerifier {
  private final String secret = "ourhotelappmadebykhasimjanadinesh";
  private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

  public String extractUsername(String token){
    Claims claims = Jwts.parser().verifyWith((javax.crypto.SecretKey) key).build().parseSignedClaims(token).getPayload();

    return claims.getSubject();
  }

  public boolean isValid(String token,String username){
    return  extractUsername(token).equals(username);
  }
}
