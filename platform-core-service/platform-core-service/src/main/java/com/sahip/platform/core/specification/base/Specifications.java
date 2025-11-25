package com.sahip.platform.core.specification.base;

import com.sahip.platform.core.entity.base.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

public final class Specifications {

    private Specifications() {
    }

    public static <T> Specification<T> likeIgnoreCase(String field, String value) {
        if (value == null || value.isBlank()) return null;
        return (root, query, cb) -> cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%");
    }

    public static <T> Specification<T> equalsIfNotNull(String field, Object value) {
        if (value == null) return null;
        return (root, query, cb) -> cb.equal(root.get(field), value);
    }

    public static <T extends BaseEntity> Specification<T> isNotDeleted() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }
}

