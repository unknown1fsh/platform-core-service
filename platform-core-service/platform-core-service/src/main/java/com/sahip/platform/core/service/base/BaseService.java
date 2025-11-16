package com.sahip.platform.core.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService<E, D, ID> {

    D create(D dto);

    D get(ID id);

    Page<D> search(Specification<E> spec, Pageable pageable);

    D update(ID id, D dto);

    void delete(ID id);

    default void restore(ID id) {
        throw new UnsupportedOperationException("Restore is not implemented");
    }
}

