package com.sahip.platform.core.controller;

import com.sahip.platform.core.dto.UserDTO;
import com.sahip.platform.core.dto.request.ChangePasswordRequest;
import com.sahip.platform.core.enums.UserStatus;
import com.sahip.platform.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO dto) {
        return userService.create(dto);
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public Page<UserDTO> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) UserStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return userService.search(username, status, PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}/status")
    public UserDTO changeStatus(@PathVariable Long id, @RequestParam("value") UserStatus status) {
        return userService.changeStatus(id, status);
    }

    @PostMapping("/{id}/password")
    public void changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request.getNewPassword());
    }
}
