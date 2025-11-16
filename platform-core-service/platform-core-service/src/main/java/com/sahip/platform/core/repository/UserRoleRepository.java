package com.sahip.platform.core.repository;

import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.entity.UserRole;
import com.sahip.platform.core.repository.base.BaseRepository;

import java.util.List;

public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    List<UserRole> findByUser(UserAccount user);
}


