package com.sahip.platform.core.repository;

import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long>, JpaSpecificationExecutor<UserAccount> {

    Optional<UserAccount> findByUsername(String username);

    boolean existsByUsername(String username);

    long countByStatus(UserStatus status);
}
