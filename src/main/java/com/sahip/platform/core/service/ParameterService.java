package com.sahip.platform.core.service;

import com.sahip.platform.core.dto.ParameterDTO;
import com.sahip.platform.core.entity.Parameter;
import com.sahip.platform.core.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ParameterService extends BaseService<Parameter, ParameterDTO, Long> {
    String getValue(String category, String code);

    ParameterDTO create(ParameterDTO dto);

    ParameterDTO get(Long id);

    Page<ParameterDTO> search(String category, String code, Boolean active, Pageable pageable);

    ParameterDTO update(Long id, ParameterDTO dto);

    ParameterDTO toggle(Long id);

    void delete(Long id);

    // BaseService bridge
    @Override
    default Page<ParameterDTO> search(Specification<Parameter> spec, Pageable pageable) {
        throw new UnsupportedOperationException("Use search(category,code,active,pageable) for ParameterService");
    }
}
