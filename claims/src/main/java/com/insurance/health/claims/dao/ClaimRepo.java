package com.insurance.health.claims.dao;

import com.insurance.health.claims.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepo extends JpaRepository<Claim, Long> {

}
