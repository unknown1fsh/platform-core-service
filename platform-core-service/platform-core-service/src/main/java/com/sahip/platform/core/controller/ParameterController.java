package com.sahip.platform.core.controller;

import com.sahip.platform.core.dto.ParameterDTO;
import com.sahip.platform.core.service.ParameterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parameters")
@RequiredArgsConstructor
public class ParameterController {

    private final ParameterService parameterService;

    @PostMapping
    public ParameterDTO create(@Valid @RequestBody ParameterDTO dto) {
        return parameterService.create(dto);
    }

    @GetMapping("/{id}")
    public ParameterDTO get(@PathVariable Long id) {
        return parameterService.get(id);
    }

    @GetMapping
    public Page<ParameterDTO> search(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Boolean active,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return parameterService.search(category, code, active, PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public ParameterDTO update(@PathVariable Long id, @Valid @RequestBody ParameterDTO dto) {
        return parameterService.update(id, dto);
    }

    @PatchMapping("/{id}/toggle")
    public ParameterDTO toggle(@PathVariable Long id) {
        return parameterService.toggle(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        parameterService.delete(id);
    }
}

