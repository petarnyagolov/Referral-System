package com.infinno.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReferralIdentifierServiceImpl implements ReferralIdentifierService {
    @Override
    public String setReferralCode() {
        return UUID.randomUUID().toString();

    }

    @Override
    public String setUserHash() {
        return UUID.randomUUID().toString();
    }
}
