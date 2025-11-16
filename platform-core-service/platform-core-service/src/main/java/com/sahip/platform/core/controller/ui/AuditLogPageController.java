package com.sahip.platform.core.controller.ui;

import com.sahip.platform.core.dto.AuditLogDTO;
import com.sahip.platform.core.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuditLogPageController {

    private final AuditLogService auditLogService;

    @GetMapping("/audit-logs")
    public String list(@RequestParam(required = false) String username,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "20") int size,
                       Model model) {
        Page<AuditLogDTO> logs = auditLogService.list(username, PageRequest.of(page, size));
        model.addAttribute("page", logs);
        model.addAttribute("username", username);
        return "audit/list";
    }
}


