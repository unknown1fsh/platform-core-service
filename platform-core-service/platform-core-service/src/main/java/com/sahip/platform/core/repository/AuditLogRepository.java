package com.sahip.platform.core.repository;

import com.sahip.platform.core.entity.AuditLog;
import com.sahip.platform.core.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditLogRepository extends BaseRepository<AuditLog, Long> {

    Page<AuditLog> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
}


