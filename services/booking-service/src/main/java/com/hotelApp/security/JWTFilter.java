package com.hotelApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

  private final JWTVerifier jwtVerifier;

  public JWTFilter(JWTVerifier jwtVerifier) {
    this.jwtVerifier = jwtVerifier;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    String header = request.getHeader("Authorization");

    if(header != null && header.startsWith("Bearer ")){
      String token = header.substring(7);

      String username = jwtVerifier.extractUsername(token);

      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username,null, List.of());

      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request,response);
  }
}
