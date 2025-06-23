package com.maria.web_access.domain.service;

import com.maria.web_access.domain.entity.Secret;
import com.maria.web_access.domain.repo.SecretRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {
    @Autowired
    private SecretRepo secretRepository;

    public boolean checkSecretExists(String secret) {
        Secret storedSecret = secretRepository.findBySecret(secret);
        return storedSecret != null;
    }
}
