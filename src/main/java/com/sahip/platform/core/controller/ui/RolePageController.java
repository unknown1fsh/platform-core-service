package com.sahip.platform.core.controller.ui;

import com.sahip.platform.core.dto.RoleDTO;
import com.sahip.platform.core.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RolePageController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        Page<RoleDTO> p = roleService.list(PageRequest.of(page, size));
        model.addAttribute("page", p);
        return "roles/list";
    }

    @GetMapping("/roles/new")
    public String createForm(Model model) {
        model.addAttribute("dto", new RoleDTO());
        model.addAttribute("action", "/roles/new");
        return "roles/form";
    }

    @PostMapping("/roles/new")
    public String createSubmit(@ModelAttribute RoleDTO dto) {
        roleService.create(dto);
        return "redirect:/roles";
    }

    @GetMapping("/roles/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("dto", roleService.get(id));
        model.addAttribute("action", "/roles/" + id + "/edit");
        return "roles/form";
    }

    @PostMapping("/roles/{id}/edit")
    public String editSubmit(@PathVariable Long id, @ModelAttribute RoleDTO dto) {
        roleService.update(id, dto);
        return "redirect:/roles";
    }

    @PostMapping("/roles/{id}/delete")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}


