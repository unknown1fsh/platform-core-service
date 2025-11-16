package com.sahip.platform.core.controller.ui;

import com.sahip.platform.core.dto.ParameterDTO;
import com.sahip.platform.core.service.ParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ParameterPageController {

    private final ParameterService parameterService;

    @GetMapping("/parameters")
    public String list(@RequestParam(required = false) String category,
                       @RequestParam(required = false) String code,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {
        Page<ParameterDTO> p = parameterService.search(category, code, null, PageRequest.of(page, size));
        model.addAttribute("page", p);
        model.addAttribute("category", category);
        model.addAttribute("code", code);
        return "parameters/list";
    }

    @GetMapping("/parameters/new")
    public String createForm(Model model) {
        model.addAttribute("dto", new ParameterDTO());
        model.addAttribute("action", "/parameters/new");
        return "parameters/form";
    }

    @PostMapping("/parameters/new")
    public String createSubmit(@ModelAttribute ParameterDTO dto) {
        parameterService.create(dto);
        return "redirect:/parameters";
    }

    @GetMapping("/parameters/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("dto", parameterService.get(id));
        model.addAttribute("action", "/parameters/" + id + "/edit");
        return "parameters/form";
    }

    @PostMapping("/parameters/{id}/edit")
    public String editSubmit(@PathVariable Long id, @ModelAttribute ParameterDTO dto) {
        parameterService.update(id, dto);
        return "redirect:/parameters";
    }
}

