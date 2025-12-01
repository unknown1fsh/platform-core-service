package com.sahip.platform.core.service.impl;

import com.sahip.platform.core.dto.UserDTO;
import com.sahip.platform.core.entity.UserAccount;
import com.sahip.platform.core.enums.UserStatus;
import com.sahip.platform.core.exception.NotFoundException;
import com.sahip.platform.core.exception.BusinessException;
import com.sahip.platform.core.mapper.UserMapper;
import com.sahip.platform.core.repository.UserRepository;
import com.sahip.platform.core.service.UserService;
import com.sahip.platform.core.specification.UserSpecification;
import com.sahip.platform.core.specification.base.Specifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO create(UserDTO dto) {
        if (dto.getUsername() != null && userRepository.existsByUsername(dto.getUsername())) {
            throw new BusinessException("Username already exists: " + dto.getUsername());
        }
        UserAccount entity = userMapper.toEntity(dto);
        entity.setStatus(UserStatus.ACTIVE);
        entity.setPassword(passwordEncoder.encode("ChangeMe123")); // örnek
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public UserDTO getById(Long id) {
        UserAccount entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        return userMapper.toDto(entity);
    }

    @Override
    public Page<UserDTO> search(String username, UserStatus status, Pageable pageable) {
        Specification<UserAccount> spec = Specification
                .where(Specifications.<UserAccount>isNotDeleted())
                .and(UserSpecification.usernameContains(username))
                .and(UserSpecification.statusEquals(status));

        return userRepository.findAll(spec, pageable)
                .map(userMapper::toDto);
    }

    @Transactional
    @Override
    public UserDTO update(Long id, UserDTO dto) {
        UserAccount entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        if (dto.getUsername() != null && !dto.getUsername().equals(entity.getUsername())) {
            if (userRepository.existsByUsername(dto.getUsername())) {
                throw new BusinessException("Username already exists: " + dto.getUsername());
            }
            entity.setUsername(dto.getUsername());
        }
        entity.setEmail(dto.getEmail());
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        return userMapper.toDto(userRepository.save(entity));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        UserAccount entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        entity.setDeletedAt(java.time.LocalDateTime.now());
        userRepository.save(entity);
    }

    @Transactional
    @Override
    public UserDTO changeStatus(Long id, UserStatus status) {
        UserAccount entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        entity.setStatus(status);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Transactional
    @Override
    public void changePassword(Long id, String newPassword) {
        UserAccount entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found: " + id));
        entity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(entity);
    }
}
