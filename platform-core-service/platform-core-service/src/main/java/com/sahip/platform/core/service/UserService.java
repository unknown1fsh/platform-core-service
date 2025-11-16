package com.sahip.platform.core.service;

import com.sahip.platform.core.dto.UserDTO;
import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.enums.UserStatus;
import com.sahip.platform.core.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService extends BaseService<UserAccount, UserDTO, Long> {
    UserDTO create(UserDTO dto);
    UserDTO getById(Long id);
    Page<UserDTO> search(String username, UserStatus status, Pageable pageable);
    UserDTO update(Long id, UserDTO dto);
    void delete(Long id);
    UserDTO changeStatus(Long id, UserStatus status);
    void changePassword(Long id, String newPassword);

    // BaseService bridge
    @Override
    default UserDTO get(Long id) { return getById(id); }

    @Override
    default Page<UserDTO> search(Specification<UserAccount> spec, Pageable pageable) {
        throw new UnsupportedOperationException("Use search(username,status,pageable) for UserService");
    }
}
