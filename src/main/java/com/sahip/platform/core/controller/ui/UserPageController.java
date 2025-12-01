package com.sahip.platform.core.controller.ui;

import com.sahip.platform.core.dto.UserDTO;
import com.sahip.platform.core.enums.UserStatus;
import com.sahip.platform.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;

    @GetMapping("/users")
    public String list(@RequestParam(required = false) String username,
                       @RequestParam(required = false) UserStatus status,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        Page<UserDTO> p = userService.search(username, status, PageRequest.of(page, size));
        model.addAttribute("page", p);
        model.addAttribute("username", username);
        model.addAttribute("statuses", UserStatus.values());
        return "users/list";
    }

    @GetMapping("/users/new")
    public String createForm(Model model) {
        model.addAttribute("dto", new UserDTO());
        model.addAttribute("action", "/users/new");
        model.addAttribute("statuses", UserStatus.values());
        return "users/form";
    }

    @PostMapping("/users/new")
    public String createSubmit(@ModelAttribute UserDTO dto) {
        userService.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("dto", userService.getById(id));
        model.addAttribute("action", "/users/" + id + "/edit");
        model.addAttribute("statuses", UserStatus.values());
        return "users/form";
    }

    @PostMapping("/users/{id}/edit")
    public String editSubmit(@PathVariable Long id, @ModelAttribute UserDTO dto) {
        userService.update(id, dto);
        return "redirect:/users";
    }
}

