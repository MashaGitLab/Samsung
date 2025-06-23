package com.maria.web_access.domain.repo;

import com.maria.web_access.domain.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {
}
