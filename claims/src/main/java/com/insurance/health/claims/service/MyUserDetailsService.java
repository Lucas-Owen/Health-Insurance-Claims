package com.insurance.health.claims.service;

import com.insurance.health.claims.dao.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepo userRepo;

    @Autowired
    public MyUserDetailsService(@Qualifier("InMemoryRepo") UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.selectUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
