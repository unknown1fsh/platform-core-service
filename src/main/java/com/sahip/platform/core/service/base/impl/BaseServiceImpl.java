package com.sahip.platform.core.service.base.impl;

import com.sahip.platform.core.entity.base.BaseEntity;
import com.sahip.platform.core.exception.NotFoundException;
import com.sahip.platform.core.mapper.BaseMapper;
import com.sahip.platform.core.repository.base.BaseRepository;
import com.sahip.platform.core.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public abstract class BaseServiceImpl<E extends BaseEntity, D, ID> implements BaseService<E, D, ID> {

    protected final BaseRepository<E, ID> repository;
    protected final BaseMapper<E, D> mapper;

    protected BaseServiceImpl(BaseRepository<E, ID> repository, BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public D get(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public Page<D> search(Specification<E> spec, Pageable pageable) {
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    @Transactional
    @Override
    public D update(ID id, D dto) {
        E entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: " + id));
        E updated = mapper.toEntity(dto);
        updated.setId(entity.getId());
        updated.setCreatedAt(entity.getCreatedAt());
        return mapper.toDto(repository.save(updated));
    }

    @Transactional
    @Override
    public void delete(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: " + id));
        entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }

    @Transactional
    @Override
    public void restore(ID id) {
        E entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Record not found: " + id));
        entity.setDeletedAt(null);
        repository.save(entity);
    }
}

