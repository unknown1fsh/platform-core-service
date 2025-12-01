package com.sahip.platform.core.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final List<T> items;

    public PageResponse(int page, int size, long totalElements, int totalPages, List<T> items) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<T>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
        );
    }
}

