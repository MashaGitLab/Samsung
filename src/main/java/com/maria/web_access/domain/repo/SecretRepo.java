package com.maria.web_access.domain.repo;

import com.maria.web_access.domain.entity.Secret;
import org.springframework.data.repository.CrudRepository;

public interface SecretRepo extends CrudRepository<Secret, Long> {

    Secret findBySecret(String secret);
}
