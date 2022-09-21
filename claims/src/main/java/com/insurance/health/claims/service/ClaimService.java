package com.insurance.health.claims.service;

import com.insurance.health.claims.dao.ClaimRepo;
import com.insurance.health.claims.model.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {

    private final ClaimRepo claimRepo;

    @Autowired
    public ClaimService(ClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }

    public void save(Claim claim){
        claimRepo.save(claim);
    }
}
