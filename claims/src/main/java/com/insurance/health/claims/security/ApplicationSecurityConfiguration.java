package com.insurance.health.claims.security;

import com.insurance.health.claims.security.filters.JwtUserPassAuthFilter;
import com.insurance.health.claims.security.filters.JwtVerifierFilter;
import com.insurance.health.claims.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static com.insurance.health.claims.security.ApplicationUserRole.ADMIN;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
                                            @Qualifier("MyUserDetailsService") MyUserDetailsService userDetailsService){
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf()
                .ignoringAntMatchers("/login", "/provider/claim", "/provider/invoice")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUserPassAuthFilter(daoAuthenticationProvider()))
                .addFilterAfter(new JwtVerifierFilter(), JwtUserPassAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/company").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();

        return http.build();
    }


    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
