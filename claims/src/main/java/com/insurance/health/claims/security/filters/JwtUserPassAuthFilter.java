package com.insurance.health.claims.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;


public class JwtUserPassAuthFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationProvider authenticationManager;

    public JwtUserPassAuthFilter(AuthenticationProvider authenticationProvider) {
        this.authenticationManager = authenticationProvider;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "THISKEYSHOULDBEASLONGASDESIREDTHISKEYSHOULDBEASLONGASDESIREDTHISKEYSHOULDBEASLONGASDESIRED";

        String token = Jwts.builder()
                    .setSubject(authResult.getName())
                    .claim("authorities", authResult.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                    .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                    .compact();

        response.addHeader("Authorization", "Bearer " + token);
    }

}
