package com.insurance.health.claims.dao;

import com.insurance.health.claims.model.MyUserDetails;

import java.util.Optional;

public interface UserRepo {
    Optional<MyUserDetails> selectUserByUsername(String username);
}
