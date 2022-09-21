package com.insurance.health.claims.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.insurance.health.claims.security.filters.JwtUserPassAuthFilter.KEY;

public class JwtVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header==null || header.isEmpty() || !header.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.replace("Bearer ", "");
        try{
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = body.getSubject();
            var auths = (List<Map<String, String>>) body.get("authorities");
            Set<SimpleGrantedAuthority> authorities = auths.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }catch (JwtException e){
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }
    }
}
