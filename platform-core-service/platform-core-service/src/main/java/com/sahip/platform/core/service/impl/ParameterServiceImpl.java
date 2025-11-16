package com.sahip.platform.core.service.impl;

import com.sahip.platform.core.dto.ParameterDTO;
import com.sahip.platform.core.entity.Parameter;
import com.sahip.platform.core.exception.BusinessException;
import com.sahip.platform.core.exception.NotFoundException;
import com.sahip.platform.core.mapper.ParameterMapper;
import com.sahip.platform.core.repository.ParameterRepository;
import com.sahip.platform.core.service.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository parameterRepository;
    private final ParameterMapper parameterMapper;

    @Override
    public String getValue(String category, String code) {
        Parameter p = parameterRepository
                .findByCategoryAndCodeAndActiveTrue(category, code)
                .orElseThrow(() -> new NotFoundException("Parameter not found: " + category + "/" + code));
        return p.getValue();
    }

    @Transactional
    @Override
    public ParameterDTO create(ParameterDTO dto) {
        if (parameterRepository.existsByCategoryAndCode(dto.getCategory(), dto.getCode())) {
            throw new BusinessException("Parameter already exists: " + dto.getCategory() + "/" + dto.getCode());
        }
        Parameter entity = parameterMapper.toEntity(dto);
        if (entity.getActive() == null) {
            entity.setActive(true);
        }
        return parameterMapper.toDto(parameterRepository.save(entity));
    }

    @Override
    public ParameterDTO get(Long id) {
        Parameter entity = parameterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parameter not found: " + id));
        return parameterMapper.toDto(entity);
    }

    @Override
    public Page<ParameterDTO> search(String category, String code, Boolean active, Pageable pageable) {
        Specification<Parameter> spec = Specification.where((root, q, cb) -> cb.isNull(root.get("deletedAt")));
        if (category != null && !category.isBlank()) {
            spec = spec.and((root, q, cb) -> cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() + "%"));
        }
        if (code != null && !code.isBlank()) {
            spec = spec.and((root, q, cb) -> cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%"));
        }
        if (active != null) {
            spec = spec.and((root, q, cb) -> cb.equal(root.get("active"), active));
        }
        return parameterRepository.findAll(spec, pageable).map(parameterMapper::toDto);
    }

    @Transactional
    @Override
    public ParameterDTO update(Long id, ParameterDTO dto) {
        Parameter entity = parameterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parameter not found: " + id));
        if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
        if (dto.getCode() != null) entity.setCode(dto.getCode());
        if (dto.getValue() != null) entity.setValue(dto.getValue());
        entity.setDescription(dto.getDescription());
        if (dto.getActive() != null) entity.setActive(dto.getActive());
        return parameterMapper.toDto(parameterRepository.save(entity));
    }

    @Transactional
    @Override
    public ParameterDTO toggle(Long id) {
        Parameter entity = parameterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parameter not found: " + id));
        entity.setActive(Boolean.FALSE.equals(entity.getActive()));
        return parameterMapper.toDto(parameterRepository.save(entity));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Parameter entity = parameterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parameter not found: " + id));
        entity.setDeletedAt(LocalDateTime.now());
        parameterRepository.save(entity);
    }
}
