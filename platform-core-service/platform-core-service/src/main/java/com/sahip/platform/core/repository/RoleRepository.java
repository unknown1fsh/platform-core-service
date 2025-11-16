package com.sahip.platform.core.repository;

import com.sahip.platform.core.entity.Role;
import com.sahip.platform.core.repository.base.BaseRepository;

public interface RoleRepository extends BaseRepository<Role, Long> {

    boolean existsByName(String name);
}


