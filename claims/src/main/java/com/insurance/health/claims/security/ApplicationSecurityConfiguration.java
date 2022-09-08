package com.insurance.health.claims.security;

import com.insurance.health.claims.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

import static com.insurance.health.claims.security.ApplicationUserRole.ADMIN;


@Configuration
public class ApplicationSecurityConfiguration implements WebMvcConfigurer{
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
                .csrf().ignoringAntMatchers("/provider/login", "/provider/claim", "/provider/invoice").and()
                .authorizeRequests()
                .antMatchers("/", "/provider", "/provider/login").permitAll()
                .antMatchers("/company").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

//    @Bean
//    protected UserDetailsService userDetailsService(){
//        UserDetails lucas = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
////                .roles(ADMIN.name()) //replaced this with granted authorities
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("password"))
//                .roles(PROVIDER.name())
//                .build();
//        return new InMemoryUserDetailsManager(lucas, user);
//    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}