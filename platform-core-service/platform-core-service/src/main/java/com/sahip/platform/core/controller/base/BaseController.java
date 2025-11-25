package com.sahip.platform.core.controller.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<E, D> {

    protected abstract D doCreate(D dto);
    protected abstract D doGet(Long id);
    protected abstract Page<D> doSearch(Specification<E> spec, Pageable pageable);
    protected abstract D doUpdate(Long id, D dto);
    protected abstract void doDelete(Long id);

    @PostMapping
    public D create(@RequestBody D dto) {
        return doCreate(dto);
    }

    @GetMapping("/{id}")
    public D get(@PathVariable Long id) {
        return doGet(id);
    }

    @PutMapping("/{id}")
    public D update(@PathVariable Long id, @RequestBody D dto) {
        return doUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doDelete(id);
    }
}

