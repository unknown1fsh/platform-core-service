package com.sahip.platform.core.service;

import com.sahip.platform.core.dto.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<RoleDTO> list(Pageable pageable);

    RoleDTO get(Long id);

    RoleDTO create(RoleDTO dto);

    RoleDTO update(Long id, RoleDTO dto);

    void delete(Long id);
}


