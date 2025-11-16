package com.sahip.platform.core.service.impl;

import com.sahip.platform.core.dto.RoleDTO;
import com.sahip.platform.core.entity.Role;
import com.sahip.platform.core.exception.NotFoundException;
import com.sahip.platform.core.mapper.RoleMapper;
import com.sahip.platform.core.repository.RoleRepository;
import com.sahip.platform.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<RoleDTO> list(Pageable pageable) {
        return roleRepository.findAll(pageable).map(roleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO get(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional
    public RoleDTO create(RoleDTO dto) {
        Role role = roleMapper.toEntity(dto);
        role.setId(null);
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return roleMapper.toDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found"));
        roleRepository.delete(role);
    }
}


