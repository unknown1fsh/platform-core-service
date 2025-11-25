package com.sahip.platform.core.repository;

import com.sahip.platform.core.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long>, JpaSpecificationExecutor<Parameter> {
    Optional<Parameter> findByCategoryAndCodeAndActiveTrue(String category, String code);

    boolean existsByCategoryAndCode(String category, String code);
}
