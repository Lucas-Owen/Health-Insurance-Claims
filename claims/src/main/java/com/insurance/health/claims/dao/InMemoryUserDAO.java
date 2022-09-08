package com.insurance.health.claims.dao;

import com.insurance.health.claims.model.MyUserDetails;
import com.insurance.health.claims.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("InMemoryRepo")
public class InMemoryUserDAO implements UserRepo {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryUserDAO(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<MyUserDetails> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private List<MyUserDetails> getUsers(){
        List<MyUserDetails> users = new ArrayList<>(
                List.of(
                        new MyUserDetails(
                                    "admin",
                                    passwordEncoder.encode("password"),
                                    ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                                    true,
                                true,
                                true,
                                true
                                )
                )
        );

        return users;
    }
}
